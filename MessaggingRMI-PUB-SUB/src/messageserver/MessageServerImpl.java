package messageserver;

import interfaces.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class MessageServerImpl extends UnicastRemoteObject implements IMessageServer {
	private static final long serialVersionUID = -4556940050298718034L;
	
	private Vector<IMessageListener> subs;
	private Semaphore maxConcurrent;
	private Semaphore maxThread;
	
	public MessageServerImpl() throws RemoteException {
		this.subs = new Vector<IMessageListener>();
		this.maxConcurrent = new Semaphore(3);
		this.maxThread = new Semaphore(5);
	}
	
	@Override
	public boolean publish(String topic, String message) throws RemoteException {
		boolean check = false;
		
		if(!this.maxThread.tryAcquire()) {
			return check;
		}
		
		try {
			this.maxConcurrent.acquire();
			
			for(IMessageListener m: this.subs) {
				String tpc = m.getTopic();
				if(tpc.equals(topic)) {
					m.onMessage(message);
					System.out.println("[MessageServerImpl] Call onMessage: " + message + "; on Topic: " + topic);
				}
			}
			check = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			check = false;
		} finally {
			this.maxConcurrent.release();
			this.maxThread.release();
		}
		
		return check;
	}

	@Override
	public void subscribe(String topic, IMessageListener listener) throws RemoteException {
		listener.setTopic(topic);
		this.subs.add(listener);
		
		System.out.println("[MessageServerImpl] New listener added with topic: " + topic);
	}



}
