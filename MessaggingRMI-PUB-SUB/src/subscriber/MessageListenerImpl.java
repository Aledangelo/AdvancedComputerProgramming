package subscriber;

import interfaces.IMessageListener;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MessageListenerImpl extends UnicastRemoteObject implements IMessageListener {
	private static final long serialVersionUID = 7879911369033457122L;
	
	private String topic;
	
	public MessageListenerImpl() throws RemoteException {
		this.topic = null;
	}

	@Override
	public void onMessage(String message) throws RemoteException {
		System.out.println("[MessageListenerImpl] New Message: " + message);
		
	}

	@Override
	public String getTopic() throws RemoteException {
		return this.topic;
	}

	@Override
	public void setTopic(String topic) throws RemoteException {
		this.topic = topic;
		
	}
	
	

}
