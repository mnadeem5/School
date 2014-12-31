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

#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>

int file_size(uint8_t*);