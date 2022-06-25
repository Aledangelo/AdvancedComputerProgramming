package client;

import service.Counter;

public class Client {
    public static void main(String args[]) {
        Counter counter = new CounterProxy();

        int x = 0;

        System.out.println("Set count to 10");
        counter.setCount("Client", 10);

        System.out.println("Sum 15 to count");
        x = counter.sum(15);
        System.out.println("Current count: " + x);

        System.out.println("Increment count");
        x = counter.inc();
        System.out.println("Current count: " + x);
    }
}
