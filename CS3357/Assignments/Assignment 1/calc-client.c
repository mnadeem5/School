/********************************************************************************
 * calc-client.c
 * 
 * Computer Science 3357a
 * Assignment 1
 *
 * Author: Zaid Albirawi
 * Email: zalbiraw@uwo.ca
 * 
 * This program will be used to connect with the server and send and recieve
 * messages.
*******************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#include <getopt.h>

#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>

#include "sockaddr.h"

#define CLIENT 0

//Function prototypes.
int open_connection(struct addrinfo*);

/********************************************************************************
 * main
 * 
 * main is used to verify that the user enters the required arguments. If the
 * user provides the required arguments then the method calls upon open_connection
 * and sends the server the request message if the connection was successfully 
 * established.
*******************************************************************************/
int main(int argc, char** argv)
{
	char *server, *port, *expr;
	bool s = 0, p = 0, e = 0;
	int opt, option_index = 0; 

	//Populate the long_options list.
	static struct option long_options[] =
	{
		{"server",  required_argument, 0, 's' },
		{"port", 	required_argument, 0, 'p'},
		{"expr", 	required_argument, 0, 'e'},
		{0, 0, 0, 0}
	};

	//Iterates through the input and fetches the required values, if they are 
	//provided.
	while((opt = getopt_long(argc, argv, "s:p:e:", long_options, &option_index)) != -1)
		switch (opt)
		{
			case 's':
				s = 1;
				server = optarg;
				break;

			case 'p':
				p = 1;
				port = optarg;
				break;

			case 'e':
				e = 1;
				expr = optarg;
				//Attaches a carriage return, and a linefeed to the end of the message.
				strcat(expr, "\r\n");
				break;
				
			default:
				printf("Please enter valid arguments for calc-client\n");
				return 0;
				break;
		}
	//If any of the options are not provided, terminate.
	if (!s || !p || !e)
	{
		printf("Plase provide all of the following arguments --server, --port, and --expr.\n");
		return;
	}
 
	//Create a addrinfo with the server, port, CLIENT paramter(sets the
	//ai_flag to a AI_PASSIVE, creates a listenning socket)
	struct addrinfo *results = get_sockaddr(server, port, CLIENT);
	//Opens connection using the results list populated by the addrinfo
	//function.
	int bytes_read, skt = open_connection(results);
	char buffer [4096];

	//Checks if the message was sent, terminates otherwise.
	if (send(skt, expr, strlen(expr), 0) == -1)
	{
		printf("Error: failed to send message.\n");
		return; 
	}

	//Reads up to 4096 bytes from the server.
	bytes_read = recv(skt, buffer, sizeof(buffer), 0);

	//If nothing was read, terminate with an error.
	if (bytes_read == -1)
	{
		printf("Error: unable to read.\n");
		return;
	}

	//Output the response message.
	buffer[bytes_read] = '\0';
	printf("%s\n", buffer);

	//Closes the socket.
	close(skt);
	return 0;	
}

/********************************************************************************
 * open_connection
 * 
 * open_connection function opens a connection between the client and the server.
*******************************************************************************/
int open_connection(struct addrinfo* list)
{
	struct addrinfo *addr;
	int skt;

	//Iterates through the list of the possible addresses provided by the 
	//get_sockaddr and tries to find a valid socket.
	for(addr = list; addr != NULL; addr = addr->ai_next)
	{	
		//Opens a socket with the information provided by the list elements.
		skt = socket(addr->ai_family, addr->ai_socktype, addr->ai_protocol);

		//If the socket was not opened, then try the next addr.
		if (skt == -1)
			continue;
		
		//If the connection was successful, break out of the loop.
		if (connect(skt, addr->ai_addr, addr->ai_addrlen) != -1)
			break;
	}

	//Free the memory allocated for the list.
	freeaddrinfo(list);

	//If the addr is valid, then return as the answer.
	if (addr != NULL)
		return skt;

	printf("Error: unable to establish connection\n");
	return -1;
}