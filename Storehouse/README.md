# Storehouse
Multithreaded application for Store management. System consists of 3 types of entities:
* **Store**: it offers services specified by the IStore interface. The first, deposit (String article, int id), allows the deposit of an article. The deposit operation is characterized by the type of item (ie "laptop" or "smartphone") and an article id (random between 1 and 100). The Warehouse has 2 circular management queues. Each id is stored in the queue specified by the item parameter. Each queue is managed in mutual exclusion. If a queue is full, the deposit request is put on hold. Both queues have a size of 5. The int fetch service (String article) returns the id taken from the queue specified by the article parameter. If the queue is empty, the withdrawal operation is put on hold. The Warehouse saves the ID of the picked item on file.
* **Client A**: it generates 5 threads, each of which, at the end of a time of t seconds (with t chosen at random between 2 and 4), makes a request and deposits, with article and id selected at random. Each thread makes 3 requests.
* **Client B**: it generates 5 threads, each of which, at the expiry of a time of t seconds (with t chosen at random between 2 and 4), makes a request to fetch, with the article chosen at random. Each thread makes 3 requests.

**The system is implemented using sockets and the proxy / skeleton pattern.**
