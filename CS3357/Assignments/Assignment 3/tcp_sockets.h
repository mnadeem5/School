#ifndef TCP_SOCKETS
#define TCP_SOCKETS

#include <stdio.h>
#include <string.h>
#include <netdb.h>

struct addrinfo* get_sockaddr(const char*, const char*);
int open_connection(const char*, const char*);

#endif