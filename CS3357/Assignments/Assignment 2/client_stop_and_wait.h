/******************************************************************************
 * client_stop_and_wait.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * client_stop_and_wait.c implements the stop and wait protocol to send packets to the
 * server.
******************************************************************************/

#ifndef STOPANDWAIT_H
#define STOPANDWAIT_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <unistd.h>
#include <syslog.h>

#include "udp_sockets.h"
#include "message.h"
#include "connect.h"
#include "connect_client.h"

#define MAXLEN 1464

void stop_and_wait(transfer_info_t*);
void handshake(transfer_info_t*);
void transfer(transfer_info_t*);
void terminate(transfer_info_t*, int);
void send_stop_and_wait_message(char, short, transfer_info_t*, int, uint8_t*);

#endif