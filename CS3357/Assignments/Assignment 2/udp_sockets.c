/******************************************************************************
 * udp_sockets.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * udp_sockets.c recieves and sends binary messages
******************************************************************************/

#include "udp_sockets.h"

struct addrinfo* get_udp_sockaddr(const char* node, const char* port
    , int flags)
{
    struct addrinfo hints;
    struct addrinfo* results;
    int retval;

    memset(&hints, 0, sizeof(struct addrinfo));

    hints.ai_family = AF_INET;
    hints.ai_socktype = SOCK_DGRAM;                                 
    hints.ai_flags = flags;                  
                         
    retval = getaddrinfo(node, port, &hints, &results);

    if (retval != 0)
        exit(EXIT_FAILURE);

    return results;
}
 
/******************************************************************************
 * receive_message is a helper to recieve messages with timer
******************************************************************************/                                         
message_t* receive_message_with_timer(int sockfd, host_t* source, int timeout
    , ctrl_msg_t* ctrl, data_msg_t* data)
{
    struct pollfd fd = {
        .fd = sockfd,
        .events = POLLIN
    };

   	if(poll(&fd, 1, timeout) == 1 && fd.revents == POLLIN)
        return receive_message(sockfd, source, ctrl, data);
    return NULL;
}

/******************************************************************************
 * receive_message is a helper to recieve messages
******************************************************************************/  
message_t* receive_message(int sockfd, host_t* source, ctrl_msg_t* ctrl,
    data_msg_t* data)
{
    uint8_t *buffer = (uint8_t*)malloc(MMS);
    source->addr_len = sizeof(source->addr);
    message_t* msg = NULL;

    recvfrom(sockfd, buffer, MMS, 0, (struct sockaddr*)&source->addr
        , &source->addr_len);

    /**************************************************************************
     * generates messages based of the type of the message
    **************************************************************************/   
    switch((char)buffer[0])
    {
        case INIT:
            msg = binary_to_message(buffer, ctrl, NULL);
            break;
        case TERM:
            msg = binary_to_message(buffer, NULL, NULL);
            break;
        case DATA:
            msg = binary_to_message(buffer, NULL, data);
            break;
    }

    free(buffer);

    if (msg != NULL)
    {
        inet_ntop(source->addr.sin_family, &source->addr.sin_addr
            , source->friendly_ip, sizeof(source->friendly_ip));                  
        return msg;                                                               
    }
    return NULL;
}

/******************************************************************************
 * send_message sends messages
******************************************************************************/   
int send_message(int sockfd, uint8_t* msg, int size, host_t* dest)
{
    return sendto(sockfd, msg, size, 0, (struct sockaddr*)&dest->addr
        , dest->addr_len);
}