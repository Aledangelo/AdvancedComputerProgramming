package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import store.IStore;

public class ClientProxy implements IStore {
    private int port;

    public ClientProxy(int p) {
        this.port = p;
    }

    public void deposit(String item, int id) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), port);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.writeUTF("deposit");
            out.writeUTF(item);
            out.writeInt(id);

            in.readUTF(); // ack

            in.close();
            out.close();
            socket.close();
        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int withdraw(String item) {
        int x = 0;
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), port);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.writeUTF("withdraw");
            out.writeUTF(item);

            x = in.readInt();

            in.close();
            out.close();
            socket.close();
        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return x;
    }
}
