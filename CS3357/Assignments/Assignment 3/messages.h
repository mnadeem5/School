	/******************************************************************************
* messages.h contains all the constants for the messages.
******************************************************************************/

#ifndef MESSAGES_H
#define MESSAGES_H

#include "send_message.h"

/******************************************************************************
* Message types
******************************************************************************/
#define PRIVMSG 30

/******************************************************************************
* prototypes for different message options
******************************************************************************/
void send_private_message(int, char*, char*);
void send_message(short, int, char*, char*);

#endif