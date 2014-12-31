/*****************************************************************************
 * rftpd.c
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * rftpd.c is a server function that interprets the users input and performs
 * a file transfer operation between it and a client function. 
******************************************************************************/

#include "rftpd.h"

/*****************************************************************************
 * Process the user input and passes it on to the server function.
******************************************************************************/
int main(int argc, char** argv)
{
    char *dir, *port = PORT;
    int opt, option_index = 0, timeout = TOUT;

    /**************************************************************************
     * Initializes the syslog object and sets its mask to a normal mask.
    **************************************************************************/
    openlog("Server", LOG_PERROR | LOG_PID | LOG_NDELAY, LOG_USER);
    setlogmask(LOG_UPTO(LOG_INFO));

    /**************************************************************************
     * long_options is an array of option structs that will hold the values
     * for the program options.
    **************************************************************************/
    static struct option long_options[] =
    {
        {"verbose",     no_argument,        0, 'v'},
        {"timeout",     required_argument,  0, 't'},
        {"port",        required_argument,  0, 'p'},
        {0, 0, 0, 0}
    };

    /*************************************************************************
     * Iterates through the argc list, finds all the options that are
     * inlisted under long_options, and performs appropriate tasks. 
    **************************************************************************/
    while((opt = getopt_long(argc, argv, "vt:p:", long_options, &option_index))
        != -1)
        switch (opt)
        {
            case 'v':
                setlogmask(LOG_UPTO(LOG_DEBUG));
                break;
            case 't':
                timeout = atoi(optarg) * 1000;
                break;
            case 'p':
                port = optarg;
                break;
        }

    /*************************************************************************
     * Fetches the values for the destenation folder.
    **************************************************************************/
    if (optind != argc - 1)
    {
        perror("Error! Please enter the destenation folder (ex. rftpd [OPTIONS...] [OUTPUTDIR])\n");
        exit(EXIT_FAILURE);	
    }

    dir = argv[optind];

    connect_server(dir, timeout, port);

    return 1;
}