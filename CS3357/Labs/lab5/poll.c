#include <poll.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "udp_sockets.h"
#include "udp_server.h"

int main()
{
  message_t* msg;   // Message read from the client
  host_t client;    // Client's address

  // Create a socket to listen on port 5000
  int sockfd = create_server_socket("5000");

  // Poll the socket for 10 seconds
  msg = receive_message_with_timeout(sockfd, 10000, &client);

  if (msg != NULL)
  {
    // Add NULL terminator and print the message
    msg->buffer[msg->length] = '\0';
    printf("Message received from %s: %s\n", client.friendly_ip, msg->buffer);

    free(msg);
  }
  else
  {
    puts("No message received in 10s.  Exiting.");
  }

  // Close the socket
  close(sockfd);

  exit(EXIT_SUCCESS);
}
