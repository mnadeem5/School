/******************************************************************************
 * server_stop_and_wait.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * server_stop_and_wait.h contains the helper functions for rftpd.c 
******************************************************************************/

#ifndef SERVER_STOP_AND_WAIT_H
#define SERVER_STOP_AND_WAIT_H

#include <stdio.h>
#include <syslog.h>
#include <unistd.h>

#include "message.h"
#include "connect.h"
#include "connect_server.h"

void server_stop_and_wait_transfer(int, int, FILE*, int);
message_t* receive(char, short, int, host_t*, ctrl_msg_t*, data_msg_t*);

#endif