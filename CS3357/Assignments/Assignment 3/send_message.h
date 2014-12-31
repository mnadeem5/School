#ifndef SEND_MESSAGES_H
#define SEND_MESSAGES_H

#include <stdio.h>
#include <stdlib.h>

#include <syslog.h>

#include <netdb.h>

#include "gui.h"
#include "connection_messages.h"
#include "channel_messages.h"
#include "messages.h"

#define TYPE 4
#define ASTERISK 1
#define SPACE 1
#define CRLF 1

void initDebug(WindowContext*);
void send_msg(int, char*, int);
int get_message_len(short, char*, char*, char*);

#endif