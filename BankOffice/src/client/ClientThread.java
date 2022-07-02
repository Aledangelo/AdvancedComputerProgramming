package client;

import java.rmi.RemoteException;
import java.util.Random;

import interfaces.IBOfficeManager;

public class ClientThread extends Thread {
    private  int R;
    private IBOfficeManager officeManager;

    public ClientThread(int r, IBOfficeManager om) {
        this.R = r;
        this.officeManager = om;
    }

    public void run() {
        Random rnd = new Random();
        int idClient;
        boolean check;
        try {
            for (int i = 0; i < R; i++) {
                Thread.sleep(1000 + rnd.nextInt(2001));

                idClient = 1 + rnd.nextInt(100);

                check = officeManager.submitRequest(idClient);

                if (check) {
                    System.out.println("[ClientThread] Sent request with id: " + idClient);
                } else {
                    System.out.println("[ClientThread] Something went wrong!");
                }
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }

}
