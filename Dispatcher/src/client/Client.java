package client;

public class Client {
    public static void main(String[] args) {
        ClientProxy d = new ClientProxy(7070);
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new ClientThread(d);
            threads[i].start();
        }

        for(int j = 0; j < 5; j++) {
            try {
                threads[j].join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
