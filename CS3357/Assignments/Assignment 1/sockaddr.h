/********************************************************************************
 * sockaddr.h
 * 
 * Computer Science 3357a
 * Assignment 1
 *
 * Author: Zaid Albirawi
 * Email: zalbiraw@uwo.ca
 * 
 * This header file is called upon by both the calc-server.c and calc-client 
 * programs to resolve the value of the list of possible 
*******************************************************************************/

/********************************************************************************
 * get_sockaddr
 *
 * get_sockaddr evaluates the hostname and port number to list of possible socket
 * values to which the user might be able to connect to.
*******************************************************************************/
struct addrinfo* get_sockaddr(const char* hostname, const char* port, bool mode)
{
	//Contains information about the connection type, and socket type.
	struct addrinfo args;
	//Linked list that will hold the result of possible socket connections 
	struct addrinfo *results;
	//An integer that contains the socket address.
	int skt;

	//Sets aside memorsy space for the args valriable so it could be populated.
	memset(&args, 0, sizeof(struct addrinfo));

	//ai_family specifies the address type to IPv4
	args.ai_family = AF_INET;
	//ai_socktype specifies the sockets type to streaming socket(TCP)
	args.ai_socktype = SOCK_STREAM;

	//Mode is weather the call is made by the calc-client or the calc-server
	//If its the calc-client then this is not executed, else it will be.
	if (mode)
		//Creates listening socket.
		args.ai_flags = AI_PASSIVE;

	//Finds the socket address that is compatible with the ip address and port
	//number 
	skt = getaddrinfo(hostname, port, &args, &results);

	//If skt is not 0 then the opration has failed, or there does not exist a
	//valid socket for the provided information.
	if (!skt)
	{
		printf("Error: unable to get address info.\n");
		return NULL;
	}
	//Returns a list of all results optained.
	return results;
}