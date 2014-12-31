/********************************************************************************
 * calc.h
 * 
 * Computer Science 3357a
 * Assignment 1
 *
 * Author: Zaid Albirawi
 * Email: zalbiraw@uwo.ca
 * 
 * This header file is called upon by the calc-server.c program to parse and 
 * compute the value of the expression provided. 
*******************************************************************************/

#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include "stack.h"

//Function prototypes.
char* calc (char*);
char* calcHelper (char*, stack*, stack*);
char* simplify (stack*, stack*);
char* operate (stack*, stack*);
bool isDigit (char);
void invalid (char*, char*);

//final will contain the formatted final answer.
char final [4096];

/********************************************************************************
 * calc
 * 
 * calc will take in an expression and check weather its properly formatted or
 * not. If it is properly formatted then it calls the calcHepler to parse and 
 * solve the expression, otherwise, it will return a status code with the error
 * description.
*******************************************************************************/
char* calc (char* expr)
{
	char* result;
	short l = strlen(expr);

	//Checks the formate of the request, weather it ends with \r\n and has a 
	//maximum length of 80 characters or not.
	if (l < 3 || expr[l-2] != '\r' || expr[l-1] !='\n')
		return "Status: malformed-req\r\n";
	else if (strlen(expr)>80)
		return "Status: max-length-exceeded\r\n";
	else 
	{
		//Creates and initilizes the stacks that it will be using to solve the 
		//expression
		stack stkOprator, stkOprand;
		initStack(&stkOprator);
		initStack(&stkOprand);

		result = calcHelper(expr, &stkOprator, &stkOprand);

		//Checks weather the expression is valid or not depending on the calcHelper
		//response. 
		if (result == "ok")
		{
			//formates the respond message and returns it to the calc-server.
            sprintf(final, "Status: ok\r\nResult: %d\r\n", popInt(&stkOprator));
            return final;
		}
		else
		{
			//formates the respond message and returns it to the calc-server.
			sprintf(final, "Status: %s\r\n", result);
			return final;
		}
	}
}

/********************************************************************************
 * calcHelper
 * 
 * calcHelper makes use of the stacks stkOprator, and stkOprand to validate and 
 * and solve the expression provided by the method calc.
*******************************************************************************/
char* calcHelper(char* expr, stack* stkOprator, stack* stkOprand)
{
	//Hold the values of the results returned from the function operate, and 
	//the values for the parsed digits, respectively.
	char *result, *newDigit;
	//Counters for expression and the digit parser.
	short next = 0, i;
	//If true then the previously parsed character is an oprand, else it is an
	//operator. It true then the next number will be set to a negative value, 
	//else it won't. If true then the brackets are empty, else they are not.
	bool oprand = 1, negative = 0, empty;

	//Iterates while the terminator is not approached.
	while (expr[next] != '\r')
	{
		if (strchr("+*/", expr[next]) != NULL)
		{
			//If the last character paresed is an oprand then the expression
			//is invalid (ex. ++, *+ -+, /+, -*, */) 
			if (oprand)
				return "invalid-expr";
			//Sets the values of oprand to true, because this character is an 
			//oprand.
			oprand = 1;
		}
		//switch case for the different character values acceptable.
		switch (expr[next])
		{
			case '+':
				//Pushes '+' onto the oprands stack.
				pushStr(stkOprand, "+");
				break;

			case '-':
				//If the last character paresed is an oprand then this '-'
				//must be a negator. 
				if (oprand)
				{
					//Toggle the negation status.
					if (negative)
						negative = 0;
					else
						negative = 1;
				}
				else 
				{
					//If this is a subtraction oprand then multiple the next 
					//operator my -1 and insert and addition sign between them.
					negative = 1;
					pushStr(stkOprand, "+");
				}

				oprand = 1;
				break;

			case '*':
				//Pushes '*' onto the oprands stack.
				pushStr(stkOprand, "*");
				break;

			case '/':
				//Pushes '/' onto the oprands stack.
				pushStr(stkOprand, "/");
				break;

			case ' ':
				break;

			case '(':
				//If the last character paresed is not an oprand then the expression
				//is invalid (ex. #(expr), 100(1+1)) 
				if (!oprand)
					return "invalid-expr";

				//Set the empty flag to true.
				empty = 1;
				//If the negation flag is set, then negate the result of the brackets.
				if (negative)
				{
					negative = 0;
					pushInt(stkOprator, -1);
					pushStr(stkOprand, "*");
				}

				oprand = 1;	
				//Pushes '(' onto the oprands stack.
				pushStr(stkOprand, "(");
				break;

			case ')':
				//Solve what is on the stack until '(' is found, if no '('
				//is found then there exists a bracket mismatch.
				while (stkOprand->size > 0 && peekStr(stkOprand)!="(")
					if ((result = operate(stkOprand, stkOprator)) != "ok")
						return result;

				//Checks if the oprand stack is empy, hence, no '(' found in the 
				//oprand stack, returns a mismatch.
				if (stkOprand->size==0)
					return "mismatch";
				
				//If the last character paresed is an oprand then the expression
				//is invalid (ex. (1+), 1+(1+1-)) 
				if (oprand)
					return "invalid-expr";

				//If the brackets do not contain anything then the expression is 
				//invalid. 
				if (empty)
					return "invalid-expr";

				oprand = 0;
				//Pops the '(' off the oprands stack.
				popStr(stkOprand);

				//Calls simplify to perform any queued operations (negations of the
				//brackets result)
				simplify(stkOprand, stkOprator);
				break;

			case '0' ... '9':
				//If the last character paresed is an operator then the expression
				//is invalid (ex. 1 1, 1+1 20) 
				if (!oprand)
					return "invalid-expr";

				//Finds the length of the number.
				for (i = next; next < strlen(expr) && isDigit(expr[next]); next++)
					0;

				//Substrings the number into a new string, newDigit.
				newDigit = (char*)malloc(next-i);
				strncpy(newDigit, expr+i, next-i);

				//If the flag negative is true, then push the negation of the number
				//into the oprator stack and turn off the flag, else push the oprator
				//into the stack.
				if (!negative)
					pushInt(stkOprator, atoi(newDigit));
				else 
				{
					negative = 0;
					pushInt(stkOprator, -1*atoi(newDigit));
				}	

				//Perform all pendding multiplication and division calculations.
				simplify(stkOprand, stkOprator);
				
				//Set the empty flag to zero, therefore, if there is a bracket
				//open then that bracket is not empty. 
				empty = 0;
				oprand = 0;
				next--;
				break;

			//If there exists a character that is not acceptable then terminate.
			default:
				return "invalid-expr";
		}
		next++;
	}

	//preforms all the leftover operations, addition and subtraction.
	while (stkOprand->size>0)
		if ((result = operate(stkOprand, stkOprator))!="ok")
			return result;
	
	return "ok";
}

/********************************************************************************
 * simplify
 * 
 * simplify performs all the multiplication and division operations that are 
 * prending after a parsing operation.
*******************************************************************************/
char* simplify(stack* stkOprand, stack* stkOprator)
{
	char *result;
	while (peekStr(stkOprand) == "*" || peekStr(stkOprand) == "/")
		if ((result = operate(stkOprand, stkOprator)) != "ok")
			return result;
}

/********************************************************************************
 * operate
 * 
 * operate performs the addition, multiplication and division operations and 
 * pushes the new values onto the stack.
*******************************************************************************/
char* operate(stack* stkOprand, stack* stkOprator)
{
	int x, y;
	char oprand = popStr(stkOprand)[0];

	//Checks if there are two or more oprators for a binary operation
	if (stkOprator->size > 1)
	{
		//Pops the first two operators off of the stack to perform 
		//a binary operation on them
		x = popInt(stkOprator);
		y = popInt(stkOprator);
		switch(oprand)
		{
			case '+':
				pushInt(stkOprator, x+y);
				break;
			case '*':
				pushInt(stkOprator, x*y);
				break;
			case '/':
				//Returns invalid if the second number is a 0, division by zero is not 
				//allowed.
				if (x == 0)
					return "invalid-epxr";

				pushInt(stkOprator, y/x);
				break;
			//Checks if there is a leftover '(' and returns a mismatch
			case '(':
				return "mismatch";
		}
	}
	//Checks if there is a leftover '(' and returns a mismatch
	else if (oprand == '(')
		return "mismatch";
	//If the oprator stacks size is less than 2, then the expression is 
	//invalid.
	else
		return "invalid-expr";
	return "ok";
}

/********************************************************************************
 * isDigit
 * 
 * isDigit checks weather the character is a digit or not.
*******************************************************************************/
bool isDigit(char x)
{
	if (x < '0' || x > '9')
		return 0;
	return 1;
}