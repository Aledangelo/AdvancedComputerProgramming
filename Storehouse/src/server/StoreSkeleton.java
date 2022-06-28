package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import store.IStore;

public abstract class StoreSkeleton implements IStore {
    public void runSkeleton() {
        ServerSocket serverSocket;
        int port = 7070;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("[Server] Listening on port " + port);

            while(true) {
                Socket socket = serverSocket.accept();
                StoreThread st = new StoreThread(socket, this);
                st.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
