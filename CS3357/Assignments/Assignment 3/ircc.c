/******************************************************************************
* ircc.c
******************************************************************************/

#include "ircc.h"

int main(int argc, char** argv)
{
	/**************************************************************************
	* sockfd, is the socket file discriptor 
	* debug, debug flag
	* terminate_thread, is the thread termination flag
	**************************************************************************/
 	int sockfd, debug = NODEBUG, *terminate_thread = (int*)malloc(sizeof(int));

 	/**************************************************************************
	* graphics context variable
 	* holds all the information about the layout and text buffers
	**************************************************************************/
 	WindowContext wc;
 	char input[512 - 60];


 	/**************************************************************************
	* sets the nickname to the operation system's username
	**************************************************************************/
 	char* temp = getlogin();
 	char* nickname = (char*)malloc(strlen(temp));
 	strcpy(nickname, temp);

	/**************************************************************************
	* receive_thread is a pthread object that will be used to open a message
	* receiever thread.
	**************************************************************************/
 	pthread_t receive_thread;

 	/**************************************************************************
	* process command line options and sets the terminate_thread flag to false
	**************************************************************************/
	sockfd = processOptions(argc, argv, &debug);
	*terminate_thread = 0;

 	/**************************************************************************
	* starts ncureses
	**************************************************************************/
	initWC(&wc, debug);

 	/**************************************************************************
	* creates the client_info typedef that will carry the users info 
	**************************************************************************/
	client_info_t client_info = {
		.sockfd = sockfd,
        .wc = &wc,
        .nick = nickname,
        .channel = "none",
        .terminate_thread = terminate_thread,
        .receive_thread = &receive_thread
	};

	initDebug(&wc);

	/**************************************************************************
	* opens a message receiever thread.
	**************************************************************************/
	pthread_create(&receive_thread, NULL, receive_message, (void*)&client_info);

	/**************************************************************************
	* starts the connection with the server
	**************************************************************************/
	connect_to_server(sockfd, nickname);

	while(1)
	{
		/**********************************************************************
		* gets the input from the ncurses buffer
		**********************************************************************/
		getInput(&wc, input);
		if(process_input(&client_info, input)==EXIT_STATUS) 
			break;
	}

	/**************************************************************************
	* close graphics, sets the thread termination flag to true and waits
	* for the thread to exit
	**************************************************************************/
	closeWC(&wc);
	*terminate_thread = 1;
	pthread_join(receive_thread, NULL);

	/**************************************************************************
	* frees terminate_thread flag, and closes the socket port
	**************************************************************************/
	free(terminate_thread);
	close(sockfd);

	return EXIT_SUCCESS;
}

/******************************************************************************
* processOptions helps the main function proccess all command line inputs
******************************************************************************/
int processOptions(int argc, char *argv[], int *debug)
{
	/**************************************************************************
	* server, holds the server address
	* port, holds the port number
	**************************************************************************/
	char *server = NULL, *port = PORT;
	int opt, option_index = 0; 

	openlog("Server", LOG_PERROR | LOG_PID | LOG_NDELAY, LOG_USER);
    setlogmask(LOG_UPTO(LOG_INFO));

	/**************************************************************************
	* creates an option struct that will contain the possible long options
	**************************************************************************/
	static struct option long_options[] =
	{
		{"server",  no_argument, 0, 0},
		{"port", 	optional_argument, 0, 'p'},
		{"debug", 	optional_argument, 0, 'd'},
		{0, 0, 0, 0}
	};

	/**************************************************************************
	* iterates through the command line input to determine the requested
	* options and adds there arguments
	**************************************************************************/
	while((opt = getopt_long(argc, argv, "sp::d", long_options
		, &option_index)) != -1)
		switch (opt)
		{
			case 's':
				server = "54.86.211.170";
				break;

			case 'p':
				port = optarg;
				break;

			case 'd':
				*debug = DEBUG;
				break;
		}

	/**************************************************************************
	* if the server option was not provieded then exit with an error
	**************************************************************************/

	if (optind == argc - 1 && server == NULL)
		server = argv[optind];
	else if (server == NULL)
	{
		syslog(LOG_WARNING, "Please provide the server argument(--server).\n");
		exit(EXIT_FAILURE);
	}

	/**************************************************************************
	* if the server name provided is longer than 63 characters then exits with
	* an error
	**************************************************************************/
	if (strlen(server) > 63)
	{
		syslog(LOG_WARNING, "The server name should not exceed 63 characters. "
							"Plase provide a valid the server name.\n");
		exit(EXIT_FAILURE);	
	}

	/**************************************************************************
	* open connection with the given server and port number
	**************************************************************************/
	return open_connection(server, port);
}

/******************************************************************************
* pconnect_to_server starts a connection with the server
******************************************************************************/
void connect_to_server(int sockfd, char* nickname)
{
	send_nick_msg(sockfd, nickname);
	send_user_msg(sockfd, "cs3357", "0", nickname);
}