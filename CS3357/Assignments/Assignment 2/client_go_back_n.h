/*****************************************************************************
 * client_go_back_n.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * client_go_back_n.c implemets the the go back n transfer protocol 
******************************************************************************/

#ifndef GOBACKN_H
#define GOBACKN_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <unistd.h>
#include <syslog.h>
#include <pthread.h>
#include <time.h>

#include "udp_sockets.h" 
#include "message.h"
#include "connect.h"

#define MAXLEN 1464

void go_back_n(transfer_info_t*);
void go_back_n_handshake(transfer_info_t*);
void go_back_n_transfer (transfer_info_t*);
void go_back_n_terminate(transfer_info_t*, int);

void *go_back_n_receive_thread(void*);
void *go_back_n_timer_thread(void*);

int next_data_buffer(data_msg_t**, char*, int, int);

#endif