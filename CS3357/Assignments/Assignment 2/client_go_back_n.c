/*****************************************************************************
 * client_go_back_n.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * client_go_back_n.c implemets the the go back n transfer protocol 
******************************************************************************/

#include "client_go_back_n.h"

void go_back_n(transfer_info_t* info)
{
    openlog("go_back_n", LOG_PERROR | LOG_PID | LOG_NDELAY, LOG_USER);
    setlogmask(LOG_UPTO(LOG_INFO));

    if (info->verbose)
    {
        setlogmask(LOG_UPTO(LOG_DEBUG));
        syslog(LOG_DEBUG, "Entered DEBUG MODE\n");
    }
	go_back_n_handshake(info);
}

/*****************************************************************************
 * Creates a handshake control message and retransmites it if it times out
******************************************************************************/
void go_back_n_handshake(transfer_info_t* info)
{
	int sockfd = info->sockfd, timeout = info->timeout, snum = info->win_size;
	host_t* server = info->server;

	/*************************************************************************
	 * Creates the message
	**************************************************************************/
	message_t* msg = create_message(INIT, NAK, snum), *received;
	ctrl_msg_t* ctrl = create_ctrl_msg(info->file_size
		, strlen((char*)info->name), info->name);

	uint8_t* buffer = message_to_binary(msg, ctrl, NULL);

	char* feedback;

	/*************************************************************************
	 * Loops until an ACK is recieved
	**************************************************************************/
	while(1)
	{
		/*********************************************************************
		 * Notifies the user that the message has been sent
		**********************************************************************/
		feedback = print_info(SENT, msg->type, msg->snum, 0);
        syslog(LOG_DEBUG, "%s", feedback);
        free(feedback);

		if (send_message(sockfd, buffer, msg_size(ctrl, NULL), server) == -1)
        {
            perror("Transfer failed, unable to send to socket.");
            close(sockfd);
            exit(EXIT_FAILURE);
        }
		/*********************************************************************
		 * Waits for a replay
		**********************************************************************/
        if ((received = receive_message_with_timer(sockfd, server, timeout
        	, NULL, NULL)) != NULL)
        {
        	feedback = print_info(ACKR, received->type, received->snum, 0);
	        syslog(LOG_DEBUG, "%s", feedback);
	        free(feedback);

	        if (received->type == INIT && received->ack == ACK
	        	&& received->snum == snum)
        	break;
        }

        free(received);	
	}

	free(msg);
	free(received);
	free(ctrl->name);
	free(ctrl);
	free(buffer);

	go_back_n_transfer(info);
}

/*****************************************************************************
 * defines mutexs for the threads 
******************************************************************************/
pthread_mutex_t sent_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t ack_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t wait_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t wait_cond = PTHREAD_COND_INITIALIZER;

int msgs_sent, acks_received;

/*****************************************************************************
 * transfers the data
******************************************************************************/
void go_back_n_transfer(transfer_info_t* info)
{
	int sockfd = info->sockfd, timeout = info->timeout;
	int seek = 0, data_chunks_buffered = 0;
	int win_size = info->win_size, sent_msg_size, i, transferred = 0;
	host_t* server = info->server;
	data_msg_t* data_buffer[win_size*2];
	message_t *msg;
	data_msg_t* data;
	uint8_t* buffer;
	char* feedback;

	pthread_t receive_thread, timer_thread;

	/*************************************************************************
	 * while there is data to be sent
	**************************************************************************/
	while(1)
	{
		/*********************************************************************
		 * populates the data_chunks_buffered with a chunck of data
		**********************************************************************/
		seek += (data_chunks_buffered = next_data_buffer(data_buffer
			, (char*)info->name, win_size*2, seek));

		if (data_chunks_buffered == 0)
			break;

		msgs_sent = 0; 
		acks_received = 0; 

		/*********************************************************************
		 * Starts the pthreads, timer and message listener
		**********************************************************************/
		pthread_create(&receive_thread, NULL, go_back_n_receive_thread
			, (void*)info);
		pthread_create(&timer_thread, NULL, go_back_n_timer_thread
			, (void*)&timeout);
		
		/*********************************************************************
		 * Loops until all the messages that are in the buffer are sent
		**********************************************************************/
		while(acks_received < data_chunks_buffered)
		{
			/*****************************************************************
			 * locks the msgs_sent, acks_received and varibales from being 
			 * changed by the timer, and message reciever threads.
			******************************************************************/
			pthread_mutex_lock(&sent_mutex);
			pthread_mutex_lock(&ack_mutex);
			/*****************************************************************
			 * Makes sure that a massage is sent if there is an avaibale spot
			 * for it in the transfer window.
			******************************************************************/			
			while(msgs_sent - acks_received < win_size && msgs_sent
				< data_chunks_buffered)
			{
				msg = create_message(DATA, NAK, msgs_sent);
				data = data_buffer[msgs_sent];
				sent_msg_size = msg_size(NULL, data);
				buffer = message_to_binary(msg, NULL, data);

				/*************************************************************
				 * Notifies the user that the message has been sent
				**************************************************************/
				feedback = print_info(SENT, msg->type, msg->snum
					, sent_msg_size - BIT64);
		        syslog(LOG_DEBUG, "%s", feedback);
		        free(feedback);
				/*************************************************************
				 * Sends the message
				**************************************************************/
				if (send_message(sockfd, buffer, sent_msg_size, server) == -1)
		        {
		            perror("Transfer failed, unable to send to socket.");
		            close(sockfd);
		            exit(EXIT_FAILURE);
		        }

		        free(msg);
		        free(buffer);
		        msgs_sent++;
			}
			/*****************************************************************
			 * unlocks the msgs_sent, acks_received and varibales.
			******************************************************************/
			pthread_mutex_unlock(&sent_mutex);
			pthread_mutex_unlock(&ack_mutex);

			/*****************************************************************
			 * waits for a single from either the timer, or the receive
			 * threads. If the timer times out then start again from the last
			 * unacked message, if an ack is recieved then send the next
			 * message
			******************************************************************/			
		    pthread_mutex_lock(&wait_mutex);
		    pthread_cond_wait(&wait_cond, &wait_mutex);
		    pthread_mutex_unlock(&wait_mutex);
		}
		/*****************************************************************
		 * frees memory
		******************************************************************/		
        for(i = 0; i < acks_received - 1; i++)
        {
        	transferred += data_buffer[i]->datalen;

            free(data_buffer[i]->data);
            free(data_buffer[i]);
        }

		/*****************************************************************
		 * status update
		******************************************************************/	
        syslog(LOG_INFO, "Size: %d\tTransferred: %d\tPercentage: %.2lf%%"
        	, info->file_size, transferred
        	, ((double)transferred/info->file_size)*100);

		/*****************************************************************
		 * kills the threads because there is no other way to stop their
		 * infinity loops
		******************************************************************/	
		pthread_cancel(receive_thread);
		pthread_cancel(timer_thread);
	}

	syslog(LOG_INFO, "Size: %d\tTransferred: %d\tPercentage: 100%%\n"
        , info->file_size, info->file_size);

	go_back_n_terminate(info, acks_received % (win_size*2));
}

/*****************************************************************************
 * sends the termination message
******************************************************************************/
void go_back_n_terminate(transfer_info_t* info, int snum)
{
	int sockfd = info->sockfd, timeout = info->timeout;
	host_t* server = info->server;

	message_t* msg = create_message(TERM, NAK, snum), *received;
	uint8_t* buffer = message_to_binary(msg, NULL, NULL);

	char* feedback;

	while(1)
	{
		feedback = print_info(SENT, msg->type, msg->snum, 0);
        syslog(LOG_DEBUG, "%s", feedback);
        free(feedback);

		if (send_message(sockfd, buffer, msg_size(NULL, NULL), server) == -1)
        {
            perror("Transfer failed, unable to send to socket.");
            close(sockfd);
            exit(EXIT_FAILURE);
        }

        if ((received = receive_message_with_timer(sockfd, server, timeout
        	, NULL, NULL)) != NULL)
        {
        	feedback = print_info(ACKR, received->type, received->snum, 0);
	        syslog(LOG_DEBUG, "%s", feedback);
	        free(feedback);

	        if (received->type == TERM && received->ack == ACK
	        	&& received->snum == snum)
	        	break;
        }
        free(received);
	}

	free(msg);
	free(received);
	free(buffer);
}


/*****************************************************************************
 * timer thread
******************************************************************************/
void *go_back_n_timer_thread(void* time)
{
	int timeout = *((int*)time);
	clock_t start;
	while(1)
	{

		/*********************************************************************
		 * starts a new timer
		**********************************************************************/
		start = clock();
		while(timeout > ((clock() - start) * 1000 / CLOCKS_PER_SEC)){}

		/*********************************************************************
		 * locks the msgs_sent variable from being modified by any other
		 * thread, sends a signle to the transfer method that timer is out
		**********************************************************************/
		pthread_mutex_lock(&sent_mutex);
		msgs_sent = acks_received;
		pthread_cond_signal(&wait_cond);
		pthread_mutex_unlock(&sent_mutex);
	}
	pthread_exit(NULL);
}

/*****************************************************************************
 * message recieve thread
******************************************************************************/
void *go_back_n_receive_thread(void* info)
{
	int sockfd = ((transfer_info_t*)info)->sockfd, snum;
	host_t* server = ((transfer_info_t*)info)->server;
	message_t *received;
	char* feedback;

	while(1)
	{
		received = receive_message(sockfd, server, NULL, NULL);
		pthread_cond_signal(&wait_cond);

		/*********************************************************************
		 * If a message is recieved then single the transfer method
		**********************************************************************/
		if (received->ack == ACK)
		{
	    	snum = received->snum;
	    	pthread_mutex_lock(&ack_mutex);
	    	if (snum >= acks_received)
	    		acks_received = snum + 1;
	    	pthread_cond_signal(&wait_cond);
	    	pthread_mutex_unlock(&ack_mutex);

	    	feedback = print_info(ACKR, received->type, snum, 0);
	        syslog(LOG_DEBUG, "%s", feedback);
	        free(feedback);
	    }
        free(received);
	}
    pthread_exit(NULL);
}

/*****************************************************************************
 * buffers the next data chunck
******************************************************************************/
int next_data_buffer(data_msg_t** buffer, char* filename, int bufferlen
	, int seek)
{
	FILE *file = fopen(filename, "r");

    if (!file) 
    { 
        perror("data_buffer: Can't open file\n"); 
        exit(EXIT_FAILURE);
    }

    fseek(file, seek*MAXLEN, SEEK_SET);

    int i, data_size;
    uint8_t segment[MAXLEN];

    for(i = 0; i < acks_received; i++)
        buffer[i] = NULL;

    for (i = 0; (i < bufferlen) && (data_size = fread(segment, 1, MAXLEN
    	, file)); i++)
    	buffer[i] = create_data_msg(data_size, segment);
    
    fclose(file);
    return i;
}