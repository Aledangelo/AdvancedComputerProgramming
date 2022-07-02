package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.Semaphore;

import interfaces.IBankOffice;

public class BankOfficeImpl extends UnicastRemoteObject implements IBankOffice {
    private static final long serialVersionUID = 1L;

    private Semaphore maxReq;
    private Semaphore maxConcurrentReq;

    private PrintWriter printWriter;

    public BankOfficeImpl() throws RemoteException {
        this.maxReq = new Semaphore(5);
        this.maxConcurrentReq = new Semaphore(3);

        try {
            this.printWriter = new PrintWriter(new BufferedWriter(new FileWriter("./requests.txt", true)));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean serveRequest(int idClient) throws RemoteException {

        boolean flag = false;
        Random rnd = new Random();
        if (maxReq.tryAcquire() == false) {
            System.out.println("Maximum number of requests in the queue has been reached");
            return flag;
        }

        try {
            maxConcurrentReq.acquire();
            Thread.sleep(1 + rnd.nextInt(5));

            printWriter.println("ID: " + idClient);
            printWriter.flush();
            
            flag = true;
        } catch(InterruptedException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            maxConcurrentReq.release();
            maxReq.release();
        }

        return flag;
    }
}
