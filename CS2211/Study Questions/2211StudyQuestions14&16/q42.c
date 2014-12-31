/*
 * q42.c
 *
 *  Created on: Dec 6, 2013
 *      Author: ZiZo
 */

#include<stdio.h>


int main (void)
{
	enum {frosh, soph, jr, sr}class;
	for(class = frosh; class <=jr; ++class)
		printf("%d", class);
	return 0;
}
