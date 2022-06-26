package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.RemoteCounter;

public class CounterSkeleton implements RemoteCounter {
    private RemoteCounter counter;

    public CounterSkeleton(RemoteCounter c) {
        counter = c;
    }

    public void runSkeleton() {
        ServerSocket serverSocket = null;
        Socket sock = null;

        try {
            serverSocket = new ServerSocket(6969);
            System.out.println("Server listening on port 6969");

            while(true) {
                sock = serverSocket.accept();
                SkeletonThread st = new SkeletonThread(sock, this);
                st.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int inc() { return counter.inc(); }
    public int get() { return counter.get(); }
    public int sqr() { return counter.sqr(); }
    public int sum(int val) { return counter.sum(val); }
}
