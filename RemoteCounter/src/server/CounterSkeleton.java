package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import service.Counter;

public abstract class CounterSkeleton implements Counter {
    
    public void runSkeleton() {
        try {
            DatagramSocket socket = new DatagramSocket(9000);
            System.out.println("[Counter Skeleton] Entering the main loop...");

            while(true) {
                byte[] buff = new byte[100];
                DatagramPacket req = new DatagramPacket(buff, buff.length);
                socket.receive(req);
                
                CounterWorker worker = new CounterWorker(socket, req, this);
                worker.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
