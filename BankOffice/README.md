# Bank Office Manager

The system implements a load balancing policy between branches, and is composed of 3 types of entities:
* **BankOffice**: offers the boolean service serveRequest(int idClient). Each service request lasts a randomly chosen time between 1 and 5 seconds. At the end of the request, the idClient is saved to a file. The branch, upon its launch, subscribes to the Manager.
    * **It can concurrently serve 3 requests.** Any further requests are put on hold; however, at most 2 requests can be queued while waiting for the BankOffice to serve a previous request. In case the request cannot be queued, BankOffice immediately returns false results. Successfully served requests end with a true outcome.
* **Manager**: takes charge of Client service requests via boolean submitRequest (int idClient) and routes them to a specific BankOffice. Manager has a list of BankOffice sorted according to the order of the subscriptions, and invokes each of them in sequence. The request is served by the first free BankOffice: at the end of the request, the Manager returns the true result to the Client. The Manager returns false if each BankOffice returns false.
* **Client**: it generates T threads, each of which, at the expiry of a time of t seconds (with t chosen at random between 1 and 3), makes a reservation request. Each client thread generates R requests. Set T equal to 10; R equal to 10. The idClient is generated randomly between 1 and 100.

**To use this project you need to run the rmiregistry command in the *bin* folder.**