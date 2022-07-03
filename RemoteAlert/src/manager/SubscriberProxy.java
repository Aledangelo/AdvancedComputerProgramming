package manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaces.ISubscriber;

public class SubscriberProxy implements ISubscriber {
    private int port;

    public SubscriberProxy(int port) {
        this.port = port;
    }

    @Override
    public void notifyAlert(int criticality) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), this.port);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.writeUTF("notify");
            out.writeInt(criticality);

            in.readUTF();

            System.out.println("[SubscriberProxy] notifyAlert(" + criticality + ")");

            in.close();
            out.close();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
