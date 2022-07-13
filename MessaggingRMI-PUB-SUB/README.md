# Messaging Pub-Sub
Pub-Sub messaging system based on Java RMI middleware technology. The system consists of 3 types of entities:
* **Publisher**: invokes the MessageServer *publish* method, which specifies the topic and content of the message. Both the topic and the message are strings.
* **Subscriber**: invokes the MessageServer *subscribe* method, which specifies the topic he is interested in and the SUB reference. This reference will be used by the MessageServer to notify the subscriber of any publication of messages associated with the topic of interest.
* **MessageServer**: remote object that responds to *publish/subscribe* requests. At each subscribe it stores the topic and the subscriber's reference. Each time *publish* is called, it scrolls the list of subscribed publishers and invokes the *onMessage* method of subscribers whose subscription topic coincides with that of the published message.

***onMessage* method is implemented by a remote object instantiated by the subscriber and contacted by the MessageServer according to a distributed callback mechanism.**
The **MessageServer** can concurrently manage 3 *publish*. A *publish*, when there are more than 3 concurrent requests, is queued. At most 2 *publish* can be put on hold. If there are already 2 pending, each further invocation of the *publish* method ends immediately with a **false** result.