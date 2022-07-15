# Printer JMS - Proxy/Skeleton UDP
System for managing a remote printer based on JMS and socket. The system consists of:
* **Client**: Generate print requests. A request consists in sending a JMS message from Client to Dispatcher via the "PrintRequest" JMS Destination. The request is characterized by 1) docName (String) and 2) port (int), which is the port number of UDP socket. Use the JMS MapMessages to send the docName-port pair to the Dispatcher. The Client generates 5 messages. For each message, docName is generated randomly by appending an integer between 0 and 100 to the string “doc”; port is fixed, and must be specified as a prompt when starting the Client.
* **Dispatcher**: Implement JMS asynchronous reception. Upon receipt of each MapMessage, the Dispatcher JMS listener starts a thread that extracts the docName and port from message. The thread shows docName on the screen, uses proxy to invokes *void printDoc(String)* method specified by the IPrinter interface, passing docName.
* **Printer**: Provides the remote IPrinter interface and its *void printDoc(String)* method. The printDoc method shows docName on the screen and stores it on file; the invocation of *printDoc* lasts 5 seconds. The printDoc method is executed in mutual exclusion; the port number of Printer is specified by a prompt when starting the program.