#ifndef UDP_SOCKETS_H
#define UDP_SOCKETS_H

#include <netdb.h>

// Maximum amount of data per UDP datagram
#define UDP_MSS 65535
typedef struct
{
    int length;
    uint8_t buffer[UDP_MSS];
} message_t;

typedef struct
{
    struct sockaddr_in addr;
    socklen_t addr_len;
    char friendly_ip[INET_ADDRSTRLEN];
} host_t;
                                                                                    
struct addrinfo* get_udp_sockaddr(const char*, const char*, int);
message_t* create_message();                                            
message_t* receive_message(int, host_t*);
int send_message(int, message_t*, host_t*);

#endif