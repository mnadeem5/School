/******************************************************************************
 * udp_server.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * udp_server.c binds and returns the socket address for server use.
******************************************************************************/

#include "udp_server.h"

int create_server_socket(char* port)
{
	struct addrinfo* addr_list = get_udp_sockaddr(NULL, port, AI_PASSIVE);
    struct addrinfo* addr;
    int sockfd;

    for (addr = addr_list; addr != NULL; addr = addr->ai_next)
    {
        sockfd = socket(addr->ai_family, addr->ai_socktype, addr->ai_protocol);

        if (sockfd == -1)
            continue;

        if (bind(sockfd, addr->ai_addr, addr->ai_addrlen) == -1)
        {
            close(sockfd);
            continue;
        }
        else
            break;
    }

    freeaddrinfo(addr_list);
    if (addr == NULL)
    {
        perror("bind_socket: Unable to bind");
        exit(EXIT_FAILURE);
    }
    else
        return sockfd;
}

