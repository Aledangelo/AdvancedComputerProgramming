package printer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

import interfaces.IPrinter;

public class PrinterSkeleton implements IPrinter {
    private IPrinter printer;
    private int port;

    public PrinterSkeleton(IPrinter printer, int port) {
        this.printer = printer;
        this.port = port;
    }

    public void runSkeleton() {
        try {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("[Printer Skeleton] Skeleton listening on port: " + port);

                while(true) {
                    Socket socket = serverSocket.accept();
                    PrinterThread pt = new PrinterThread(socket, this);
                    pt.start();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean print(String docName) throws RemoteException {
        return printer.print(docName);
    }
}
