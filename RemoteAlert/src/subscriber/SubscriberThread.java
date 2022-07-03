package subscriber;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaces.ISubscriber;

public class SubscriberThread extends Thread {
    private Socket socket;
    private ISubscriber subscriber;

    public SubscriberThread(Socket socket, ISubscriber subscriber) {
        this.socket = socket;
        this.subscriber = subscriber;
    }

    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
            DataInputStream in = new DataInputStream(this.socket.getInputStream());

            String method = in.readUTF();

            if(method.equals("notify")) {
                int criticality = in.readInt();
                this.subscriber.notifyAlert(criticality);
                out.writeUTF("ack");    // ack
            } else {
                System.out.println("[SubscriberThread] Invalid Method!");
            }

            in.close();
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
