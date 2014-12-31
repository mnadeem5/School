/******************************************************************************
* connection_messages.h contains all the constants for the messages and all 
* the constants for the mode types.
******************************************************************************/

#ifndef CONNECTION_MSGS_H
#define CONNECTION_MSGS_H

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include <netdb.h>

#include "send_message.h"

/******************************************************************************
* Message types
******************************************************************************/
#define NICK 10
#define USER 11
#define QUIT 12

/******************************************************************************
* prototypes for different message options
******************************************************************************/
void send_nick_msg(int, char*);
void send_user_msg(int, char*, char*, char*);
void send_quit_msg(int, char*);
void send_registration_msg(short, int, char*, char*, char*);

#endif