package server;

public class Server {
    public static void main(String[] args) {
        DispatcherImpl dispatcherImpl = new DispatcherImpl(5);
        dispatcherImpl.runSkeleton();
    }
}
