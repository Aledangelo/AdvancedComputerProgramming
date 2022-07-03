package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaces.IPrinter;

public class PrinterImpl extends UnicastRemoteObject implements IPrinter {
    private Lock lock;

    public PrinterImpl() throws RemoteException {
        this.lock = new ReentrantLock();
    }

    @Override
    public boolean print(String docName) throws RemoteException {
        boolean check = false;

        if (!lock.tryLock()) {
            System.out.println("[Printer] Printer busy");
            return check;
        }

        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("./print.txt", true)));

            System.out.println("[Printer] Saving " +  docName + " on file");
            printWriter.println("File: " + docName);
            printWriter.flush();

            check = true;

            printWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return check;
    }
}
