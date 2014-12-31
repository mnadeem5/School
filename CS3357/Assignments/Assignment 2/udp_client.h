/******************************************************************************
 * udp_client.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * udp_client.c returns the socket address for client use.
******************************************************************************/

#ifndef UDP_CLIENT_H
#define UDP_CLIENT_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netdb.h>
#include "udp_sockets.h"

int create_client_socket(char*, char*, host_t*);

#endif