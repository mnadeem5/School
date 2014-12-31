#include "tcp_sockets.h"

struct addrinfo* get_sockaddr(const char* hostname, const char* port)
{
	struct addrinfo args, *results;
	int sockfd;

	memset(&args, 0, sizeof(struct addrinfo));

	args.ai_family = AF_INET;
	args.ai_socktype = SOCK_STREAM;

	sockfd = getaddrinfo(hostname, port, &args, &results);

	if (sockfd != 0) 
	{
		printf("Error: unable to get address info.\n");
		return NULL;
	}
	return results;
}

int open_connection(const char* hostname, const char* port)
{
	struct addrinfo *addr = get_sockaddr(hostname, port);
	int sockfd;

	for( ; addr != NULL; addr = addr->ai_next)
	{	
		sockfd = socket(addr->ai_family, addr->ai_socktype, addr->ai_protocol);

		if (sockfd == -1)
			continue;

		if (connect(sockfd, addr->ai_addr, addr->ai_addrlen) != -1)
			break;
	}
	freeaddrinfo(addr);

	if (addr != NULL)
		return sockfd;

	printf("Error: unable to establish connection\n");
	return -1;
}