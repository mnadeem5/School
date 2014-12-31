#include "send_message.h"

WindowContext* wc = NULL;

void initDebug(WindowContext* window)
{
	wc = window;
	printDebugMessage(wc, "DEBUG MODE");
}

void send_msg(int sockfd, char* msg, int size)
{
	char debugMess[4096];
	if (send(sockfd, msg, size, 0) == -1)
	{
		closeWC(wc);
		perror("Error: failed to send message.\n");
		exit(EXIT_FAILURE);
	}

	if (wc != NULL)
	{
		msg[size - 1] = '\0';
		sprintf(debugMess, "Message Sent - %s", msg);
		printDebugMessage(wc, debugMess);
	}
}

int get_message_len(short type, char* str0, char* str1, char* str2)
{
	switch(type)
	{
		case NICK:
		case LIST:
			return strlen(str0);

		case QUIT:
			return 1 + strlen(str0);

		case JOIN:
			return strlen(str0) + SPACE + strlen(str1);

		case PART:
			return strlen(str0) + SPACE + 1 + strlen(str1);

		case TOPIC:
			return 1 + strlen(str0) + SPACE + 1 + strlen(str1);

		case PRIVMSG:
			return 3 + strlen(str0) + SPACE + 1 + strlen(str1);

		case USER:
			return strlen(str0) + SPACE + strlen(str1) + SPACE + ASTERISK
					+ SPACE + 1 + strlen(str2);

		default:
			exit(EXIT_FAILURE);
	}
}