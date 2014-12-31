/*****************************************************************************
 * connect.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * connect.c is a file that contains helper methods that are shared between
 * rftp.c and rftpd.c files.
******************************************************************************/

#ifndef CONNECT_H
#define CONNECT_H

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

#include "udp_sockets.h"

#define SENT '0'
#define RECE '1'
#define ACKS '2'
#define ACKR '3'

typedef struct
{
	uint8_t* name;
	int file_size;
	int sockfd;
	host_t* server;
	char protocol;
	int win_size;
	int timeout;
	bool verbose;
} transfer_info_t;

char* print_info(char, uint8_t, uint16_t, uint32_t);

#endif