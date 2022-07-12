package server;

import queue.QueueWrapperLock;

public class ServerImpl extends ServerSkeleton {
	private QueueWrapperLock queue;
	
	public ServerImpl(QueueWrapperLock queue, int port) {
		super(port);
		this.queue = queue;
	}
	
	@Override
	public boolean book(int id, boolean restriction) {
		
		if(restriction) {
			if(this.queue.full()) {
				return false;
			}
		}
		
		this.queue.insert(id);
		System.out.println("[ServerImpl] Booked with id: " + id);
		
		return true;
	}

}
