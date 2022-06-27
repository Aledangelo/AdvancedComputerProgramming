package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import dispatcher.Dispatcher;

public class ClientProxy implements Dispatcher {
    
    private int port;
    
    public ClientProxy(int p) {
        this.port = p;
    }

    // Remote invocation of sendCmd()
    public void sendCmd(int cmd) {
        try {
            Socket sock = new Socket(InetAddress.getLocalHost(), this.port);
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            DataInputStream in = new DataInputStream(sock.getInputStream());

            out.writeUTF("sendCmd");
            out.writeInt(cmd);

            in.readUTF();   // Wait for server ack 

            out.close();
            in.close();
            sock.close();
        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Remote invocation of getCmd()
    public int getCmd() {
        int x = 0;

        try {
            Socket sock = new Socket(InetAddress.getLocalHost(), this.port);
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            DataInputStream in = new DataInputStream(sock.getInputStream());

            out.writeUTF("getCmd");

            x = in.readInt();   // Wait for command

            in.close();
            out.close();
            sock.close();
        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return x;
    }
}
