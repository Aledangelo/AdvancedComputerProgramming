package subscriber;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import interfaces.ISubscriber;

public class SubscriberSkeleton implements ISubscriber {
    private ISubscriber subscriber;
    private int port;

    public SubscriberSkeleton(ISubscriber subscriber, int port) {
        this.subscriber = subscriber;
        this.port = port;
    }

    @Override
    public void notifyAlert(int criticality) {
        this.subscriber.notifyAlert(criticality);
    }

    public void runSkeleton() {
        try {
            try (ServerSocket serverSocket = new ServerSocket(this.port)) {
                System.out.println("[SubscriberSkeleton] Server listening in port " + this.port);

                while(true) {
                    Socket socket = serverSocket.accept();
                    SubscriberThread st = new SubscriberThread(socket, this);
                    st.start();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
