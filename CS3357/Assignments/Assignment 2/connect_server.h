/*****************************************************************************
 * connect_server.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * connect_server.c is a file that contains helper methods that are used by 
 * rftpd.c files.
******************************************************************************/

#ifndef CONNECT_SEREVER_H
#define CONNECT_SEREVER_H

#include <unistd.h>
#include <syslog.h>
#include "message.h"

#include "udp_sockets.h"
#include "udp_server.h"
#include "connect.h"
#include "server_stop_and_wait.h"
#include "server_go_back_n.h"

void connect_server(char*, int, char*);
ctrl_msg_t* handshake(int, int*);
void terminate(host_t, int, int);
void send_ACK(int, host_t*, message_t*);
FILE* makefile(char*, ctrl_msg_t*);

#endif