package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import service.RemoteCounter;

public class CounterProxy implements RemoteCounter {
    
    private String host;
    private int port;

    // Constructor
    public CounterProxy(String h, int p) {
        this.host = h;
        this.port = p;
    }

    // Remote invocation of services
    public int get() {

        try {
            // Socket
            Socket sock = new Socket(host, port);
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));

            // Marshalling
            out.writeUTF("get");
            out.flush();

            // Server Response
            int x = in.readInt();
            
            // Colse Socket
            sock.close();

            return x;
        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int inc() {

        try {
            Socket sock = new Socket(host, port);
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));

            out.writeUTF("inc");
            out.flush();

            int x = in.readInt();

            sock.close();
            return x;
        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int sum(int value) {
        try {
            Socket sock = new Socket(host, port);
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));

            out.writeUTF("sum");
            out.writeInt(value);
            out.flush();

            int x = in.readInt();

            sock.close();
            return x;
        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int sqr() {
        try {
            Socket sock = new Socket(host, port);
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));

            out.writeUTF("sqr");
            out.flush();

            int x = in.readInt();

            sock.close();
            return x;
        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
