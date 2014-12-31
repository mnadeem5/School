/********************************************************************************
 * calc-server.c
 * 
 * Computer Science 3357a
 * Assignment 1
 *
 * Author: Zaid Albirawi
 * Email: zalbiraw@uwo.ca
 * 
 * This program accepts a port number as its argument, opens and listen to a socket
 * connections on the port. As soon as a connection is established, the program 
 * takes the message and sends it to calc.h, which handles the message and returns
 * a reponse message.
*******************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
  
#include <getopt.h>
#include <syslog.h>

#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>

#include "sockaddr.h"
#include "calc.h"

#define SERVER 1
#define BACKLOG 25

//Functions prototypes.
int bind_socket(struct addrinfo*);
int wait_for_connection(int);
void handle_connection(int);

/********************************************************************************
 * main
 * 
 * main executes the program and validates that the user has entered a port number, 
 * otherwise, it terminates.
*******************************************************************************/
int main(int argc, char** argv)
{
	//Holds the input for the port number
	char *port = NULL;
	int opt, option_index = 0;

	//Syslog call.
	openlog("Server Log", LOG_PERROR | LOG_PID | LOG_NDELAY, LOG_USER);
	//Sets the default mask for syslog.
	setlogmask(LOG_UPTO(LOG_INFO));

	//Populates the long_options list with the acceptable options.
	static struct option long_options[] =
	{
		{"port", 	required_argument, 0, 'p'},
		{"debug", 	no_argument, 0, 'd'},
		{0, 0, 0, 0}
	};

	//Iterates through the program's input and tries to find the port number.
	while((opt = getopt_long(argc, argv, "p:d", long_options, &option_index)) != -1)
		switch (opt)
		{
			case 'p':
				//Sets the port value to the provided value.
				port = optarg;
				break;
			case 'd':
				//Turns on the debug mode.
				setlogmask(LOG_UPTO(LOG_DEBUG));
				break;
		}
	//Terminates with an error if no port number was provided.
	if (port != NULL)
	{
		printf("Plase provide the --port argument.\n");
		return;
	}

	//Creates a list, results, that will be populated by the function
	//get_sockaddr with the possible socket addresses./
	struct addrinfo* results = get_sockaddr(NULL, port, SERVER);
	//Trys to find a valid socket and bind it.
	int skt = bind_socket(results);

	//Listens to the socket
	if (listen(skt, BACKLOG) == -1)
	{
		printf("Error: unable to listen on socket.\n");
		return;
	}

	//Waits for a connection on the socket and handles the request.
	while(1)
		handle_connection(wait_for_connection(skt));

	//Closes the syslog.
	closelog();
	return 0;
}

/********************************************************************************
 * bind_socket
 * 
 * bind_socket takes the list provided by the main function and trys to bind the 
 * specified information with a socket.
*******************************************************************************/
int bind_socket(struct addrinfo *list)
{
	//Holds information about the connection we are trying to optain.
	struct addrinfo *addr;
	//Holds the address of the socket.
	int skt;
	//A parameter to make the socket reuseable.
	char reuse = '1';

	//Iterates through the list provided by the main function and tries
	//to find a valid socket.
	for (addr = list; addr !=NULL; addr->ai_next)
	{
		//Opens a socket using the information provided by list.
		skt = socket(addr->ai_family, addr->ai_socktype, addr->ai_protocol);

		//If socket was not opened, then try the next one in list.
		if (skt == -1)
			continue;

		//Allows the socket to be reuseable instead of closing it right away.
		if (setsockopt(skt, SOL_SOCKET, SO_REUSEADDR, &reuse, sizeof(int)) == -1)
		{
			printf("Error: unable to set the socket option.\n");
			return -1;
		}

		//Binds the socket with the address, if the binding fails, continue to 
		//to the next item in list.
		if (bind(skt, addr->ai_addr, addr->ai_addrlen) == -1)
		{
			//Closes the socket if the binding fails.
			close(skt);
			continue;
		}
		else
			break;
	}

	//Frees the memory location of list.
	freeaddrinfo(list);

	//If addr contains a value, then the binding was successful, returns socket
	//address.
	if (addr != NULL)
		return skt;

	printf("Error: unable to bind.\n");
	return -1;
}

/********************************************************************************
 * wait_for_connection
 * 
 * wait_for_connection waits for a connection to be established and accepted,
 * if a connection is established, return the connection.
*******************************************************************************/
int wait_for_connection(int skt)
{	
	struct sockaddr_in client_addr;
	char ip_address[INET_ADDRSTRLEN];
	int addr_len = sizeof(struct sockaddr_in), connection;
 
	//Sets the value of the connection to the result of the accept function
	//which returns a value > -1 if a connection was accepted.
	connection = accept(skt, (struct sockaddr*)&client_addr, &addr_len);

	//Terminates if the connection was not accepted.
	if (connection == -1)
	{
		printf("Error: unable to accept connection.\n");
		return -1;
	}

	//Translates the byte code address to ASCII from.
	inet_ntop(client_addr.sin_family, &client_addr.sin_addr, ip_address, sizeof(ip_address));
	//Prints the IP address onto the LOG_INFO syslog.
	syslog(LOG_INFO, "Request received from client %s\n", ip_address);

	return connection;
}

/********************************************************************************
 * handle_connection
 * 
 * handle_connection handles the request message and uses calc.h to generate a 
 * reponse message, returns the response message.
*******************************************************************************/
void handle_connection(int connection)
{
	char buffer[4096], response[4096], logger[4096], *result;
	int bytes_read;

	//Reads 0-4096 bytes from the client.
	bytes_read = recv(connection, buffer, sizeof(buffer), 0);

	if (bytes_read > 0)
	{
		//Add a line terminator.
		buffer[bytes_read] = '\0';

		//Prints the request message to the LOG_INFO syslog.
		syslog(LOG_INFO, "Expression was:  %s\n", buffer);
		//Calls the calc function with the request message and wait for a the 
		//function to provid the response request.
		result = calc(buffer);
		sprintf(response, "Status: %s", result);
		sprintf(logger, "Status Code: %s", result);
		//Prints the reponse message to the LOG_INFO syslog.
		syslog(LOG_INFO, "%s\n", logger);	

		if (send(connection, response, strlen(response), 0) == -1)
			syslog(LOG_INFO, "Error: unable to send response massage.\n");
	}
	else
		printf("Error: unable to read from socket.\n");

	//Closes the connection.
	close(connection);
}