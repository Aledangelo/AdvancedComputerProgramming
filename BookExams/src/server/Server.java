package server;

import queue.*;

public class Server {
	public static void main(String[] args) {
		int port = Integer.valueOf(args[0]).intValue();
		
		QueueWrapperLock queue = new QueueWrapperLock(new CircularQueue(10));
		
		StorageThread st = new StorageThread(queue, "bookList.txt");
		st.start();
		
		ServerImpl skeleton = new ServerImpl(queue, port);
		skeleton.runSkeleton();
	}
}
