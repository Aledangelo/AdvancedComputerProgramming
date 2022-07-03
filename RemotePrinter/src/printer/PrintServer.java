package printer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;
import interfaces.IPrinter;

public class PrintServer {
    public static void main(String[] args) {
        try {
            int port = Integer.valueOf(args[0]).intValue();

            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher dispatcher = (IDispatcher)rmiRegistry.lookup("myDispatcher");

            IPrinter printer = new PrinterImpl();
            PrinterSkeleton printerSkeleton = new PrinterSkeleton(printer, port);

            dispatcher.addPrinter("127.0.0.1", port);
            System.out.println("[Printer] I registered myself to dispatcher");

            printerSkeleton.runSkeleton();

        } catch(NotBoundException e) {
            e.printStackTrace();
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
