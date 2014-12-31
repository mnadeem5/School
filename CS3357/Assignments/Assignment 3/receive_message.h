/******************************************************************************
* receive_message.h
******************************************************************************/

#ifndef RECEIVE_MESSAGE_H
#define RECEIVE_MESSAGE_H

#include <stdio.h>
#include <netdb.h>

#include <syslog.h>
#include <poll.h>

#include "ircc.h"
#include "gui.h"
#include "command_replies.h"
#include "error_replies.h"
#include "process_input.h"
#include "tokenize.h"

#define CODE 3
#define PING 40
#define ERROR 41

void *receive_message(void*);
void parse_messages(client_info_t*, char*);
void interpret_messages(client_info_t*, char*);
int get_message_code(char*);
char* get_tail_message(char*);
char* add_header(char*, char*);
int get_message_type(char*);
char* get_message_sender(char*);

#endif