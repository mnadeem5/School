/*****************************************************************************
 * message.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * message.c contains the definitions and all the helper methods for the
 * control and data messages.
******************************************************************************/

#include "message.h"

/*****************************************************************************
 * create_message generates a message_t structure that holds the message's, 
 * type, ack, and sequence number values.
******************************************************************************/
message_t* create_message(uint8_t type, uint8_t ack, uint16_t snum)
{
    message_t *msg = (message_t*)malloc(sizeof(message_t));   

    msg->type = type;
    msg->ack = ack;
    msg->snum = snum;    

    return msg;
}

/*****************************************************************************
 * create_ctrl_msg generates a ctrl_msg_t structure that holds the control
 * message's file size, filename length, and filename.
******************************************************************************/
ctrl_msg_t* create_ctrl_msg(uint32_t file_size, uint32_t namelen
    , uint8_t *name)
{
    ctrl_msg_t *ctrl_msg =  (ctrl_msg_t*)malloc(sizeof(ctrl_msg_t));

    ctrl_msg->file_size = file_size;
    ctrl_msg->namelen = namelen;
    ctrl_msg->name = (uint8_t*)malloc(namelen);
    memcpy(ctrl_msg->name, name, namelen);

    return ctrl_msg;
}

/*****************************************************************************
 * create_data_msg generates a data_msg_t structure that holds the data
 * message's data length, and data chunk.
******************************************************************************/
data_msg_t* create_data_msg(uint32_t datalen, uint8_t *data)
{
    data_msg_t *data_msg =  (data_msg_t*)malloc(sizeof(data_msg_t)); 

    data_msg->datalen = datalen;
    data_msg->data = (uint8_t*)malloc(datalen);
    memcpy(data_msg->data, data, datalen);

    return data_msg;                                   
}

/*****************************************************************************
 * msg_size returns the byte size of any message structure, if a massage does 
 * not have control or data messages attacked to it, then the size of that
 * message is set to 4 bytes in default.
******************************************************************************/
int msg_size(ctrl_msg_t* ctrl_msg, data_msg_t* data_msg)
{
    if (ctrl_msg != NULL)
        return BIT32 + BIT64 + ctrl_msg->namelen;

    if (data_msg != NULL)
        return BIT32 + BIT32 + data_msg->datalen;

    return BIT32;
}

/*****************************************************************************
 * message_to_binary translates messages to binary bytes to prepare them to 
 * be sent over the network. During this proccess, message_to_binary also 
 * translates all the 2n-byte, where n is either 1 or 2, objects to big endian
 * binary notation. 
******************************************************************************/
uint8_t* message_to_binary(message_t *msg, ctrl_msg_t *ctrl_msg
    , data_msg_t *data_msg)
{
	uint16_t snum;
	uint32_t file_size, namelen, datalen;

    /**************************************************************************
    * Copies the message's type and ack fields values to the first two slots
    * of the datagram byte array.
    **************************************************************************/
    uint8_t *datagram = (uint8_t*)malloc(msg_size(ctrl_msg, data_msg));
    memcpy(datagram, msg, BIT16);

    /**************************************************************************
    * Translates the sequence number into big endian notation and inserts it
    * in the second two bytes of the array.
    **************************************************************************/
    snum = htons(msg->snum);
    memcpy(datagram + BIT16, &snum, BIT16);
    
    if(ctrl_msg != NULL || data_msg != NULL)
    {
        switch((char)msg->type)
        {   
            case INIT:
                /**************************************************************
                * Translates the file size into big endian notaion and inserts
                * it into the 5-8 byte slots in the datagram byte array.
                **************************************************************/
                file_size = htonl(ctrl_msg->file_size);
                memcpy(datagram + BIT32, &file_size, BIT32);

                /**************************************************************
                * Translates the filename length into big endian notaion and 
                * inserts it into the 9-12 byte slots in the datagram byte
                * array.
                **************************************************************/
                namelen = ntohl(ctrl_msg->namelen);
                memcpy(datagram + BIT64, &namelen, BIT32);

                /**************************************************************
                * Copies the filename into the 13-name-length bytes in the
                * datagram byte array.
                **************************************************************/
                memcpy(datagram + BIT32 + BIT64, ctrl_msg->name
                    , ctrl_msg->namelen);
                break;
            case DATA:
                /**************************************************************
                * Translates the data length into big endian notaion and
                * inserts it into the 5-8 byte slots in the datagram byte array
                **************************************************************/
                datalen = htonl(data_msg->datalen);
                memcpy(datagram + BIT32, &datalen, BIT32);

                /**************************************************************
                * Copies the data into the 9-data-length bytes in the datagram 
                * byte array.
                **************************************************************/
                memcpy(datagram + BIT32 + BIT32, data_msg->data
                    , data_msg->datalen);
                break;
        }
    }
    return datagram;
}

/*****************************************************************************
 * binary_to_message extracts and regenerates the messages from the byte array
 * buffer. While extracting the data the method also translates the endianess
 * of the 2n-byte objects and transforms them into host endianess.
******************************************************************************/
message_t* binary_to_message(uint8_t *buffer, ctrl_msg_t *ctrl_msg
    , data_msg_t *data_msg)
{
    int ptr = 0;
    uint8_t type, ack;
    uint16_t snum;

    /**************************************************************************
    * Extracts the message's type and ack variables from the frist two bytes of
    * the buffer.
    **************************************************************************/
    type = buffer[ptr++];
    ack = buffer[ptr++];

    /**************************************************************************
    * Extracts the sequence number from the second two bytes of the buffer
    * array.
    **************************************************************************/
    memcpy(&snum, buffer + ptr, BIT16);
    ptr += BIT16;

    if (ctrl_msg != NULL || data_msg != NULL) 
    {
        if (type == DATA)
        {
            uint32_t datalen;

            /******************************************************************
            * Extracts the data length from the 5-8 bytes of the buffer array 
            * and translates its endianess to the machine's endianess.
            ******************************************************************/
            memcpy(&datalen, buffer + ptr, BIT32);
            data_msg->datalen = ntohl(datalen);
            ptr += BIT32;

            /******************************************************************
            * Extracts the data from the buffer, using the data length.
            ******************************************************************/
            data_msg->data = (uint8_t*)malloc(data_msg->datalen);
            memcpy(data_msg->data, buffer + ptr, data_msg->datalen);
        }
        else if (type == INIT)
        {
            uint32_t file_size, namelen;

            /******************************************************************
            * Extracts the file size from the 5-9 bytes of the buffer. Changes 
            * object endianess to host endianess.
            ******************************************************************/
            memcpy(&file_size, buffer + ptr, BIT32);
            ctrl_msg->file_size = ntohl(file_size);
            ptr += BIT32;

            /******************************************************************
            * Extracts the filename length from the 9-12 bytes in the buffer 
            * and translates the bytes to host endianess
            ******************************************************************/
            memcpy(&namelen, buffer + ptr, BIT32);
            ctrl_msg->namelen = ntohl(namelen);
            ptr += BIT32;

            /******************************************************************
            * Extracts the filename from the buffer, using the filename length.
            ******************************************************************/
            ctrl_msg->name = (uint8_t*)malloc(ctrl_msg->namelen);
            memcpy(ctrl_msg->name, buffer + ptr, ctrl_msg->namelen);
        }
    }
    /**************************************************************************
    * Returns the generated message with help from the create_message method.
    **************************************************************************/
    return create_message(type, ack, ntohs(snum));
}