/******************************************************************************
 * udp_client.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * udp_client.c returns the socket address for client use.
******************************************************************************/

#include "udp_client.h"

int create_client_socket(char* hostname, char* port, host_t* server)
{
    int sockfd;
    struct addrinfo* addr;
    struct addrinfo* results = get_udp_sockaddr(hostname, port, 0);                 

    for (addr = results; addr != NULL; addr = addr->ai_next)                       
    {
        sockfd = socket(addr->ai_family, addr->ai_socktype, addr->ai_protocol);

        if (sockfd == -1)
          continue;

        memcpy(&server->addr, addr->ai_addr, addr->ai_addrlen);
        memcpy(&server->addr_len, &addr->ai_addrlen, sizeof(addr->ai_addrlen));
        break;
    }

    freeaddrinfo(results);

    if (addr == NULL)
    {
        perror("create_client_socket: Unable to create socket");
        exit(EXIT_FAILURE);
    }
    else
        return sockfd;                                                               
}