/*****************************************************************************
 * connect_client.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * connect_client.c is a file that contains helper methods that are used by 
 * rftp.c.
******************************************************************************/

#include "connect_client.h"

/******************************************************************************
 * Returns the size of the file that is to be transfered, as long as it exists.
******************************************************************************/
int file_size(uint8_t* filename)
{
    FILE *file = fopen((char*)filename, "r");
    int size;

    if (!file) 
    { 
        perror("fileSize: Can't open file\n"); 
        exit(EXIT_FAILURE);
    }

    fseek(file, 0L, SEEK_END);
    size = ftell(file);
    fclose(file);

    return size;
}