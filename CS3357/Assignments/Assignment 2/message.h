/*****************************************************************************
 * message.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * message.c contains the definitions and all the helper methods for the
 * control and data messages.
******************************************************************************/

#ifndef MESSAGE_H
#define MESSAGE_H

#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>

#define MMS 1472

#define INIT '1'
#define TERM '2'
#define DATA '3'

#define NAK '0'
#define ACK '1'

#define BIT8 1
#define BIT16 2
#define BIT32 4
#define BIT64 8

typedef struct
{
    uint8_t type;
    uint8_t ack;
    uint16_t snum;
} message_t;

typedef struct
{
    uint32_t file_size;
    uint32_t namelen;
    uint8_t* name;
} ctrl_msg_t;

typedef struct
{
    uint32_t datalen;
    uint8_t* data;
} data_msg_t;

message_t* create_message(uint8_t, uint8_t, uint16_t);
ctrl_msg_t* create_ctrl_msg(uint32_t, uint32_t, uint8_t*);
data_msg_t* create_data_msg(uint32_t, uint8_t*);
int msg_size(ctrl_msg_t*, data_msg_t*);
uint8_t* message_to_binary(message_t*, ctrl_msg_t*, data_msg_t*);
message_t* binary_to_message(uint8_t*, ctrl_msg_t*, data_msg_t*);

#endif