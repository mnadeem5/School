#include "process_input.h"

//*** process_input ***
//processes the user input
//checks for commands given, otherwise it prints input as a regular message to chat
//commands are checked for proper syntax
int process_input(client_info_t* client_info, char *input)
{
    int sockfd = client_info->sockfd;
    WindowContext* wc = client_info->wc;

    int ext=NO_EXIT_STATUS;
    char *inputCopy, *tokens[MAX_TOKENS];
    int numToks;

    //tokenize the input to find possible commands
    inputCopy=(char *)malloc(strlen(input)+1);
    strcpy(inputCopy, input);
    numToks=tokenize(inputCopy, tokens);

    //************************************************************
    //find possible commands and call the appropriate functions
    //************************************************************
    
    //registration commands
    if(numToks && !strcmp(tokens[0], "/nick"))
        processNickCommand(sockfd, wc, tokens, numToks);
    else if(numToks && !strcmp(tokens[0], "/quit"))
        ext=processQuitCommand(client_info, input, numToks);

    //channel commands
    else if(numToks && !strcmp(tokens[0], "/join"))
        processJoinCommand(client_info, tokens, numToks);
    else if(numToks && !strcmp(tokens[0], "/leave"))
        processPartCommand(client_info, tokens, input, numToks);
    else if(numToks && !strcmp(tokens[0], "/list"))
        processListCommand(sockfd, wc, tokens, numToks);
    else if(numToks && !strcmp(tokens[0], "/topic"))
        processTopicCommand(client_info, tokens, input, numToks);

    else if (numToks && !strcmp(tokens[0], "/help"))
        processHelpCommand(wc);

    //************************************************************
    //send regular command if no commands found
    //************************************************************
    else
        processRegularMessage(client_info, input);

    free(inputCopy);
    return ext;

}

//*** processNickCommand ***
//processes a /nick command with appropriate error checking
void processNickCommand(int sockfd, WindowContext *wc, char **tokens, int numToks)
{
    if(numToks - 1 > NICK_PARAMS)
    {
        printErrorMessage(wc, "too many parameters for /nick command");
        return;
    } 
    else if(numToks - 1 < NICK_PARAMS)
    {
        printErrorMessage(wc, "need more parameters for /nick command");
        return;
    }

    send_nick_msg(sockfd, tokens[1]);
}

//*** processQuitCommand ***
//process a /quit command with appropriate error checking
int processQuitCommand(client_info_t* client_info, char *input, int numToks)
{   
    int sockfd = client_info->sockfd;

    free(client_info->nick);

    if (strcmp(client_info->channel, "none"))
        free(client_info->channel);

    //check to see if extra 'message' parameter is specified
    if(numToks >= QUIT_PARAMS + 1) 
    {
        send_quit_msg(sockfd, input + 6);
        return EXIT_STATUS;
    }

    send_quit_msg(sockfd, "");
    return EXIT_STATUS;
}

//*** processJoinCommand ***
//processes a /join command with appropriate error checking
void processJoinCommand(client_info_t* client_info, char **tokens, int numToks)
{
    int sockfd = client_info->sockfd;
    char* channel = client_info->channel;

    if(numToks - 1 > JOIN_PARAMS)
    {
        if(numToks - 1 == JOIN_PARAMS + 1)
        {
            if (strcmp(channel, "none"))
                send_channel_part_msg(sockfd, channel, "joining another channel");

            send_channel_join_msg(sockfd, tokens[1], tokens[2]);
        }
        else 
            printErrorMessage(client_info->wc, "too many parameters for /join command");
        return;
    }
    else if(numToks - 1 < JOIN_PARAMS)
    {
        printErrorMessage(client_info->wc, "need more parameters for /join command");
        return;
    }

    if (strcmp(channel, "none"))
        send_channel_part_msg(sockfd, channel, "joining another channel");

    send_channel_join_msg(sockfd, tokens[1], "");
}

//*** processPartCommand ***
//processes a /part command with appropriate error checking
void processPartCommand(client_info_t* client_info, char **tokens, char *input, int numToks)
{
    if(numToks >= PART_PARAMS + 1) 
    {
        send_channel_part_msg(client_info->sockfd, client_info->channel, input + 6);
        return ;
    }

    send_channel_part_msg(client_info->sockfd, client_info->channel, "");
}

//*** processListCommand ***
//processes a /list command with appropriate error checking
void processListCommand(int sockfd, WindowContext *wc, char **tokens, int numToks)
{
    if(numToks == LIST_PARAMS + 1) 
        send_channel_list_msg(sockfd, tokens[1]);
    else 
        send_channel_list_msg(sockfd, "");
}

//*** processTopicCommand ***
//processes a /topic command with appropriate error checking
void processTopicCommand(client_info_t* client_info, char **tokens, char *input, int numToks)
{
    if(numToks >= TOPIC_PARAMS + 1) 
    {
        send_channel_topic_msg(client_info->sockfd, client_info->channel, input + 7);
        return ;
    }

    send_channel_topic_msg(client_info->sockfd, client_info->channel, "");
}


//need to complete this function (does not send to server)
void processRegularMessage(client_info_t* client_info, char *input)
{   
    send_private_message(client_info->sockfd, client_info->channel, input);
    printMessage(client_info->wc, input, client_info->nick);
}

void processHelpCommand(WindowContext *wc)
{
    printServerMessage(wc, "/nick: change the user's nickname");
    printServerMessage(wc, "/quit: quits ircc application");
    printServerMessage(wc, "/join: joins a chat room");
    printServerMessage(wc, "/leave: leaves a chat room");
    printServerMessage(wc, "/list: lists all chat rooms");
    printServerMessage(wc, "/topic: changes the chat rooms topic");
}