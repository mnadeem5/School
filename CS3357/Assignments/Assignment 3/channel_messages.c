/******************************************************************************
* channel_messages.c contains all the messages that deal with channel 
* operations.
******************************************************************************/

#include "channel_messages.h"

/******************************************************************************
* send_channel_join_msg sends a join request to the server
*
* Join Message:
*		Command: JOIN
*		Parameters: {<channel>} {<key>}			**We can have multiple channels
*												and keys, they must be seprated
*												with "," but no spaces. *{}
*												means can have multiple
*		Ex: "JOIN #CS3357\r\n"
*		Ex: "JOIN #CS3357 KeytoNetworks\r\n"
*		Ex: "JOIN #CS3357,#CS2212 KeytoNetworks,HateGroups\r\n"
*		Ex: "JOIN #CS3357,#CS2212 KeytoNetworks\r\n"
*		Ex: "JOIN #CS3357,#CS2212 ,HateGroups\r\n"
******************************************************************************/
void send_channel_join_msg(int sockfd, char* channel, char* key)
{
	send_channel_msg(JOIN, sockfd, channel, key, NULL);
}

/******************************************************************************
* send_channel_part_msg sends a part request to the server
*
* Part Message:
*		Command: PART
*		Parameters: {<channel>} [:<message>]	Can also part mutliple messages
*												with the use of "," **no spaces
*												between the list items.
*		Ex: "PART #CS3357\r\n"
*		Ex: "PART #CS3357 :sleep is for the weak\r\n"
*		Ex: "PART #CS3357,#CS2212 :dropping out\r\n"
*		Ex: "PART #CS3357 :sleep is for the weak\r\n"
******************************************************************************/
void send_channel_part_msg(int sockfd, char* channel, char* message)
{
	send_channel_msg(PART, sockfd, channel, message, NULL);
}

/******************************************************************************
* send_channel_topic_msg sends a part request to either query the message topic
* or change it
*
* Topic Message:
*		Command: TOPIC
*		Parameters: <channel> [:<topic>]		*if a topic is included then
*												the server changes the channels
*												topic. However if the topic
*												field is empty then the server
*												will send back the current
*												channel's topic.
*		Ex: "TOPIC #CS3357\r\n"
*		Ex: "TOPIC #CS3357 :I am getting tired of this\r\n"
******************************************************************************/
void send_channel_topic_msg(int sockfd, char* channel, char* topic)
{
	send_channel_msg(TOPIC, sockfd, channel, topic, NULL);
}

/******************************************************************************
* send_channel_list_msg sends a names message to server to request a list of
* the visible channels
*
* List Message:
*		Command: LIST
*		Parameters: {[<channel>]}				*if there is no channel
*												parameter then the server will
*												return all channels on the
*												server along with their topic,
*												if there are then it returns
*												the topics of those channels.
*		Ex: "LIST\r\n"
*		Ex: "LIST #CS3357\r\n"
*		Ex: "LIST #CS3357,#CS2212\r\n"
******************************************************************************/
void send_channel_list_msg(int sockfd, char* channel)
{
	send_channel_msg(LIST, sockfd, channel, NULL, NULL);
}

/******************************************************************************
* send_channel_msg is helper function that helps all the send_channel_*_msg
* methods parse and send their messages.
******************************************************************************/
void send_channel_msg(short type, int sockfd, char* str0, char* str1, char* str2)
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
		* Join message.
		**********************************************************************/
		case JOIN:
			sprintf(buffer, "JOIN %s %s\r\n", str0, str1);
			break;		

		/**********************************************************************
		* Part message.
		**********************************************************************/
		case PART:
			sprintf(buffer, "PART %s :%s\r\n", str0, str1);
			break;

		/**********************************************************************
		* Topic message.
		**********************************************************************/
		case TOPIC:
			sprintf(buffer, "TOPIC %s :%s\r\n", str0, str1);
			break;

		/**********************************************************************
		* List message.
		**********************************************************************/
		case LIST:
			sprintf(buffer, "LIST %s\r\n", str0);
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