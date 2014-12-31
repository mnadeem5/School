/******************************************************************************
 * udp_sockets.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * udp_sockets.c recieves and sends binary messages
******************************************************************************/

#ifndef UDP_SOCKETS_H
#define UDP_SOCKETS_H

#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <stdio.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <sys/types.h>

#include <poll.h>

#include "message.h"

typedef struct
{
	struct sockaddr_in addr;
	socklen_t addr_len;
	char friendly_ip[INET_ADDRSTRLEN];
} host_t;

struct addrinfo* get_udp_sockaddr(const char*, const char*, int);
message_t* receive_message_with_timer(int, host_t*, int, ctrl_msg_t*
	, data_msg_t*);
message_t* receive_message(int, host_t*, ctrl_msg_t*, data_msg_t*);
int send_message(int, uint8_t*, int, host_t*);

#endif