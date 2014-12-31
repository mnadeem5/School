#ifndef COMMAND_REPLIES_H
#define COMMAND_REPLIES_H

/******************************************************************************
* replies upon connection to the server
******************************************************************************/
#define RPL_WELCOME 			1
#define RPL_YOURHOST			2
#define RPL_CREATED				3
#define RPL_MYINFO				4
#define RPL_SUPPORT				5
#define RPL_CONNECTIONPROCESS 	20

/******************************************************************************
* AWAY command replies
******************************************************************************/
#define RPL_AWAY 				301
#define RPL_UNAWAY 				305
#define RPL_NOWAWAY				306

/******************************************************************************
* LIST command replies
******************************************************************************/
#define RPL_LIST 				322
#define RPL_LISTEND 			323

/******************************************************************************
* TOPIC command replies
******************************************************************************/
#define RPL_NOTOPIC 			331
#define RPL_TOPIC 				332

/******************************************************************************
* INVITE command replies
******************************************************************************/
#define RPL_INVITING			341

/******************************************************************************
* NAMES command replies
******************************************************************************/
#define RPL_NAMREPLY 			353
#define RPL_ENDOFNAMES 			366

/******************************************************************************
* Server reply to a dropped command
******************************************************************************/
#define	RPL_TRYAGAIN 			263 

/******************************************************************************
* Custom replies for ircd-irc2 server
******************************************************************************/
#define RPL_ID					42
#define RPL_LUSERCLIENT			251
#define RPL_LUSERCHANNELS		254
#define RPL_LUSERME				255
#define RPL_LUSER 				265
#define RPL_GUSER 				266
#define NOCODE					0
#define UNKNOWNCONNECTIONS		253
#define	USERID					333

#endif