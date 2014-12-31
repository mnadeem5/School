/******************************************************************************
 * udp_server.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * udp_server.c binds and returns the socket address for server use.
******************************************************************************/

#ifndef UDP_SERVER_H
#define UDP_SERVER_H

#include <stdio.h>
#include <stdlib.h>
#include <netdb.h>
#include <sys/socket.h>
#include <unistd.h>
#include "udp_sockets.h"

int create_server_socket(char*);

#endif