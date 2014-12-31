/*****************************************************************************
 * connect_server.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * connect_server.c is a file that contains helper methods that are used by 
 * rftpd.c files.
******************************************************************************/

#include "connect_server.h"

/*****************************************************************************
 * Binds a socket for the transfer use and calls the appropriate functions to 
 * complete the transfer.
******************************************************************************/
void connect_server(char *dir, int wait_time, char *port)
{
    int win_size;
    /*************************************************************************
     * Binds the socket.
    **************************************************************************/
    int sockfd = create_server_socket(port);
    /*************************************************************************
     * Recieves the initialization message and populates the fileinfo
     * structure with the file size, filename length, and filename.
    **************************************************************************/
    ctrl_msg_t *fileinfo = handshake(sockfd, &win_size);
    /*************************************************************************
     * Creates a file based on the information passed from fileinfo.
    **************************************************************************/
    FILE *file = makefile(dir, fileinfo);
    /*************************************************************************
     * Starts the transfer process. 
    **************************************************************************/
    if (win_size == 0)    
        server_stop_and_wait_transfer(sockfd, wait_time, file
            , fileinfo->file_size);
    else
        go_back_n_transfer(sockfd, wait_time, win_size, file
            , fileinfo->file_size);

    free(fileinfo->name);
    free(fileinfo);
    fclose(file);
    close(sockfd);
}

/******************************************************************************
 * waits for the intitilaization control message, receives it, and acks it.
 * It then return the file data to the server method in rftpd.c
******************************************************************************/
ctrl_msg_t* handshake(int sockfd, int* win_size)
{
    message_t* received;
    ctrl_msg_t* fileinfo = (ctrl_msg_t*)malloc(sizeof(ctrl_msg_t));
    host_t source;
    char* feedback;

    while(1)
    {
        /**********************************************************************
        * waits for a message
        **********************************************************************/
        received = receive_message(sockfd, &source, fileinfo, NULL);

        feedback = print_info(RECE, received->type, received->snum, 0);
        syslog(LOG_DEBUG, "%s", feedback);
        free(feedback);

        /**********************************************************************
        * acks message
        **********************************************************************/
        send_ACK(sockfd, &source, received);

        /**********************************************************************
        * if the message is an initialization message, then break out of the 
        * loop
        **********************************************************************/
        if (received->type == INIT)
            break;

        /**********************************************************************
        * drops unwanted message
        **********************************************************************/
        free(fileinfo->name);
        free(received);
    }
    *win_size = received->snum;
    free(received);
    return fileinfo;
}

/******************************************************************************
 * waits for any duplicate termination messages, and acks them
******************************************************************************/
void terminate(host_t source, int sockfd, int timeout)
{ 
    message_t* received;

    /**************************************************************************
     * waits for any duplicate termination messages
    **************************************************************************/
    while((received = receive_message_with_timer(sockfd, &source, timeout, NULL
        , NULL)) != NULL && received->type == TERM)
    {
        /**********************************************************************
        * ack messages
        **********************************************************************/
        received->ack = ACK;
        send_ACK(sockfd, &source, received);
        free(received);
    }
    free(received);
}

/******************************************************************************
 * send ack helper
******************************************************************************/
void send_ACK(int sockfd, host_t* source, message_t* msg)
{
    uint8_t* buffer;
    char* feedback;
    msg->ack = ACK;

    /**************************************************************************
     * sends ack
    **************************************************************************/
    if (send_message(sockfd, (buffer = message_to_binary(msg, NULL, NULL))
        , BIT32, source) == -1)
    {
        perror("Transfer failed, unable to send to socket.");
        close(sockfd);
        exit(EXIT_FAILURE);
    }
    /**************************************************************************
     * prints feedback and frees memory
    **************************************************************************/
    feedback = print_info(ACKS, msg->type, msg->snum, 0);
    syslog(LOG_DEBUG, "%s", feedback);
    free(feedback);
    free(buffer);
}

/******************************************************************************
 * opens/creates a file and returns the FILE stucture pointer.
******************************************************************************/
FILE* makefile(char *dir, ctrl_msg_t *fileinfo)
{
    char filename[(int)fileinfo->namelen];
    memcpy(filename, fileinfo->name, fileinfo->namelen);
    filename[fileinfo->namelen] = '\0';

    return fopen(strcat(dir, filename), "w");
}