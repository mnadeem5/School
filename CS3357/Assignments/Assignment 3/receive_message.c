/******************************************************************************
* receive_message.c contains the a methood that will be running in the
* background to recevice messages.
******************************************************************************/

#include "receive_message.h"

void *receive_message(void *arg)
{
	/**************************************************************************
	* buffer, will hold the value of the received message
	* sockfd, will hold the id of the receiving socket
	**************************************************************************/
	client_info_t* client_info = (client_info_t*)arg;
	char buffer [4096];
	int sockfd = client_info->sockfd, term;
	int* terminate_thread = client_info->terminate_thread;

	struct pollfd fd = {
        .fd = sockfd,
        .events = POLLIN
    };

	/**************************************************************************
	* this loop will keep iterating and waiting for messages to be received
	**************************************************************************/
	while(1)
	{
		/**********************************************************************
		* blocking call, waits for a message to be recieved, when a message
		* is received, terminate its end with '\0' and parse and disply the 
		* message
		**********************************************************************/
   		if(poll(&fd, 1, 1) == 1 && fd.revents == POLLIN)
   		{
			term = 	recv(sockfd, buffer, sizeof(buffer), 0);
			buffer[term] = '\0';
			parse_messages(client_info, buffer);
   		}

		if (*terminate_thread == 1) break;
	}
	pthread_exit(NULL);
}

/******************************************************************************
* breaks biggie backed messages into separate ones
******************************************************************************/
void parse_messages(client_info_t* client_info, char* buffer)
{
	int i = 0, j = 0;
	char* msg;

	while (i < strlen(buffer))
	{
		if(buffer[i] == '\n')
		{
			msg = (char*)malloc(i - j + 2);
			memcpy(msg, buffer + j, i - j + 1);
			msg[i - j + 1] = '\0';
			interpret_messages(client_info, msg);
			free(msg);
			j = i + 1;
		}
		i++;
	}
}

/******************************************************************************
* interpret messages and prints the servers responses to the user
******************************************************************************/
void interpret_messages(client_info_t* client_info, char* msg)
{
	WindowContext* wc = client_info->wc;

	/**************************************************************************
	* gets rid of the /r/n at the end of each message
	**************************************************************************/
	msg[strlen(msg) - 2] = '\0';

	/**************************************************************************
	* different strings that will help parse the recieved messages
	**************************************************************************/
	char *message, *formatted_message, *nick, *sender, *channel
		, debugMess[4096];

	/**************************************************************************
	* recieved message code
	**************************************************************************/
	int code = get_message_code(msg);

	/**************************************************************************
	* formates the message and prints it in the debug panel
	**************************************************************************/
	sprintf(debugMess, "Message recieved (%d) - %s", code, msg);
	printDebugMessage(wc, debugMess);

	switch(code)
	{
		/**********************************************************************
		* only prints the tail messages provided with the recieved messages
		* and prints them as server messages
		**********************************************************************/
		case RPL_CONNECTIONPROCESS:
		case RPL_WELCOME:
		case RPL_YOURHOST:
		case RPL_CREATED:
		case RPL_LUSERCLIENT:
		case RPL_LUSERME:
		case RPL_LUSER:
		case RPL_GUSER:
		case RPL_LIST:
			printServerMessage(wc, get_tail_message(msg));
			break;

		/**********************************************************************
		* only prints the tail messages provided with the recieved messages
		* and prints them as error messages
		**********************************************************************/
		case ERR_NOSUCHCHANNEL:
		case ERR_CANNOTSENDTOCHAN:	
		case ERR_ERRONEUSNICKNAME:
			printErrorMessage(wc, get_tail_message(msg));
			break;

		/**********************************************************************
		* prints response to any actions that the user tried to perform but
		* needs to be on a channel to perform, prints this as a warnnign
		* message
		**********************************************************************/
		case ERR_NOTONCHANNEL:
		case ERR_NOSUCHNICK:
			printWarningMessage(wc, "Please register in a channel to perform"
				" the required action.");
			break;

		/**********************************************************************
		* Nick name is already in use warnning message
		**********************************************************************/
		case ERR_NICKNAMEINUSE:
			printWarningMessage(wc, "Nick name already in use");
			break;

		/**********************************************************************
		* server does not exists warning message
		**********************************************************************/
		case ERR_NOSUCHSERVER:
			printWarningMessage(wc, get_tail_message(msg));
			break;

		/**********************************************************************
		* populates users when it gets a NAMES message
		**********************************************************************/
		case RPL_NAMREPLY:
			populateUsers(wc, get_tail_message(msg));
			break;

		/**********************************************************************
		* changes the topic when a TOPIC message is recieved
		**********************************************************************/
		case RPL_TOPIC:
			updateTopic(wc, get_tail_message(msg));
			break;

		/**********************************************************************
		* when a no-code response message is recieved
		**********************************************************************/
		case NOCODE:
			/******************************************************************
			* swtiches bases on the message type
			******************************************************************/
			switch(get_message_type(msg))
			{
				/**************************************************************
				* updates the nickname info and prints the nickname change
				* onto the users scren
				**************************************************************/
				case NICK:
					sender = get_message_sender(msg);
					nick = get_tail_message(msg);

					printNickChangeMessage(wc, sender, nick);

					if (!strcmp(client_info->nick, sender))
					{
						free(client_info->nick);
						client_info->nick = (char*)malloc(strlen(nick));
						strcpy(client_info->nick, nick);
					}
					free(sender);
					break;

				/**************************************************************
				* prints that a user has joined the channel and updates the 
				* user info and panels
				**************************************************************/
				case JOIN:
					sender = get_message_sender(msg);

					if (!strcmp(sender, client_info->nick))
					{
						channel = get_tail_message(msg);
						updateChannel(wc, channel);

						if (strcmp(client_info->channel, "none"))
							free(client_info->channel);

						client_info->channel = (char*)malloc(strlen(channel));
						strcpy(client_info->channel, channel);
					}
					else
						printEnterMessage(wc, sender);

					free(sender);
					break;

				/**************************************************************
				* updates the user that a user has left the channel, and
				* the panels
				**************************************************************/
				case PART:	
					sender = get_message_sender(msg);

					if (!strcmp(sender, client_info->nick))
					{
						populateUsers(wc, "");
						updateChannel(wc, "None");
						updateTopic(wc, "No Current Topic");
						free(client_info->channel);
						client_info->channel = "none";
					}

					printLeaveMessage(wc, sender, get_tail_message(msg));
					free(sender);
					break;

				/**************************************************************
				* informs the user that the topic has changed
				**************************************************************/
				case TOPIC:
					message = add_header(client_info->channel
						, " topic changed to: ");

					formatted_message = add_header(message
						, get_tail_message(msg));

					printServerMessage(wc, formatted_message);
					updateTopic(wc, get_tail_message(msg));
					free(formatted_message);
					free(message);
					break;

				/**************************************************************
				* prints the messages recieved
				**************************************************************/
				case PRIVMSG:
					formatted_message = get_message_sender(msg);
					printMessage(wc, get_tail_message(msg), formatted_message);
					free(formatted_message);
					break;

				/**************************************************************
				* informs the user that they have been idel
				**************************************************************/
				case PING:
					printWarningMessage(wc, "WARNING! You're idle. You are"
						" about to timeout!");
					break;

				/**************************************************************
				* prints a user QUIT message
				**************************************************************/
				case QUIT:
					sender = get_message_sender(msg);
					printLeaveMessage(wc, sender, get_tail_message(msg));
					free(sender);
					break;

				/**************************************************************
				* prints a an ERROR message and exists the program
				**************************************************************/
				case ERROR:
					printErrorMessage(wc, "Connection lost.");
						
					*client_info->terminate_thread = 1;
					pthread_join(*client_info->receive_thread, NULL);
					free(client_info->terminate_thread);

					free(client_info->nick);

				    if (strcmp(client_info->channel, "none"))
				        free(client_info->channel);

					closeWC(wc);
					close(client_info->sockfd);
					perror("Connection lost.");
					exit(EXIT_FAILURE);
					break;
			}
			break;

		/**********************************************************************
		* disregards those messages
		**********************************************************************/
		case RPL_MYINFO:
		case RPL_SUPPORT:
		case RPL_ID:
		case RPL_LUSERCHANNELS:
		case ERR_NOMOTD:
		case RPL_ENDOFNAMES:
		case RPL_LISTEND:
		case UNKNOWNCONNECTIONS:
		case USERID:
			break;

		/*default:
			printMessage(wc, msg, "server");
			break;*/
	}
}

/******************************************************************************
* returns the message code, 0 if its not found
******************************************************************************/
int get_message_code(char* msg)
{
	char code[CODE];
	int i;

	for (i = 0; i < strlen(msg) && msg[i] != ' '; i++){}

	memcpy(code, msg + i + 1,CODE);
	return atoi(code);
}

/******************************************************************************
* returns a pointer the tail message
******************************************************************************/
char* get_tail_message(char* msg)
{
	int i;

	for (i = 1; i < strlen(msg) && msg[i] != ':'; i++){}
	return msg + i + 1;
}

/******************************************************************************
* adds a header to message and returns the new message 
******************************************************************************/
char* add_header(char* header, char* tail_message)
{
	char* message = malloc(strlen(header) + strlen(tail_message));

	memcpy(message, header, strlen(header));
	memcpy(message + strlen(header), tail_message, strlen(tail_message));
	message[strlen(header) + strlen(tail_message)] = '\0';
	return message;
}

/******************************************************************************
* returns the type of no-code message
******************************************************************************/
int get_message_type(char* message)
{
	int i, j = 0;
	char ping[5], error[6];

	strncpy(ping, message, 4);
	strncpy(error, message, 5);

	ping[4] = '\0';
	error[5] = '\0';

	if(!strcmp("PING", ping))
		return PING;

	if(!strcmp("ERROR", error))
		return ERROR;

	for (i = 0; i < strlen(message); i++)
	{
		if (message[i] == ' ' && !j)
			j = i + 1;
		else if (message[i] == ' ')
			break;
	}

	char type[i - j];
	strncpy(type, message + j, i - j);
	type[i - j] = '\0';

	if (!strcmp("NICK", type))
		return NICK;

	if (!strcmp("JOIN", type))
		return JOIN;

	if (!strcmp("PART", type))
		return PART;

	if (!strcmp("TOPIC", type))
		return TOPIC;

	if (!strcmp("PRIVMSG", type))
		return PRIVMSG;

	if(!strcmp("QUIT", type))
		return QUIT;

	return -1;
} 

/******************************************************************************
* returns the username of the sender of the message
******************************************************************************/
char* get_message_sender(char* message)
{
	int i;
	char* sender;

	for (i = 0; i < strlen(message) && message[i] != '!'; i++){}

	sender = (char*)malloc(i);
	memcpy(sender, message + 1, i - 1);
	sender[i - 1] = '\0';

	return sender;
}