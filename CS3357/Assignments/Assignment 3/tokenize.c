#include "tokenize.h"

//*** tokenize ***
// function takes in an input string and tokenizes
// it by spaces. returns the number of tokens in string
int tokenize(char *input, char **tokens)
{
    int n=0, spaceFlag=1, i=0;

    while(input[i]!=0)
    {
        if(input[i]!=' ') spaceFlag=0;
        i++;
    }
    if(spaceFlag) return 0;

    tokens[n]=strtok(input, " ");
    while(tokens[n] && n<MAX_TOKENS) tokens[++n]=strtok(NULL, " ");

    return n;
}