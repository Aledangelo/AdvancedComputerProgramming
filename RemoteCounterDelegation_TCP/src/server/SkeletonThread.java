package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.RemoteCounter;

public class SkeletonThread extends Thread {
    Socket sock;
    RemoteCounter counter;

    public SkeletonThread(Socket s, RemoteCounter c) {
        this.sock = s;
        this.counter = c;
    }

    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(this.sock.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(this.sock.getInputStream()));

            // Read operation
            String op = in.readUTF();
            System.out.print("Operation: " + op);

            // Unmarshalling and method call
            switch(op) {
                case "sum":
                    int val = in.readInt();
                    out.writeInt(counter.sum(val));
                    out.flush();
                    break;
                case "inc":
                    out.writeInt(counter.inc());
                    out.flush();
                    break;
                case "get":
                    out.writeInt(counter.get());
                    out.flush();
                    break;
                case "sqr":
                    out.writeInt(counter.sqr());
                    out.flush();
                    break;
                default:
                    System.out.println("Invalid OP");
                    break;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
