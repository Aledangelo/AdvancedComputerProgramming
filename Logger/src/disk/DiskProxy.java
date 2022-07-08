package disk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import interfaces.ILogger;

public class DiskProxy implements ILogger {
    private DatagramSocket socket;
    private int port;

    public DiskProxy(DatagramSocket socket, int port) {
        this.socket = socket;
        this.port = port;
    }

    @Override
    public void setData(int data) {
        String msg = new String("setData&" + data);

        try {
            DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getLocalHost(), this.port);
            this.socket.send(request);

            byte[] ack = new byte[65508];
            DatagramPacket response = new DatagramPacket(ack, ack.length);
            this.socket.receive(response);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
