package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import dispatcher.Dispatcher;

public abstract class DispatcherSkeleton implements Dispatcher {
    public void runSkeleton() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(7070);
            System.out.println("[Dispatcher Skeleton] Server listening on port 7070");
            while(true) {
                Socket socket = serverSocket.accept();
                DispatcherThread dt = new DispatcherThread(socket, this);
                dt.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
