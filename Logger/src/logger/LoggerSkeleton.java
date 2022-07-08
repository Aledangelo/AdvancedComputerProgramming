package logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import interfaces.ILogger;

public class LoggerSkeleton implements ILogger {
    private int port;
    private ILogger logger;

    public LoggerSkeleton(int port, ILogger logger) {
        this.port = port;
        this.logger = logger;
    }

    public void runSkeleton() throws SocketException, IOException {
        DatagramSocket datagramSocket = new DatagramSocket(this.port);
        System.out.println("[SocketSkeleton] Listening on port: " + this.port);

        while(true) {
            byte[] buff = new byte[65508];
            DatagramPacket request = new DatagramPacket(buff, buff.length);
            datagramSocket.receive(request);
            LoggerThread loggerThread = new LoggerThread(datagramSocket, request, this);
            loggerThread.start();
        }
    }

    @Override
    public void setData(int data) {
        this.logger.setData(data);
    }
}
