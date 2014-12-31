/******************************************************************************
 * server_go_back_n.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * server_go_back_n.c contains the helper functions for rftpd.c 
******************************************************************************/

#ifndef SERVER_GO_BACK_N_H
#define SERVER_GO_BACK_N_H

#include <stdio.h>
#include <syslog.h>
#include <unistd.h>

#include "message.h"
#include "connect.h"
#include "connect_server.h"

void go_back_n_transfer(int sockfd, int timeout, int win_size, FILE* file, int filesize);
void go_back_n_terminate(host_t source, int sockfd, int timeout);

#endif