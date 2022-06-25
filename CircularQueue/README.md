# Circular Queue

There are two types of threads in the application (**producer** and **consumer**):
* **producer**: they put values in a queue of limited size with circular management;
* **consumer**: take the data from the buffer

## Queue Management
* entries are made in the queue;
* the samples are taken from the head of the queue;

## Constraints
* consumers cannot check out from an empty queue;
* producers cannot proceed with the insertion if the queue is full;
* withdrawals/insertions are made in mutual exclusion;