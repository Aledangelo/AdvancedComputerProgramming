package server;

public class Server {
    public static void main(String[] args) {
        StoreSkeleton skeleton = new StoreImpl(5);
        skeleton.runSkeleton();
    }
}
