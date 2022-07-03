package printer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaces.IPrinter;

public class PrinterThread extends Thread {
    private Socket socket;
    private IPrinter printer;

    public PrinterThread(Socket socket, IPrinter printer) {
        this.socket = socket;
        this.printer = printer;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String method = in.readUTF();
            if(method.equals("print")) {
                String docName = in.readUTF();
                out.writeBoolean(printer.print(docName));
                System.out.println("[Printer Thread] Printing " + docName);
            } else {
                System.out.println("[Printer Thread] Invalid Method!");
            }

            in.close();
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
