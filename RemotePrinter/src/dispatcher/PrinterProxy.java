package dispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaces.IPrinter;

public class PrinterProxy implements IPrinter {
    private String ip;
    private int port;

    public PrinterProxy(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public boolean print(String docName) {
        boolean check = false;

        try {
            Socket socket = new Socket(InetAddress.getByName(ip), port);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.writeUTF("print");
            out.writeUTF(docName);

            check = in.readBoolean();

            System.out.println("[PrinterProxy] print(" + docName + ") returns " + check);

            in.close();
            out.close();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return check;
    }

}
