/******************************************************************************
* connection_messages.c contains all the messages that deal with connection 
* registration.
******************************************************************************/

#include "connection_messages.h"

/******************************************************************************
* send_nick_msg sends a nickname message and is the first step to establishing
* a connection with the server.
*
* Nick Message(Nickname Message):
*		Command: NICK
*		Parameters: <nickname>
*		Ex: "NICK Saad\r\n"
******************************************************************************/
void send_nick_msg(int sockfd, char* nickname)
{
	send_registration_msg(NICK, sockfd, nickname, NULL, NULL);
}

/******************************************************************************
* send_user_msg sends a user message that contains the user information. This
* message is the second part of establishing a connection with the server.
*
* User Message:
*		Command: USER
*		Parameters: <nickname> <mode> <unused> <realname>
*		Ex: "USER Zaid 0 * :Zaid Albirawi\r\n"
******************************************************************************/
void send_user_msg(int sockfd, char* username, char* mode, char* realname)
{
	send_registration_msg(USER, sockfd, username, mode, realname);
}

/******************************************************************************
* send_quit_msg sends a quit message to the server. The server will reply with
* an error message, thats nothing to worry about. This is how the system is 
* built.
*
* Quit Message:
*		Command: QUIT
*		Parameters: [<message>]
*		Ex: "QUIT :Going to bed, good night.\r\n"
******************************************************************************/
void send_quit_msg(int sockfd, char* message)
{
	send_registration_msg(QUIT, sockfd, message, NULL, NULL);
}

/******************************************************************************
* send_registration_msg is helper function that helps all the send_*_msg
* methods parse and send their messages.
******************************************************************************/
void send_registration_msg(short type, int sockfd, char* str0, char* str1
	, char* str2)
{
	/**************************************************************************
	* get_message_len is located in send_message.c this method helps in 
	* calculating the messages' sizes.
	**************************************************************************/
	int size = TYPE + SPACE + get_message_len(type, str0, str1, str2) + CRLF;
	/**************************************************************************
	* buffer will be used to format the message while msg will hold the message
	* value and is used with send_msg
	**************************************************************************/
	char buffer[size], *msg = (char*)malloc(size);

	switch(type)
	{

		/**********************************************************************
		* Nickname message.
		**********************************************************************/
		case NICK:
			sprintf(buffer, "NICK %s\r\n", str0);
			break;

		/**********************************************************************
		* User message.
		**********************************************************************/
		case USER:
			sprintf(buffer, "USER %s %s * :%s\r\n", str0, str1, str2);
			break;

		/**********************************************************************
		* Quit message.
		**********************************************************************/
		case QUIT:
			sprintf(buffer, "QUIT :%s\r\n", str0);
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