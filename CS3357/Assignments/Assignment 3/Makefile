CC=gcc
CFLAGS=-Wall -g -c
LFLAGS=-Wall -g
PFLAG=-pthread
NCFLAG=-lncurses

all: clean ircc

ircc: tcp_sockets.o connection_messages.o channel_messages.o messages.o send_message.o receive_message.o gui.o process_input.o ircc.o tokenize.o
	$(CC) $(LFLAGS) -o $@ $^ $(PFLAG) $(NCFLAG)

ircc.o: ircc.c ircc.h
	$(CC) $(CFLAGS) -o $@ $<

receive_message.o: receive_message.c receive_message.h command_replies.h error_replies.h
	$(CC) $(CFLAGS) -o $@ $<

send_message.o: send_message.c send_message.h
	$(CC) $(CFLAGS) -o $@ $<

messages.o: messages.c messages.h
	$(CC) $(CFLAGS) -o $@ $<

channel_messages.o: channel_messages.c channel_messages.h
	$(CC) $(CFLAGS) -o $@ $<

connection_messages.o: connection_messages.c connection_messages.h
	$(CC) $(CFLAGS) -o $@ $<

tcp_sockets.o: tcp_sockets.c tcp_sockets.h
	$(CC) $(CFLAGS) -o $@ $<

gui.o: gui.c gui.h
	$(CC) $(CFLAGS) -o $@ $< $(NCFLAG)

process_input.o: process_input.c process_input.h
	$(CC) $(CFLAGS) -o $@ $<

tokenize.o: tokenize.c tokenize.h
	$(CC) $(CFLAGS) -o $@ $<

clean:
	rm -f *.o *.h.gch ircc