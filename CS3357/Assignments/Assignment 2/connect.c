/*****************************************************************************
 * connect.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * connect.c is a file that contains helper methods that are shared between
 * rftp.c and rftpd.c files.
******************************************************************************/

#include "connect.h"

/******************************************************************************
 * Builds and returns a debug log message that cotains the information about
 * the delivered/received messages.
******************************************************************************/
char* print_info(char mode, uint8_t type, uint16_t snum, uint32_t size)
{
    char *msg_mode, *msg_type, *msg_info = (char*)malloc(1024);
    if (mode == ACKR)
        msg_mode = "ACK received:";
    else if (mode == ACKS)
        msg_mode = "ACK sent:\t\t";
    else if (mode == SENT)
        msg_mode = "Message sent:\t";
    else
        msg_mode = "Message received:\t";

    switch((char)type)
    {
        case INIT:
            msg_type = "message type: initialization message\t";
            break;
        case TERM:
            msg_type = "message type: terminatation message\t";
            break;
        case DATA:
            msg_type = "message type: data message\t\t";
            break;
    }
    
    if (mode == ACKR || mode == ACKS || type != DATA)
   		sprintf(msg_info, "%s%s, sequence number: %d\t\t\n", msg_mode, msg_type
            , snum);
   	else if (type == DATA)
    {
   		sprintf(msg_info, "%s%s, sequence number: %d\t\t, data size: %d\n"
            , msg_mode, msg_type, snum, size);
    }

    return msg_info;
}