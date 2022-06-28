package client;

import java.util.Random;

import store.IStore;

public class ClientThread extends Thread {
    private String command;
    private IStore store;

    public ClientThread(String c, IStore is) {
        this.command = c;
        this.store = is;
    }

    public void run() {
        System.out.println("[Client Thread] Starting - Cmd: " + command);
        Random rnd = new Random();
        
        switch(command) {
            case "deposit":
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(2000 + rnd.nextInt(2001));
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }

                    int id = 1 + rnd.nextInt(100);
                    int itm = rnd.nextInt(2);
                    String item;
                    if(itm == 0) {
                        item = new String("laptop");
                    } else {
                        item = new String("smartphone");
                    }
                    System.out.println("[Client Thread] Depositing: " + item + " : " + id);
                    store.deposit(item, id);
                }
                break;
            case "withdraw":
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(2000 + rnd.nextInt(2001));
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }

                    String item;
                    int itm = rnd.nextInt(2);
                    if(itm == 0) {
                        item = new String("laptop");
                    } else {
                        item = new String("smartphone");
                    }
                    System.out.println("[Client thread] Picked up: " + item + " : " + store.withdraw(item));
                }
        }
    }
}
