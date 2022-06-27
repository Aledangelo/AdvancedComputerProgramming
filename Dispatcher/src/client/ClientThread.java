package client;

import java.util.Random;

import dispatcher.Dispatcher;


public class ClientThread extends Thread {
    Dispatcher dispatcher;

    public ClientThread(Dispatcher d) {
        this.dispatcher = d;
    }

    public void run() {
        Random rnd = new Random();

        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(2000 + rnd.nextInt(2001));
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            int cmd = rnd.nextInt(4);
            System.out.println("[T. Client] Sending cmd: " + cmd);
            dispatcher.sendCmd(cmd);
        }
    }
}
