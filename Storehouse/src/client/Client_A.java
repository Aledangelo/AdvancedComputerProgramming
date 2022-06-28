package client;

public class Client_A {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy(7070);
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new ClientThread("deposit", clientProxy);
            threads[i].start();
        }

        for (int i = 0; i < 5; i++) {
            try {
                threads[i].join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
