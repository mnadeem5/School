#ifndef PROCESS_INPUT_H
#define PROCESS_INPUT_H

#include <string.h>
#include <stdlib.h>

#include "gui.h"
#include "connection_messages.h"
#include "channel_messages.h"
#include "tokenize.h"

#define EXIT_STATUS 1
#define NO_EXIT_STATUS 0

#define MAX_PARAMS 4

#define NICK_PARAMS 1 
#define QUIT_PARAMS 1

#define JOIN_PARAMS 1
#define PART_PARAMS 1
#define NAMES_PARAMS 1
#define LIST_PARAMS 1
#define TOPIC_PARAMS 1

typedef struct 
{
	int sockfd;
	WindowContext* wc;
	char* nick;
	char* channel;
	int* terminate_thread;
	pthread_t* receive_thread;
} client_info_t;

int process_input(client_info_t*, char*);
int tokenize(char*, char**);

void processNickCommand		(int, WindowContext*, char**, int);
int processQuitCommand		(client_info_t*, char*, int);
void processJoinCommand		(client_info_t*, char**, int);
void processPartCommand		(client_info_t*, char**, char*, int);
void processListCommand		(int, WindowContext*, char**, int);
void processTopicCommand	(client_info_t*, char**, char*, int);
void processRegularMessage	(client_info_t*, char*);
void processHelpCommand		(WindowContext*);

#endif