#include "gui.h"

/******************************************************************************
* NCURSES INITIALIZING AND CLOSING FUNCTIONS
******************************************************************************/

/******************************************************************************
* initWC
* 
* function initializes all the windows and their corresponding 
* variables required for the IRC user interface. information is stored
* in a sharedWindowContext struct which can later be used to call other 
* functions to update the same screen
******************************************************************************/
void initWC(WindowContext *wc, int debug)
{
	int maxy, maxx;

	/**************************************************************************
	* initscr();		//start ncurses
	* start_color();	//initialize colours
	* noecho();		//do not print letters until told
	* cbreak();		///disable line buffering
	* keypad(stdscr, TRUE);	//allows us to use special characters
	**************************************************************************/	
	initscr();
	start_color();
	noecho();
	cbreak();
	keypad(stdscr, TRUE);

	/**************************************************************************
	* initialize windows based on screen size
	**************************************************************************/	
	getmaxyx(stdscr, maxy,maxx);
	if(debug)
	{
		maxy = (int)(maxy * 2.0 / 3);
		initWindow(&(wc->debugWindow), 0, maxy, maxx-1, (int)(maxy/2)-1);
	}
	initWindow(&(wc->messageWindow), 10, 0, maxx - 21, maxy - 5);
	initWindow(&(wc->inputWindow), 0, maxy - 5, maxx - 1, 4);
	initWindow(&(wc->userWindow), maxx - 11, 0, 10, maxy - 5);
	initWindow(&(wc->infoWindow), -1, 0, 10, maxy - 5);
	scrollok(wc->inputWindow.window, TRUE);

	/**************************************************************************
	* initialize default values for channel info
	**************************************************************************/	
	strcpy(wc->channel, "None");
	strcpy(wc->topic, "No Current Topic");

	//initialize users
	/**************************************************************************
	* initialize default values for channel info
	**************************************************************************/	
	wc->numUsers=0;

	/**************************************************************************
	* initialize colours we will need
	**************************************************************************/	
	initColourPairs();

	/**************************************************************************
	* initialize buffers
	**************************************************************************/	
	wc->numMessLines=0;
	wc->numInfoLines=0;
	wc->numDebugLines=0;
	wc->messageStart=0;
	wc->debugStart=0;

	/**************************************************************************
	* set debugging 
	**************************************************************************/	
	wc->debug = debug;

	/**************************************************************************
	* draw all the windows after initializing
	**************************************************************************/	
	drawWindows(wc);
}

//*** initColourPairs ***
//function initializes all the colour pairs our display 
//will use
void initColourPairs()
{
	//border colours
	init_pair(BORDERLINE_PAIR, COLOR_RED, COLOR_BLACK);
	init_pair(BORDERCORNER_PAIR, COLOR_GREEN, COLOR_BLACK);
	//info colours
	init_pair(UPDOWN_PAIR, COLOR_RED, COLOR_WHITE);
	init_pair(USERS_PAIR, COLOR_MAGENTA, COLOR_BLACK);
	init_pair(NAMEDATE_PAIR, COLOR_CYAN, COLOR_BLACK);
	init_pair(HEADER_PAIR, COLOR_MAGENTA, COLOR_WHITE);
	//message colours
	init_pair(ENTER_PAIR, COLOR_GREEN, COLOR_BLACK);
	init_pair(EXIT_PAIR, COLOR_RED, COLOR_BLACK);
	init_pair(ERRORMESSAGE_PAIR, COLOR_RED, COLOR_WHITE);
	init_pair(SERV_PAIR, COLOR_CYAN, COLOR_BLACK);
	init_pair(REGMESSAGE_PAIR, COLOR_WHITE, COLOR_BLACK);
	init_pair(ERRORCODE_PAIR, COLOR_YELLOW, COLOR_BLACK);
	init_pair(REGCODE_PAIR, COLOR_WHITE, COLOR_BLACK);
	init_pair(WARN_PAIR, COLOR_YELLOW, COLOR_BLACK);
	init_pair(NICK_PAIR, COLOR_MAGENTA, COLOR_BLACK);
}

//*** initWindow ***
//function initializes a window with given location and width and height
void initWindow(Window *win, int x, int y,int width, int height)
{
	win->starty=y;
	win->startx=x;
	win->height=height;
	win->width=width;
	win->window = newwin(height-1, width-1, y+1, x+1);
}

//*** drawWindows ***
//draws all windows contained in the given WindowContext struct
//also draws static info text on screen like our names, the date
//the +/- scroll text and the 'members' title
void drawWindows(WindowContext *wc)
{
	char date[DATE_LEN];

	//clear everything on screen
	clear();	

	//draw borders around input window and message window
	createBorder(wc->inputWindow);
	createBorder(wc->messageWindow);

	//draw members title
	attron(COLOR_PAIR(USERS_PAIR)|A_UNDERLINE);
	mvprintw(0, wc->userWindow.startx+2, "Members");
	attroff(COLOR_PAIR(USERS_PAIR)|A_UNDERLINE);

	//draw up/down info
	attron(COLOR_PAIR(UPDOWN_PAIR));
	move(wc->messageWindow.height, wc->messageWindow.startx+wc->messageWindow.width);
	addch('+');
	move(wc->messageWindow.height-1, wc->messageWindow.startx+wc->messageWindow.width);
	addch('-');
	attroff(COLOR_PAIR(UPDOWN_PAIR));

	//draw date
	attron(COLOR_PAIR(NAMEDATE_PAIR));
	mvprintw(wc->inputWindow.starty+wc->inputWindow.height, 2, "%s", getDate(date));
	attroff(COLOR_PAIR(NAMEDATE_PAIR));
	//colour slashes back to red
	mvchgat(wc->inputWindow.starty+wc->inputWindow.height, 4, 1, 0,BORDERLINE_PAIR, NULL);
	mvchgat(wc->inputWindow.starty+wc->inputWindow.height, 7, 1, 0,BORDERLINE_PAIR, NULL);

	//draw names (zaidAlbirawi and saadAhmed)
	attron(COLOR_PAIR(NAMEDATE_PAIR));
	mvprintw(wc->inputWindow.starty+wc->inputWindow.height, wc->inputWindow.width-23, 
		"%s", "zaidAlbirawi saadAhmed");
	attroff(COLOR_PAIR(NAMEDATE_PAIR));
	attron(COLOR_PAIR(BORDERLINE_PAIR));
	mvprintw(wc->inputWindow.starty+wc->inputWindow.height, wc->inputWindow.width-11, "%s", "|");
	attroff(COLOR_PAIR(BORDERLINE_PAIR));
	
	//draw channel info 
	attron(COLOR_PAIR(BORDERLINE_PAIR));
	mvprintw(wc->messageWindow.height, wc->messageWindow.startx+2, "Channel:%s", wc->channel);
	attroff(COLOR_PAIR(BORDERLINE_PAIR));
	mvchgat(wc->messageWindow.height, wc->messageWindow.startx+10, 
		strlen(wc->channel), 0,SERV_PAIR, NULL);
	//draw topic info
	attron(COLOR_PAIR(USERS_PAIR));
	mvprintw(0, wc->messageWindow.startx+wc->messageWindow.width/2-strlen(wc->topic)/2, "%s", wc->topic);
	attroff(COLOR_PAIR(USERS_PAIR));
	refresh();


	//clear and refresh all other windows
	wclear(wc->userWindow.window);
	wclear(wc->infoWindow.window);
	wclear(wc->messageWindow.window);
	updateMessageWindow(wc);
	updateInfoWindow(wc);
	updateUserWindow(wc);
	wrefresh(wc->userWindow.window);
	wrefresh(wc->infoWindow.window);
	wrefresh(wc->messageWindow.window);

	//move cursor to back beginning of input window
	wmove(wc->inputWindow.window, 0,0);	
	wrefresh(wc->inputWindow.window);
}

//*** boxAround ***
//creates a border around a given window
void createBorder(Window win)
{
	int i;
	int y=win.starty;
	int x=win.startx;
	int h=win.height-1;
	int w=win.width-1;

	//upper left corner
	attron(COLOR_PAIR(BORDERCORNER_PAIR));
	mvaddch(y, x, '+');
  attroff(COLOR_PAIR(BORDERCORNER_PAIR));
 
 	//top line
 	attron(COLOR_PAIR(BORDERLINE_PAIR));
  for (i = 0;  i < w;  ++i)
      addch ('-');
 	attroff(COLOR_PAIR(BORDERLINE_PAIR));

 	//upper right corner
 	attron(COLOR_PAIR(BORDERCORNER_PAIR));
  addch ('+');  
  attroff(COLOR_PAIR(BORDERCORNER_PAIR));

  //side lines
  attron(COLOR_PAIR(BORDERLINE_PAIR));
  for(i = 0; i < h; ++i)
  {
    mvaddch(y+1+i, x, '|');
    mvaddch(y+1+i, x+w+1, '|');
  }
  attroff(COLOR_PAIR(BORDERLINE_PAIR));

  //bottom left corner
  attron(COLOR_PAIR(BORDERCORNER_PAIR));
  move(y+h+1,x);
  addch ('+');   
  attroff(COLOR_PAIR(BORDERCORNER_PAIR));

  //bottom line
  attron(COLOR_PAIR(BORDERLINE_PAIR));
  for (i = 0; i<w; ++i)
      addch ('-');
 	attroff(COLOR_PAIR(BORDERLINE_PAIR));

	//lower right corner
	attron(COLOR_PAIR(BORDERCORNER_PAIR));
	addch ('+'); 
	attroff(COLOR_PAIR(BORDERCORNER_PAIR));
}

//*** closeWC ***
//de-allocates all windows in the given WindowContext 
//struct and ends ncurses mode
void closeWC(WindowContext *wc)
{
	delwin(wc->inputWindow.window);
	delwin(wc->messageWindow.window);
	delwin(wc->infoWindow.window);
	delwin(wc->userWindow.window);
	endwin();
}

//**************************************************************************************************
// GET INPUT FUNCTION
//**************************************************************************************************

//*** getInput ***
//Gets input from the user and returns text if 'enter' is pressed.
//Also handles scrolling buttons (+/-) and backspace deletes
void getInput(WindowContext *wc, char *input)
{
	int ch, buffp=0;					//current character entered and the location in buffer
	char buffer[512 - 60];				//maximum input
	int entered=0;						//keep track of when 'enter' has been pressed
	memset(buffer, 0, 512 - 60);

	while(1)
	{

		//get and analyze character input
		ch=wgetch(stdscr);
		switch (ch)
		{

			//scroll up/down for debug messages
			case KEY_DOWN:
				//check to see whether we can scroll up 
				if(wc->debugStart==wc->numDebugLines-1) break;	
				wclear(wc->debugWindow.window);
				//shift debug screen up
				wc->debugStart++;								
				updateDebugWindow(wc);
				break;
			case KEY_UP:
				//check to see if we can scroll down
				if(wc->debugStart==0) break;			
				wclear(wc->debugWindow.window);	
				//shift debug screen down	
				wc->debugStart--;									
				updateDebugWindow(wc);
				break;

			//scroll up/down for messages window
			case '=':
			//check to see whether we can scroll up 
				if(wc->messageStart==wc->numMessLines-1) break; 
				wclear(wc->messageWindow.window);
				wclear(wc->infoWindow.window);
				wc->messageStart++;							//shift messages screen up
				updateMessageWindow(wc);				//update both message and info windows
				updateInfoWindow(wc);
				break;
			case '-':
				if(wc->messageStart==0) break;
				wclear(wc->messageWindow.window);
				wclear(wc->infoWindow.window);
				wc->messageStart--;
				updateMessageWindow(wc);
				updateInfoWindow(wc);
				break;

			//backspace key removes last character entered into buffer
			case KEY_BACKSPACE:
			case '\b':
				if(buffp!=0)
					buffer[--buffp]=0;
				break;

			//'enter' key copies buffer into 'input' string, which is returned
			case '\n':
				if(buffp!=0){
					strcpy(input, buffer);
					buffp=0;					
					buffer[buffp]=0;
					entered=TRUE;		
				}
				break;

			//all other characters and printed to buffer
			default:
				if(buffp<512-60-1)
				{
					wclear(wc->inputWindow.window);
					buffer[buffp++]=ch;	
				}
				break;
				
		}

		//clear the input window, draw text
		wclear(wc->inputWindow.window);
		mvwprintw(wc->inputWindow.window,0,0, "%s", buffer);
		wrefresh(wc->inputWindow.window);

		if(entered) return;
	}
}

//**************************************************************************************************
// PRINT FUNCTIONS
//**************************************************************************************************

//*** printGenericMessage ***
//This is the main function for printing to the message and info window
//all other functions rely on this for printing purposes
//Function takes in a message to print, the info field corresponding to the message
//and also type of message (for colouring purposes)
void printGenericMessage(WindowContext *wc, char *mess, char *info, int type)
{
	int numLines, i; 
	char infoMod[50]; //the info text to be printed

	//add lines to message buffer (word wrap and then add)
	numLines=putToBuffer(wc->messageBuffer, mess, &(wc->numMessLines), wc->messageWindow.width-2, type);
	
	//add lines to info area (after wordwrapping properly)
	strcpy(infoMod, info);
	for(i=0;i<numLines-1;i++) strcat(infoMod, "\n");
	// putToBuffer(wc->infoBuffer, infoMod, &(wc->numInfoLines), wc->infoWindow.width-2, type);
	putToBuffer(wc->infoBuffer, infoMod, &(wc->numInfoLines), 20, type);

	//scroll up if necessary
	if((wc->numMessLines-wc->messageStart) > wc->messageWindow.height-1)
		wc->messageStart+=numLines;

	//refresh screen
	updateMessageWindow(wc);
	updateInfoWindow(wc);
}

//*** putToBuffer ***
//helper function for printing methods
//takes a string and breaks it up into multiple lines (wordwrap)
//based on a cutoff value.
//returns the number of lines that the string was broken up into.
int putToBuffer(Line *buffer, char *str, int *start, int cutoff, int type)
{
	int lineStart=0, currChar=0, nearestSpace=0;
	int stringLength = strlen(str); 
	char string[1024];
	int numLines=0;

	strcpy(string, str);

	//go through the string character-by-character, until end of string reached
	while(currChar<stringLength)
	{

		//if character is  space, save this as the space nearest to the end of line
		if(string[currChar]==' ') nearestSpace=currChar;

		//if we reach end of line (based on cutoff), or a new line, then copy the string from previous
		//cutoff position to the nearest space, and save it as a new Line. 
		//the character after that space will be used as the next start-line position
		if((currChar-lineStart)==cutoff || string[currChar]=='\n')
		{
			if(string[currChar]=='\n') nearestSpace=currChar; 
			
			//case where there are no spaces in the string
			//thus we need to wrap on the last character
			if(nearestSpace==lineStart){

				//copy te string up to current location
				memcpy(buffer[*start].string, &(string[lineStart]), cutoff);
				buffer[(*start)].string[cutoff+1]='\0';

				//start new line at next position
				lineStart=currChar+1;
			}

			//case where there are spaces in the string
			//we can word wrap 
			else
			{

				//put a string terminator at the space (represents a 'cut' in the string)
				string[nearestSpace]=0;		
					
				//store the line as a new Line in the Line buffer 
				strcpy(buffer[(*start)].string, &(string[lineStart]));
				
				//start new line analysis from the character after the space
				lineStart=nearestSpace+1;
				currChar=nearestSpace;
				nearestSpace++;
			}

			buffer[(*start)++].type=type;	//update this line's type
			numLines++;								//update number of lines
			
		}
		currChar++;	//go to next character
	}

	//the last line is not accounted for in the previous loop
	//this takes care of it
	buffer[*start].type=type;
	strcpy(buffer[(*start)++].string, &(string[lineStart]));

	return numLines+1;
}

//*** printMessage ***
//prints a regular message onto the screen
//represents a message sent by users in the chat
void printMessage(WindowContext *wc, char *mess, char *info)
{
	char temp[MAX_USERNAME];
	strcpy(temp, info);

	//if info field is too long, trim it down
	// if(strlen(temp)>8){
	// 	temp[6]='_';
	// 	temp[7]='\0';
	// }

	printGenericMessage(wc, mess, info, REG_TYPE);
}

//*** printErrorMessage ***
//prints message to screen represented in 'error' format
void printErrorMessage(WindowContext *wc, char *mess)
{
	printGenericMessage(wc, mess, "error", ERR_TYPE);
}

//*** printWarningMessage ***
//prints message to screen represented in 'warning' format
void printWarningMessage(WindowContext *wc, char *mess)
{
	printGenericMessage(wc, mess, "warning", WARN_TYPE);
}

//*** printServerMessage ***
//prints message to screen representative of a server message
void printServerMessage(WindowContext *wc, char *mess)
{
	printGenericMessage(wc, mess, "server", SERV_TYPE);
}

//*** printEnterMessage ***
//adds a user to the users panel, and prints a 
//user-entered message
void printEnterMessage(WindowContext *wc, char *user)
{
		
	char message[100];	

	//add user to side pane
	addUser(wc, user);

	//show appropriate message on screen
	strcpy(message, user);
	strcat(message, " has entered the lobby");
	printGenericMessage(wc, message, "-->", ENTER_TYPE);
}

//*** printLeaveMessage ***
//removes a user from the users panel, and prints a 
//user-left message
void printLeaveMessage(WindowContext *wc, char *user, char* msg)
{
	char message[512 - 60];

	//remove user from side pane
	removeUser(wc, user);

	//show appropriate message on screen
	strcpy(message, user);
	strcat(message, " has left the lobby: ");
	strcat(message, msg);
	printGenericMessage(wc, message, "<--", EXIT_TYPE);
}

//*** printNickChangeMessage ***
//changes the nickname of a person to a new nickname
//updates all the panes and prints a message to show the change has happened
void printNickChangeMessage(WindowContext *wc, char *oldname, char *newname)
{
	char text[200];	

	//change the username on all the current panes
	changeUserName(wc, oldname, newname);

	//print nickname change message
	sprintf(text, "%s has changed nickname to %s", oldname, newname);
	printGenericMessage(wc, text, "---", NICK_TYPE);
}

//*** printDebugMessage ***
//prints a message to the debug portion of the screen
//if the debug flag is set (-d)
void printDebugMessage(WindowContext *wc, char *message)
{
	
	//check if debug flag is set
	if(!wc->debug) return;	

	//add lines to message buffer
	putToBuffer(wc->debugBuffer, message, &(wc->numDebugLines), wc->debugWindow.width-2, DEBUG_TYPE);

	//scroll up if necessary
	if((wc->numDebugLines-wc->debugStart) > wc->debugWindow.height-1)
		wc->debugStart++;

	//refresh screen
	updateDebugWindow(wc);
}

//**************************************************************************************************
// WINDOWS UPDATE FUNCTIONS
//**************************************************************************************************

//*** updateMessageWindow ***
//updates the message window
//prints a portion of the message buffer onto the window, based on how much
//it should be scrolled up/down
void updateMessageWindow(WindowContext *wc)
{
	int i=0;

	//clear all text and move cursor to top of window
	wclear(wc->messageWindow.window);
	wmove(wc->messageWindow.window, 0, 0);

	//start printing at the location of where the messages should be printed from
	//this is defined by how much the window has been 'scrolled'
	while((i+wc->messageStart)<wc->numMessLines && i<wc->messageWindow.height-1)
	{

		//format messages consucive to their types (type of Line)
		switch(wc->messageBuffer[i+wc->messageStart].type)
		{
			case REG_TYPE:
				wattron(wc->messageWindow.window, COLOR_PAIR(REGMESSAGE_PAIR));
				break;
			case ENTER_TYPE:
				wattron(wc->messageWindow.window, COLOR_PAIR(ENTER_PAIR)|A_BOLD);
				break;
			case EXIT_TYPE:
				wattron(wc->messageWindow.window, COLOR_PAIR(EXIT_PAIR)|A_BOLD);
				break;
			case SERV_TYPE:
				wattron(wc->messageWindow.window, COLOR_PAIR(SERV_PAIR)|A_BOLD);
				break;
			case ERR_TYPE:
				wattron(wc->messageWindow.window, COLOR_PAIR(ERRORMESSAGE_PAIR));
				break;
			case WARN_TYPE:
				wattron(wc->messageWindow.window, COLOR_PAIR(WARN_PAIR)|A_BOLD);
				break;
			case NICK_TYPE:
				wattron(wc->messageWindow.window, COLOR_PAIR(NICK_PAIR)|A_BOLD);
				break;
			default: break;
		}

		//print the message, reset formatting, go to next line
		wprintw(wc->messageWindow.window, "%s\n", wc->messageBuffer[i+wc->messageStart].string);
		wstandend(wc->messageWindow.window);
		i++;
	}
	
	//update the screen and move the cursor back to starting position of input window
	wrefresh(wc->messageWindow.window);
	wmove(wc->inputWindow.window, 0,0);	
	wrefresh(wc->inputWindow.window);

}

//*** updateInfoWindow ***
//updates the info window
//prints a portion of the info buffer onto the window, based on how much
//it should be scrolled up/down. this is the same scrolling as for messages
//thus, same variables are used
void updateInfoWindow(WindowContext *wc)
{
	int i=0;
	char infoMessage[20];

	//clear all text and move cursor to top of window
	wclear(wc->infoWindow.window);
	wmove(wc->infoWindow.window, 0, 0);

	//start printing at the location of where the info should be printed from
	//this is defined by how much the window has been 'scrolled'. same scrolling
	//as the messages window, thus, same variables are used
	while((i+wc->messageStart)<wc->numMessLines && i<wc->messageWindow.height-1)
	{

		//if info message is too big, then trim it down
		strcpy(infoMessage, wc->infoBuffer[i+wc->messageStart].string);
		if(strlen(infoMessage)>8){
			infoMessage[6]='_';
			infoMessage[7]='\0';
		}

		//format messages consucive to their types (type of Line)
		switch(wc->infoBuffer[i+wc->messageStart].type)
		{
			case REG_TYPE:
				wattron(wc->infoWindow.window, COLOR_PAIR(REGCODE_PAIR));
				break;
			case SERV_TYPE:
				wattron(wc->infoWindow.window, COLOR_PAIR(SERV_PAIR));
				break;
			case ENTER_TYPE:
				wattron(wc->infoWindow.window, COLOR_PAIR(ENTER_PAIR));
				break;
			case EXIT_TYPE:
				wattron(wc->infoWindow.window, COLOR_PAIR(EXIT_PAIR));
				break;
			case ERR_TYPE:
				wattron(wc->infoWindow.window, COLOR_PAIR(ERRORCODE_PAIR));
				break;
			case WARN_TYPE:
				wattron(wc->infoWindow.window, COLOR_PAIR(WARN_PAIR));
				break;
			case NICK_TYPE:
				wattron(wc->infoWindow.window, COLOR_PAIR(NICK_PAIR));
				break;
			default: break;
		}

		//print the info message, reset formatting, go to next line
		wprintw(wc->infoWindow.window, "%*s\n", 8, infoMessage);
		wstandend(wc->infoWindow.window);
		i++;
	}
	
	//update the screen and move the cursor back to starting position of input window	
	wrefresh(wc->infoWindow.window);
	wmove(wc->inputWindow.window, 0,0);	
	wrefresh(wc->inputWindow.window);
}

//*** updateUserWindow ***
//updates the member pane on the right side
//fills the window based on the users in the current chat
void updateUserWindow(WindowContext *wc)
{
	int i;
	char users[MAX_USERS*MAX_USERNAME], tempUser[MAX_USERNAME];
	users[0]=0;

	//add all users to one string
	for(i=0;i<(wc->numUsers);i++)
	{
		strcpy(tempUser, wc->users[i].name);

		//if name is longer than 8, we need to trim it
		if(strlen(tempUser)>8)
		{
			tempUser[6]='_';
			tempUser[7]='\0';
		}

		//add user to user string
		strcat(users, tempUser);
		strcat(users, "\n");
	}

	//print string onto 
	wclear(wc->userWindow.window);
	mvwprintw(wc->userWindow.window, 0, 0, users);
	wrefresh(wc->userWindow.window);
}

//*** updateDebugWindow ***
//updates the debugging window on the bottom 1/3 of the screen
//prints only part of the debug buffer that should be displayed 
//bsaed on how much has been scrolled and how much can fit
void updateDebugWindow(WindowContext *wc)
{
	int i=0;

	//clear window and move cursor to beginning of window
	wclear(wc->messageWindow.window);
	wmove(wc->debugWindow.window, 0, 0);

	//go through all the lines that need to be displayed on window
	//and print them
	while((i+wc->debugStart)<wc->numDebugLines && i<wc->debugWindow.height-1)
	{
		wprintw(wc->debugWindow.window, "%s\n", wc->debugBuffer[i+wc->debugStart].string);
		i++;
	}

	//update the window and move cursor back to beginning of 
	//input window
	wrefresh(wc->debugWindow.window);
	wmove(wc->inputWindow.window, 0,0);	
	wrefresh(wc->inputWindow.window);
}

//*** updateChannel ***
//updates the channel name on the bottom of the messagewindow
void updateChannel(WindowContext *wc, char *newChan)
{
	strcpy(wc->channel, newChan);
	drawWindows(wc);
}

void updateTopic(WindowContext *wc, char *newTopic)
{
	strcpy(wc->topic, newTopic);
	drawWindows(wc);
}

//**************************************************************************************************
// USER UPDATE FUNCTIONS
//**************************************************************************************************

//*** changeUserName ***
//changes the given username to a new username in all the panes
//and updates the panes to reflect the changes
void changeUserName(WindowContext *wc, char *oldname, char *newname)
{
	int i;

	//change the name in the names panel
	for(i = 0; i < wc->numUsers; i++)
	{
		if (wc->users[i].name[0] == '@')
		{
			if(!strcmp(wc->users[i].name + 1, oldname))
			{
				char* at_new = (char*)malloc(strlen(newname)+ 1);
				at_new[0] = '@';
				at_new[1] = '\0';
				strcpy(wc->users[i].name, strcat(at_new, newname));
				free(at_new);
			}
			continue;
		}
		if(!strcmp(wc->users[i].name, oldname))  
			strcpy(wc->users[i].name, newname);
	}

	//change the name in the info panel
	for(i = 0; i < wc->numInfoLines; i++)
		if(!strcmp(wc->infoBuffer[i].string, oldname))
			strcpy(wc->infoBuffer[i].string, newname);

	//update the windows to reflect the change
	updateUserWindow(wc);
	updateInfoWindow(wc);
}

//*** addUser ***
//adds a new user to the side pane
void addUser(WindowContext *wc, char *user)
{
	strcpy(wc->users[(wc->numUsers)++].name, user);
	updateUserWindow(wc);
}

//*** removeUser ***
//removes a user from the side pane
void removeUser(WindowContext *wc, char *user)
{
	int i, j;
	//find user to remove
	for(i = 0; i < wc->numUsers; i++)
	{
		j = 0;
		if (wc->users[i].name[0] == '@') 
			j = 1;
		if(!strcmp(wc->users[i].name + j, user))
			break;
	}

	//shift all names in the list to cover up the removed name
	for ( ; i < wc->numUsers - 1; i++)
		wc->users[i] = wc->users[i + 1];

	wc->numUsers--;
	updateUserWindow(wc);
}

//*** populateUsers ***
//takes in a list of users as a string (separated by space)
//and adds each one to the members list, in the side pane
void populateUsers(WindowContext *wc, char *users)
{
	int i;
	char *usersTokenized[MAX_USERS], numUsers;

	//clear all current members
	wc->numUsers=0;	

	//get tokens from input
	numUsers=tokenize(users, usersTokenized);

	//add each user to the side pane
	for(i=0;i<numUsers;i++)
		printEnterMessage(wc, usersTokenized[i]);
}

//**************************************************************************************************
// MISCELLANEOUS FUNCTIONS
//**************************************************************************************************

//*** getDate ***
//returns the date in mm/dd/yyyy format
char *getDate(char *date)
{
	time_t t = time(0);							//get current time in seconds
	struct tm* lt = localtime(&t);	//convert to date-time format

	//extract relevant information in mm/dd/yyyy format
	sprintf(date, "%02d/%02d/%04d", lt->tm_mon + 1,lt->tm_mday, lt->tm_year +YEAR_BEGIN);

	return date;
}