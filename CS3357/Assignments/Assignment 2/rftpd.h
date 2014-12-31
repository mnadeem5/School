/*****************************************************************************
 * rftpd.h
 * 
 * Computer Science 3357a
 * Assignment 2
 *
 * Author: Zaid Albirawi
 * 
 * rftpd.c is a server function that interprets the users input and performs
 * a file transfer operation between it and a client function. 
******************************************************************************/

#ifndef RFTPD_H
#define RFTPD_H

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <getopt.h>
#include <syslog.h>

#include "connect_server.h"

#define PORT "5000"
#define TOUT 30000

void server(char*, int, char*);
 
#endif