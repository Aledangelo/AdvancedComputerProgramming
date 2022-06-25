package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import service.Counter;

public class CounterProxy implements Counter {
    
    private DatagramSocket sock;

    // Methods to interact with server
    public CounterProxy() {
        try {
            sock = new DatagramSocket();
        } catch(SocketException e) {
            e.printStackTrace();
        }
    }

    public void setCount(String id, int n) {
        String msg = new String("setCount#" + id + "#" + n + "#");

        try {
            DatagramPacket req = new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getLocalHost(), 9000);   // port 9000
            sock.send(req);

            byte[] buff = new byte[100];
            DatagramPacket reply = new DatagramPacket(buff, buff.length);
            sock.receive(reply);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int sum(int v) {
        String msg = new String("sum#" + v + "#");
        int x = 0;

        try {
            DatagramPacket req = new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getLocalHost(), 9000);
            sock.send(req);

            byte[] buff = new byte[100];
            DatagramPacket reply = new DatagramPacket(buff, buff.length);
            sock.receive(reply);
            String replyMsg = new String(reply.getData(), 0, reply.getLength());
            x = Integer.valueOf(replyMsg).intValue();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return x;
    }

    public int inc() {
        String msg = new String("inc#");
        int x = 0;

        try {
            DatagramPacket req = new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getLocalHost(), 9000);
            sock.send(req);

            byte[] buff = new byte[100];
            DatagramPacket reply = new DatagramPacket(buff, buff.length);
            sock.receive(reply);

        } catch(IOException e) {
            e.printStackTrace();
        }

        return x;
    }
}
