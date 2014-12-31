/*****************************************************************************
 * rftp.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * rftp.c is a client function that interprets the users input and performs
 * a file transfer operation between it and a server function. 
******************************************************************************/

#ifndef RFTP_H
#define RFTP_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <getopt.h>
#include <stdbool.h>

#include "udp_sockets.h"
#include "udp_client.h"
#include "connect.h"
#include "connect_client.h"
#include "client_stop_and_wait.h"
#include "client_go_back_n.h"

#define PORT "5000"
#define TOUT 50

#define STOPANDWAIT '0'
#define GOBACKN '1'

int create_client_socket(char*, char*, host_t*);
void client(char*, char*, transfer_info_t*);

#endif