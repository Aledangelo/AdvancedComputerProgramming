# Dispatcher

![alt text](https://raw.githubusercontent.com/Aledangelo/AdvancedComputerProgramming/main/Dispatcher/diagram.png)

Multithreaded server for dispatching commands to a remote device. The system consists of 3 entities:
* **Dispatcher**: it offer two services (**sendCmd** and **getCmd**). The void sendCmd (int command) service allows the dispatcher to be sent the command that will be executed on the device; command identifies a specific operation on the device requested by the Client. The Dispatcher inserts the command received in a circular queue of size 5, waiting for it to be subsequently picked up by Actuator through the invocation of int getCmd (). Access to the queue must be done with the constraints of the producer-consumer.
* **Client**: generates 5 threads, each of which, at the end of a time of t seconds (with t chosen at random between 2 and 4), invokes sendCmd, with command chosen at random among *0-read*, *1-write*, *2-configure*, *3-reset*. Each thread makes 3 requests.
* **Actuator**: Call getCmd () every second; getCmd extracts the command from the head of the queue and returns it to Actuator. If the queue is empty, the getCmd request is put on hold. The command returned by getCmd is printed on the screen and appended to the cmdlog.txt file.

**The system is implemented using sockets and the proxy / skeleton pattern**