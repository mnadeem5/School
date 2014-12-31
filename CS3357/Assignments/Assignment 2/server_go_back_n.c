/******************************************************************************
 * server_go_back_n.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * server_go_back_n.c contains the helper functions for rftpd.c 
******************************************************************************/

#include "server_go_back_n.h"

/******************************************************************************
 * Receives the data messages and writes the contant to the file.
******************************************************************************/
void go_back_n_transfer(int sockfd, int timeout, int win_size, FILE* file
    , int file_size)
{
	message_t* received;
    data_msg_t *data;
    host_t source;
    char* feedback;
    int bufferlen = win_size*2, datalen, written = 0, multi_acks = 0;
    data_msg_t* buffer[bufferlen];
    short buffer_head, snum, i;

    while(1)
    {
        buffer_head = -1;
        for (i = 0; i < bufferlen; i++)
            buffer[i] = NULL;

        while(1)
        {
            /******************************************************************
            * receives the next message
            ******************************************************************/
            data = (data_msg_t*)malloc(sizeof(data_msg_t));
    	    received = receive_message(sockfd, &source, NULL, data);

            if (received->type == DATA)
            {
                snum = received->snum;

                /*************************************************************
                * gives the user feedback 
                **************************************************************/
                feedback = print_info(RECE, received->type, snum
                    , data->datalen);
                syslog(LOG_DEBUG, "%s", feedback);
                free(feedback);

                if (buffer[snum] == NULL)
                {
                    buffer[snum] = data;

                    /*********************************************************
                    * gives the user feedback 
                    **********************************************************/
                    while(buffer_head + 1 < bufferlen
                        && buffer[buffer_head + 1]!= NULL)
                    {
                        /*****************************************************
                        * writes data
                        ******************************************************/
                        buffer_head++;
                        data = buffer[buffer_head];
                        datalen = data->datalen;
                        fwrite(data->data, datalen, 1, file);

                        written += datalen;
                        multi_acks++;
                    }
                }
                else
                {   
                    if(buffer_head != -1)
                    {
                        received->snum = buffer_head;
                        send_ACK(sockfd, &source, received);
                    }
                    free(data->data);
                    free(data);
                }

                /*************************************************************
                * sends acks
                **************************************************************/
                if (buffer_head != -1)
                {
                    received->snum = buffer_head;
                    for (; multi_acks > 0; multi_acks--)
                        send_ACK(sockfd, &source, received);
                }

                free(received);

                if (buffer_head + 1 == bufferlen)
                    break;
            }
            else if (received->type == TERM && received->snum ==
                (buffer_head + 1) % bufferlen)
                break;
            else
            {
                send_ACK(sockfd, &source, received);
                free(data);
                free(received); 
            }
    	}

        /*********************************************************************
        * sends feedback
        **********************************************************************/
        syslog(LOG_INFO, "Size: %d\tWritten: %d\tPercentage: %.2lf%%"
            , file_size, written, ((double)written/file_size)*100);

        for (i = 0; i < bufferlen && buffer[i] != NULL; i++)
        {
            free(buffer[i]->data);
            free(buffer[i]);
        }
        
        if (received->type == TERM)
            break;
    }

    syslog(LOG_INFO, "Size: %d\tWrittenn: %d\tPercentage: 100%%\n"
        , file_size, file_size);

    feedback = print_info(RECE, received->type, received->snum, 0);
    syslog(LOG_DEBUG, "%s", feedback);
    free(feedback);

    send_ACK(sockfd, &source, received);
    free(data);
    free(received);

    terminate(source, sockfd, timeout);
}