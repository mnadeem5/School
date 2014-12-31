/********************************************************************************
 * stack.h
 * 
 * Computer Science 3357a
 * Assignment 1
 *
 * Author: Zaid Albirawi
 * Email: zalbiraw@uwo.ca
 * 
 * This header file is a data structure that will be used to solve the expressions
*******************************************************************************/

#include <stdlib.h>

//Struct for a node that will contain a value and a link to the previous node.
struct node
{
	//A union to reduce the overhead by a byte, value will hold the value of the 
	//node weather, its an integer or a char*.
	union Data
	{
		char* s;
		int i; 
	} value;
	//A pointer to the previous node.
	struct node* previous;
};

//Typedef that defines the stack, contains the stacks base, a link to the node on
//top of the stack, and the size of the stack.
typedef struct 
{
	struct node base;
	struct node* top;
	int size;
} stack;

//Functions prototypes.
void 	initStack(stack*);
void 	pushStr(stack*, char*);
void 	pushInt(stack*, int);
char* 	popStr(stack*);
int 	popInt(stack*);
char* 	peekStr(stack*);
int 	peekInt(stack*);

//Initilizes the stack.
void initStack(stack* stk)
{
	stk->top = &stk->base;
	stk->size = 0;
}

//Push function for a char*
void pushStr(stack* stk, char* value)
{
	struct node *push =  (struct node*)malloc(sizeof(struct node));
	push->value.s = value;
	push->previous = stk->top;
	stk->top = push;
	(stk->size)++;
	syslog(LOG_DEBUG, "Oprand push\t-->\t%s\n", value);
}

//Push function for an int
void pushInt(stack* stk, int value)
{
	struct node *push =  (struct node*)malloc(sizeof(struct node));
	push->value.i = value;
	push->previous = stk->top;
	stk->top = push;
	(stk->size)++;
	syslog(LOG_DEBUG, "Oprator push\t-->\t%d\n", value);
}

//Pop function for a char*
char* popStr(stack* stk)
{
	if (stk->size)
	{
		char* value = stk->top->value.s;
		struct node* pop = stk->top;
		stk->top = stk->top->previous;
		(stk->size)--;
		free(pop);
		syslog(LOG_DEBUG, "Oprand pop\t<--\t%s\n", value);
		return value;
	} 
	return NULL;
}

//Pop function for an int
int popInt(stack* stk)
{
	if (stk->size)
	{
		int value = stk->top->value.i;
		struct node* pop = stk->top;
		stk->top = stk->top->previous;
		(stk->size)--;
		free(pop);
		syslog(LOG_DEBUG, "Oprator pop\t<--\t%d\n", value);
		return value;
	} 
	return 0;
}

//Peek function for a char*
char* peekStr(stack* stk)
{
	if (stk->size)
		return stk->top->value.s;
	return NULL;
}

//Peek function for an int
int peekInt(stack* stk)
{
	if (stk->size)
		return stk->top->value.i;
	return 0;
}