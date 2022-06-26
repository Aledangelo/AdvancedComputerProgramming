package server;

import service.RemoteCounter;

public class Server {
    
    public static void main() {
        RemoteCounter counter = new CounterImpl();

        CounterSkeleton cs = new CounterSkeleton(counter);
        cs.runSkeleton();
    }
}
