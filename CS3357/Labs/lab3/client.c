#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
 
struct addrinfo* get_sockaddr(const char* hostname, const char* port)
{
  struct addrinfo hints;
  struct addrinfo* results;
  int retval;
 
  memset(&hints, 0, sizeof(struct addrinfo));
 
  hints.ai_family = AF_INET;        // Return socket addresses for the server's IPv4 addresses
  hints.ai_socktype = SOCK_STREAM;  // Return TCP socket addresses
 
  retval = getaddrinfo(NULL, port, &hints, &results);
 
  if (retval != 0)
  {
    fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(retval));
    exit(EXIT_FAILURE);
  }
 
  return results;
}
 
int open_connection(struct addrinfo* addr_list)
{
  struct addrinfo* addr;
  int sockfd;
 
  // Iterate through each addrinfo in the list; stop when we successfully
  // connect to one
  for (addr = addr_list; addr != NULL; addr = addr->ai_next)
  {
    // Open a socket
    sockfd = socket(addr->ai_family, addr->ai_socktype, addr->ai_protocol);
 
    // Try the next address if we couldn't open a socket
    if (sockfd == -1)
      continue;
 
    // Stop iterating if we're able to connect to the server
    if (connect(sockfd, addr->ai_addr, addr->ai_addrlen) != -1)
      break;
  }
 
  // Free the memory allocated to the addrinfo list
  freeaddrinfo(addr_list);
 
  // If addr is NULL, we tried every addrinfo and weren't able to connect to any
  if (addr == NULL)
  {
    perror("Unable to connect");
    exit(EXIT_FAILURE);
  }
  else
  {
    return sockfd;
  }
 
}
 
int main(int argc, char** argv)
{
  if (argc>3)
  {
    int bytes_read;                 // Number of bytes read from the server
    char* msg = argv[3];            // Message to send
    strcat(msg, "\r\n");
    char buffer[strlen(msg) + 1];   // Buffer to store received message, leaving
                                    // space for the NULL terminator
   
    // Connect to the server
    struct addrinfo* results = get_sockaddr(argv[1], argv[2]);
    int sockfd = open_connection(results);
   
    // Send the message
    if (send(sockfd, msg, strlen(msg), 0) == -1)
    {
      perror("Unable to send");
      exit(EXIT_FAILURE);
    }
   
    // Read the echo reply
    bytes_read = recv(sockfd, buffer, sizeof(buffer), 0);
    
    if (bytes_read == -1)
    {
      perror("Unable to read");
      exit(EXIT_FAILURE);
    }
   
    // Add the terminating NULL character to the end and print it
    buffer[bytes_read] = '\0';
    printf("Data received: %s", buffer);
   
    // Close the connection
    close(sockfd);
   
    exit(EXIT_SUCCESS);
  }
  else
    printf("Please enter a Server hostname or IP address, a Server port, and a Message to send, respectively.\n");
}