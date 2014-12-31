/*
 * prog1.c
 *
 *  Created on: Nov 26, 2013
 *      Author: ZiZo
 */

#include<stdio.h>
#include<stdlib.h>

//defines the limits for the max number of digits and the max number of digits and the max length of the string.
#define MAX_DIGITS 20
#define MAX_STRING 100

//methods prototypes.
void clear_digits_array(void);
void process_digit(int digit, int position);
void print_digits_array(void);

//creates a character array that will hold the seven-segment numbers.
char digits[3][MAX_DIGITS * 4];

//creates an integer array that will contain the information about the segments of each number.
int segments[][7] =	{{1, 1, 1, 1, 1, 1, 0},{0, 1, 1, 0, 0, 0, 0},
					{1, 1, 0, 1, 1, 0, 1},{1, 1, 1, 1, 0, 0, 1},
					{0, 1, 1, 0, 0, 1, 1},{1, 0, 1, 1, 0, 1, 1},
					{1, 0, 1, 1, 1, 1, 1},{1, 1, 1, 0, 0, 0, 0},
					{1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 0, 0, 1, 1}};

int main (void)
{
	//clears the digits array.
	clear_digits_array();

	//creates an array of characters that will hold the user input and the null character.
	char str[MAX_STRING+1];

	//gets the the user input and stores it into str.
	printf("Enter a number:\n");gets(str);

	int n, ctr=0, ptr=0;
	//points the pointer s to the beginning of the str character array.
	char *s=str;

	//loops until it reaches the max length of a string or until str[n]=null
	do{
		//changes the ASCII value of the numbers to numbers between 0-9
        n=*s-'0';
        //checks if the character is a number and if the max digits has been reached and calls process_digit.
        if(n>-1 && n<10 && ctr<MAX_DIGITS)process_digit(n, ctr++);
        ptr++;
	}while(*s++&&ptr<MAX_STRING);

//calls the print function.
print_digits_array();

return 0;
}

//clears the digits array.
void clear_digits_array(void)
{
	//points q to the array digits.
	char *q=*digits;
	int i, j;
	//loops through the array digits.
	for(i=0; i<3; i++)for(j=0;j<(MAX_DIGITS *4); j++)
	{
		//changes the location the pointer points to and changes its value to ' '.
		q=*(digits+i)+j;
		*q=' ';
	}
}


void process_digit(int digit, int position)
{
	//creates a pointer and points it to the segments array at [digit][0]
	int *p=*(segments+digit);
	//creates a pointer for the digits array.
	char *q;
	int pos = (position *4);

	//points the pointer p and q to segments[digit][0] and digits[0][pos+1] respectively.
	q=*(digits)+pos+1;
	//if this segment is on then add it to digits at location pointed by q.
	if(*p==1)*q= '_';

	//points the pointer p and q to segments[digit][1] and digits[1][pos+2] respectively.
	p++;q=*(digits+1)+pos+2;
	//if this segment is on then add it to digits at location pointed by q.
	if(*p==1)*q= '|';

	//points the pointer p and q to segments[digit][2] and digits[2][pos+2] respectively.
	p++;q=*(digits+2)+pos+2;
	//if this segment is on then add it to digits at location pointed by q.
	if(*p==1)*q= '|';

	//points the pointer p and q to segments[digit][3] and digits[2][pos+1] respectively.
	p++;q=*(digits+2)+pos+1;
	//if this segment is on then add it to digits at location pointed by q.
	if(*p==1)*q= '_';

	//points the pointer p and q to segments[digit][4] and digits[2][pos] respectively.
	p++;q=*(digits+2)+pos;
	//if this segment is on then add it to digits at location pointed by q.
	if(*p==1)*q= '|';

	//points the pointer p and q to segments[digit][5] and digits[1][pos] respectively.
	p++;q=*(digits+1)+pos;
	//if this segment is on then add it to digits at location pointed by q.
	if(*p==1)*q= '|';

	//points the pointer p and q to segments[digit][6] and digits[0][pos+1] respectively.
	p++;q=*(digits+1)+pos+1;
	//if this segment is on then add it to digits at location pointed by q.
	if(*p==1)*q= '_';
}

//prints the final result.
void print_digits_array(void)
{
	int i,j;
	//points q to the array digits.
	char *q=*digits;
	//loops through the array digits.
	for(i=0;i<3;i++)
	{
		for(j=0;j<(MAX_DIGITS*4);j++)
		{
			//changes the location the pointer points to and prints its value.
			q=*(digits+i)+j;printf("%c", *q);
		}
		printf("\n");
	}
}
