/******************************************************************************
 * server_stop_and_wait.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * server_stop_and_wait.c contains the helper functions for rftpd.c 
******************************************************************************/

#include "server_stop_and_wait.h"

/******************************************************************************
 * Receives the data messages and writes the contant to the file.
******************************************************************************/
void server_stop_and_wait_transfer(int sockfd, int timeout, FILE* file
    , int filesize)
{
	message_t* received;
    data_msg_t *data = (data_msg_t*)malloc(sizeof(data_msg_t));
    host_t source;
    int written = 0, snum = 0, i = 0;

    do
    {
        /**********************************************************************
        * generates the next sequence number
        **********************************************************************/
	    snum = (snum + 1) % 2;

        /**********************************************************************
        * receives the next message
        **********************************************************************/
	    received = receive(DATA, snum, sockfd, &source, NULL, data);
    
        /**********************************************************************
        * checks if the message received is termination message or not
        **********************************************************************/
        if (received->type != DATA)
            break;

        /**********************************************************************
        * writes the data to the file
        **********************************************************************/
		fwrite(data->data, data->datalen, 1, file);
		written += data->datalen;

        if (((double)written/filesize)*25 > i)
        {
            i++;    
            syslog(LOG_INFO, "Size: %d\tWritten: %d\tPercentage: %.2lf%%"
                , filesize, written, ((double)written/filesize)*100);
        }
        
        /**********************************************************************
        * frees pointers
        **********************************************************************/
        free(data->data);
        free(received);

	} while (1);

    syslog(LOG_INFO, "Size: %d\tWritten: %d\tPercentage: %.2lf%%", filesize
        , filesize, 100.00);
    
    free(data);
    free(received);
    terminate(source, sockfd, timeout);
}

/******************************************************************************
 * recieve messages helper
******************************************************************************/
message_t* receive(char type, short snum, int sockfd, host_t* source
    , ctrl_msg_t* ctrl, data_msg_t* data)
{
    char* feedback;
	message_t* received;
    int size;

    while(1)
    {
        /**********************************************************************
        * checks if any messages are recived
        **********************************************************************/
        if ((received = receive_message(sockfd, source, ctrl, data))
            != NULL)
        {
            if (type == DATA)
                size = data->datalen;
            else
                size = 0;

            feedback = print_info(RECE, received->type, received->snum, size);
            syslog(LOG_DEBUG, "%s", feedback);
            free(feedback);

            /******************************************************************
            * acks message
            ******************************************************************/
            send_ACK(sockfd, source, received);

            /******************************************************************
            * if the message is the next message we are waiting for or a
            * termination message, then break out of the loop. Else drop packet 
            ******************************************************************/
            if (received->snum == snum && (received->type == type ||
                (type == DATA && received->type == TERM)))
                break;
        }

        /**********************************************************************
        * drops unwanted packets
        **********************************************************************/
        if (type == INIT && received->type == INIT)
            free(ctrl->name);
        else if (type == DATA && received->type == DATA)
            free(data->data);
        free(received);
    }
    return received;
}