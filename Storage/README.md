# Storage
Multithreaded server based. The system implements an electronic item repository and is composed of two entities:
* **Client**: Each client can send N messages on the Request queue. Each message contains two pieces of information: type of request and article id.
* **Store**: It manages a circular queue. The queue size is 10. When a new message arrives, it creates a new thread that extracts the information from the message. If the message is of type "insert" the id is inserted in the queue, if it is of type "withdraw" the id is taken and sent to the client.
