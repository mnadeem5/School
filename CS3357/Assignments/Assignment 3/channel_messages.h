/******************************************************************************
* channel_messages.h contains all the constants for the messages.
******************************************************************************/

#ifndef CHANNEL_MSGS_H
#define CHANNEL_MSGS_H

#include "send_message.h"
#include "process_input.h"

/******************************************************************************
* Message types
******************************************************************************/
#define JOIN 	20
#define PART 	21
#define TOPIC 	22
#define LIST 	23

/******************************************************************************
* prototypes for different message options
******************************************************************************/
void send_channel_join_msg(int, char*, char*);
void send_channel_part_msg(int, char*, char*);
void send_channel_topic_msg(int, char*, char*);
void send_channel_list_msg(int, char*);
void send_channel_msg(short, int, char*, char*, char*);

#endif