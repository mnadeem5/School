 /******************************************************************************
* messages.c contains all the messages that deal with client messagging.
******************************************************************************/

#include "messages.h"

/******************************************************************************
* send_private_message sends a private message to a user or a channel
*
* Private Message:
*		Command: PRIVMSG
*		Parameters: <traget> :<message>			*target can either be a
*												nickname or a channel name.
*		Ex: "PRIVMSG Saad :Hello Saad!\r\n"
*		Ex: "PRIVMSG #CS3357 :Hello World!\r\n"
******************************************************************************/
void send_private_message(int sockfd, char* target, char* message)
{
	send_message(PRIVMSG, sockfd, target, message);
}

/******************************************************************************
* send_message is helper function that helps all the send_*_msg methods parse
* and send their messages.
******************************************************************************/
void send_message(short type, int sockfd, char* str0, char* str1)
{
	/**************************************************************************
	* get_message_len is located in send_message.c this method helps in 
	* calculating the messages' sizes.
	**************************************************************************/
	int size = TYPE + SPACE + get_message_len(type, str0, str1, NULL) + CRLF;
	/**************************************************************************
	* buffer will be used to format the message while msg will hold the message
	* value and is used with send_msg
	**************************************************************************/
	char buffer[size + 1], *msg = (char*)malloc(size);

	switch(type)
	{
		/**********************************************************************
		* Private message.
		**********************************************************************/
		case PRIVMSG:
			sprintf(buffer, "PRIVMSG %s :%s\r\n", str0, str1);
			break;
	}
	/**************************************************************************
	* copies the message from buffer to msg.
	**************************************************************************/
	memcpy(msg, buffer, size);
	/**************************************************************************
	* sends the message
	**************************************************************************/
	send_msg(sockfd, msg, size);
	/**************************************************************************
	* frees msg
	**************************************************************************/
	free(msg);
}