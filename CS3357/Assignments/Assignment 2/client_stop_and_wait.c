/******************************************************************************
 * client_stop_and_wait.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * client_stop_and_wait.c implements the stop and wait protocol to send packets to the
 * server.
******************************************************************************/

#include "client_stop_and_wait.h"

/******************************************************************************
 * sets up the syslog mask values and starts the transfer operation
******************************************************************************/
void stop_and_wait(transfer_info_t* info)
{
    /**************************************************************************
     * opens a syslog and sets its mask to LOG_INFO
    **************************************************************************/
    openlog("stop_and_wait", LOG_PERROR | LOG_PID | LOG_NDELAY, LOG_USER);
    setlogmask(LOG_UPTO(LOG_INFO));

    /**************************************************************************
     * sets the syslog mask to LOG_DEBUG
    **************************************************************************/
    if (info->verbose)
    {
        setlogmask(LOG_UPTO(LOG_DEBUG));
        syslog(LOG_DEBUG, "Entered DEBUG MODE\n");
    }
	handshake(info);
}

/******************************************************************************
 * sends the data init message data
******************************************************************************/
void handshake(transfer_info_t* info)
{
	send_stop_and_wait_message(INIT, 0, info, 0, NULL);
    transfer(info);
}

/******************************************************************************
 * transmites the data packets
******************************************************************************/
void transfer(transfer_info_t *info)
{
	FILE *file;
	uint8_t* filename = info->name;

    /**************************************************************************
     * opens the specified file
    **************************************************************************/
    if (!(file = fopen((char*)filename, "r"))) 
    { 
        printf("Stop and Wait transfer failed, Can't open %s\n", filename); 
        close(info->sockfd);
        exit(EXIT_FAILURE);
    }

    uint8_t segment [MAXLEN];
    int seg_size, transferred = 0, filesize = file_size(filename), snum = 0
        , i = 1;

    /**************************************************************************
     * iterates through the file and transmits the data packets
    **************************************************************************/
    while ((seg_size = fread(segment, 1, MAXLEN, file)))
    {
        /**********************************************************************
         * generates and sends a data packet
        **********************************************************************/
    	send_stop_and_wait_message(DATA, ++snum, info, seg_size, segment);
    	transferred += seg_size;

        /**********************************************************************
         * prints transfer status every 4%
        **********************************************************************/
        if (((double)transferred/filesize)*25 > i)
        {
            i++;    
            syslog(LOG_INFO, "Size: %d\tTransferred: %d\tPercentage: %.2lf%%"
                , filesize, transferred, ((double)transferred/filesize)*100);
        }
	}
    syslog(LOG_INFO, "Size: %d\tTransferred: %d\tPercentage: %.2lf%%"
        , filesize, filesize, 100.00);
	fclose(file);
	terminate(info, snum);
}

/******************************************************************************
 * sends the terminatation message
******************************************************************************/
void terminate(transfer_info_t* info, int snum)
{
    send_stop_and_wait_message(TERM, snum + 1, info, 0, NULL);
    close(info->sockfd);
}

/******************************************************************************
 * generates, transmits and retransmits all messages. keeps retransmitting
 * until an ack is recieved.
******************************************************************************/
void send_stop_and_wait_message(char type, short snum, transfer_info_t* info
    , int seg_size, uint8_t* segment)
{
    /**************************************************************************
     * determines the next sequence number
    **************************************************************************/
	snum = snum % info->win_size;

    /**************************************************************************
     * creates and initailizes variables
    **************************************************************************/
	message_t* msg = create_message(type, NAK, snum), *received = NULL;
	ctrl_msg_t* ctrl = NULL;
	data_msg_t* data = NULL;
    uint8_t* buffer = NULL;
    int size;
    char* feedback;

	int sockfd = info->sockfd;
    host_t* server = info->server;

    /**************************************************************************
     * generates a control message.
    **************************************************************************/
    if (type == INIT)
    {
    	ctrl = create_ctrl_msg(info->file_size, strlen((char*)info->name)
            , info->name);
        size = 0;
    }
    /**************************************************************************
     * generates a data message
    **************************************************************************/
    else if (type == DATA)
    {
		data = create_data_msg(seg_size, segment);
        size = data->datalen;
    }

    /**************************************************************************
     * casts the data message into a byte array
    **************************************************************************/
    buffer = message_to_binary(msg, ctrl, data);
    
	do
    {
        /**********************************************************************
         * prints message details
        **********************************************************************/
        feedback = print_info(SENT, msg->type, msg->snum, size);
        syslog(LOG_DEBUG, "%s", feedback);
        free(feedback);

        /**********************************************************************
         * sends message
        **********************************************************************/
        if (send_message(sockfd, buffer, msg_size(ctrl, data), server) == -1)
        {
            perror("Stop and Wait transfer failed, unable to send to socket.");
            exit(EXIT_FAILURE);
        }

        /**********************************************************************
         * loops until and ack is recieved
        **********************************************************************/
        if ((received = receive_message_with_timer(sockfd, info->server, info->timeout
            , NULL, NULL)) != NULL && received->type == type
            && received->ack == ACK && snum == received->snum)
                break;

        free(received);
    } while(1);


    /**************************************************************************
     * prints message details
    **************************************************************************/
    feedback = print_info(RECE, msg->type, msg->snum, BIT32);
    syslog(LOG_DEBUG, "%s", feedback);
    free(feedback);


    /**************************************************************************
     * frees memory 
    **************************************************************************/
    if (type == INIT)
    {
        free(ctrl->name);
        free(ctrl);
    }
    else if (type == DATA)
    {
        free(data->data);
        free(data);
    }

    free(msg);
    free(buffer);
    free(received);
}