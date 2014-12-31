#include <stdio.h>
#include <stdlib.h>
#include <getopt.h>
#include <syslog.h>
#include <stdbool.h>

int main(int argc, char** argv)
{
	int c, option_index = 0;
	bool u = 0;
	char* username;
	openlog("lab2", LOG_PERROR | LOG_PID | LOG_NDELAY, LOG_USER);

	static struct option long_options[] =
	{
		{"verbose", no_argument, 0, 'v'},
		{"debug", no_argument, 0, 'd'},
		{"username", required_argument, 0, 'u'},
		{0, 0, 0, 0}
	};

	setlogmask(LOG_UPTO(LOG_WARNING));

	while ((c = getopt_long(argc, argv, "vdu:", long_options, &option_index))!=-1)
	{
		switch (c)
		{
			case 'v':
				setlogmask(LOG_UPTO(LOG_INFO));
				break;
			case 'd':
				setlogmask(LOG_UPTO(LOG_DEBUG));
				break;
			case 'u':
				u = 1;
				username = optarg;
				break;
		}
	}

	if(u)
		syslog(LOG_INFO, "Your username is %s.", username);
	else
		syslog(LOG_WARNING, "No username specified.");

	syslog(LOG_DEBUG, "Hello world");
	closelog();
}