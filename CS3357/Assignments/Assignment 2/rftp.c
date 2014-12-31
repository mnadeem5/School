/*****************************************************************************
 * rftp.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * rftp.c is a client function that interprets the users input and performs
 * a file transfer operation between it and a server function. 
******************************************************************************/

#include "rftp.h"

/*****************************************************************************
 * Process the user input and passes it on to the client function.
******************************************************************************/
int main(int argc, char** argv)
{
    char *server, *port = PORT;
    int opt, option_index = 0;

    /*************************************************************************
     * info is a transfer_info_t structure that hold all the important
     * information about a transfer operation.
    **************************************************************************/
    transfer_info_t info = {
    	.protocol = STOPANDWAIT,
    	.win_size = 2,
    	.timeout = TOUT,
        .verbose = 0
    };

    /*************************************************************************
     * long_options is an array of option structs that will hold the values
     * for the program options.
    **************************************************************************/
    static struct option long_options[] =
    {
        {"verbose",     no_argument,        0, 'v'},
        {"timeout",     required_argument,  0, 't'},
        {"port",        required_argument,  0, 'p'},
        {"gbn",         required_argument,  0, 'g'},
        {0, 0, 0, 0}
    };

    /*************************************************************************
     * Iterates through the argc list, finds all the options that are
     * inlisted under long_options, and performs appropriate tasks. 
    **************************************************************************/
    while((opt = getopt_long(argc, argv, "g:vt:p:", long_options
        , &option_index)) != -1)
        switch (opt)
        {
            case 'v':
                info.verbose = 1;
                break;
            case 't':
                info.timeout = atoi(optarg);
                break;
            case 'p':
                port = optarg;
                break;
            case 'g':
            	info.protocol = GOBACKN;
                info.win_size = atoi(optarg);
                break;
        }
    /*************************************************************************
     * Fetches the values for the hostname and the filename.
    **************************************************************************/
    if (optind != argc - 2)
    {
        perror("Error! Please enter the server hostname/IP address"
            " and the filename(ex. rftp [OPTIONS...] [SERVER] [FILENAME])\n");
        exit(EXIT_FAILURE);
    }

    server = argv[optind];
    info.name = (uint8_t*)argv[optind + 1];

    client(server, port, &info);
    return 1;
}

/*****************************************************************************
 * Binds a socket for the transfer use and calls the appropriate transfer
 * method with the transfer_info_t structure. 
******************************************************************************/
void client(char* host, char* port, transfer_info_t* info)
{
    host_t server;
    int sockfd;

    /*************************************************************************
     * Binds the socket.
    **************************************************************************/
    if ((sockfd = create_client_socket(host, port, &server)) == -1)
    {
        perror("Failed to create socket.\n");
        close(sockfd);
        exit(EXIT_FAILURE);
    }

    /*************************************************************************
     * Popultes the transfer_info_t structure with needed information
    **************************************************************************/
    info->file_size = file_size(info->name);
	info->sockfd = sockfd;
	info->server = &server;

    /*************************************************************************
     * Chooses appropriate transfer protocol.
    **************************************************************************/
    if (info->protocol == STOPANDWAIT)
        stop_and_wait(info);
    else
        go_back_n(info);
}