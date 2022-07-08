package logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import interfaces.ILogger;

public class LoggerThread extends Thread {
    private DatagramSocket socket;
    private DatagramPacket datagramPacket;
    private ILogger logger;

    public LoggerThread(DatagramSocket socket, DatagramPacket datagramPacket, ILogger logger) {
        this.socket = socket;
        this.datagramPacket = datagramPacket;
        this.logger = logger;
    }

    public void run() {
        String msg = new String(this.datagramPacket.getData(), 0, this.datagramPacket.getLength());

        StringTokenizer stringTokenizer = new StringTokenizer(msg, "&");
        String method = stringTokenizer.nextToken();
        
        switch(method) {
            case "setData":
                int data = Integer.valueOf(stringTokenizer.nextToken()).intValue();
                this.logger.setData(data);
                String ack = new String("ack");
                DatagramPacket reply = new DatagramPacket(ack.getBytes(), ack.getBytes().length, this.datagramPacket.getAddress(), this.datagramPacket.getPort());

                try {
                    this.socket.send(reply);
                } catch(IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("[LoggerThread] Invalid Method!");
                break;
        }
    }
}
