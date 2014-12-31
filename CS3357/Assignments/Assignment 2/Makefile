CC=gcc
CFLAGS=-Wall -g -c
LFLAGS=-Wall -g
PFLAG=-pthread

all: rftp rftpd

rftp: message.o udp_sockets.o udp_client.o connect.o connect_client.o client_go_back_n.o client_stop_and_wait.o rftp.o
	$(CC) $(LFLAGS) -o $@ $^ $(PFLAG)

rftpd: message.o udp_sockets.o udp_server.o connect.o connect_server.o server_go_back_n.o server_stop_and_wait.o rftpd.o
	$(CC) $(LFLAGS) -o $@ $^

rftp.o: rftp.c rftp.h
	$(CC) $(CFLAGS) -o $@ $<

rftpd.o: rftpd.c rftpd.h
	$(CC) $(CFLAGS) -o $@ $<

client_stop_and_wait.o: client_stop_and_wait.c client_stop_and_wait.h
	$(CC) $(CFLAGS) -o $@ $<

server_stop_and_wait.o: server_stop_and_wait.c server_stop_and_wait.h
	$(CC) $(CFLAGS) -o $@ $<

client_go_back_n.o: client_go_back_n.c client_go_back_n.h
	$(CC) $(CFLAGS) -o $@ $< $(PFLAG)

server_go_back_n.o: server_go_back_n.c server_go_back_n.h
	$(CC) $(CFLAGS) -o $@ $<

connect.o: connect.c connect.h
	$(CC) $(CFLAGS) -o $@ $<

connect_client.o: connect_client.c connect_client.h
	$(CC) $(CFLAGS) -o $@ $<

connect_server.o: connect_server.c connect_server.h
	$(CC) $(CFLAGS) -o $@ $<

udp_client.o: udp_client.c udp_client.h
	$(CC) $(CFLAGS) -o $@ $<

udp_server.o: udp_server.c udp_server.h
	$(CC) $(CFLAGS) -o $@ $<

udp_sockets.o: udp_sockets.c udp_sockets.h
	$(CC) $(CFLAGS) -o $@ $<

message.o: message.c message.h
	$(CC) $(CFLAGS) -o $@ $<

clean:
	rm -f *.o rftp rftpd