/*
 * q39.c
 *
 *  Created on: Dec 6, 2013
 *      Author: ZiZo
 */

#include<stdio.h>
#include<string.h>

int main(int argc, char* argv[])
{
	char**p = &argv[0];
	int i=0, j;
	while(i<20)
	{
		j=0;
		while(++j<argc)
			if (strlen(*(p+j))>i)
				putchar(*(*(p+j)+i));
		i++;
	}
	return 0;
}
