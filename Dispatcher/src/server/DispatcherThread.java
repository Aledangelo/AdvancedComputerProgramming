package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import dispatcher.Dispatcher;

public class DispatcherThread extends Thread {
    private Socket socket;
    private Dispatcher dispatcher;

    public DispatcherThread(Socket s, Dispatcher d) {
        this.socket = s;
        this.dispatcher = d;
    }

    public void run() {
        System.out.println ("[DispatcherThread] run thread!");
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String msg = in.readUTF();
            System.out.println("[DISPATCHER] Received cmd: " + msg);

            switch(msg) {
                case "sendCmd":
                    int cmd = in.readInt();
                    System.out.println("[DISPATCHER] sendCmd: " + cmd);
                    dispatcher.sendCmd(cmd);
                    out.writeUTF("ack");
                    break;
                case "getCmd":
                    int c = dispatcher.getCmd();
                    System.out.println("[DISPATCHER] getCmd: " + c);
                    out.writeInt(c);
                    break;
                default:
                    System.out.println("[DISPATCHER] Invalid Command");
                    break;
            }

            in.close();
            out.close();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
