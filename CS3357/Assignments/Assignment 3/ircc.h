/******************************************************************************
* ircc.h contains the important includes from ircc.c
******************************************************************************/

#ifndef IRCC_H
#define IRCC_H

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

#include <getopt.h>
#include <syslog.h>

#include <pthread.h>

#include "receive_message.h"
#include "send_message.h"
#include "connection_messages.h"
#include "channel_messages.h"
#include "tcp_sockets.h"
#include "gui.h"
#include "process_input.h"

#define PORT "6667"
#define DEBUG 1
#define NODEBUG 0

int processOptions(int, char**, int*);
void connect_to_server(int, char*);

#endif