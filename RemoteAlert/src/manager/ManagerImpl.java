package manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import alert.AlertNotification;
import interfaces.IManager;
import interfaces.ISubscriber;

public class ManagerImpl extends UnicastRemoteObject implements IManager {
    private static final long serialVersionUID = 1038712;

    private Lock lock;

    private Vector<ISubscriber> subscribers_1;
    private Vector<ISubscriber> subscribers_2;
    private Vector<ISubscriber> subscribers_3;
    private Vector<ISubscriber> subscribers_4;
    private Vector<ISubscriber> subscribers_5;

    public ManagerImpl() throws RemoteException {
        this.lock = new ReentrantLock();
        this.subscribers_1 = new Vector<ISubscriber>();
        this.subscribers_2 = new Vector<ISubscriber>();
        this.subscribers_3 = new Vector<ISubscriber>();
        this.subscribers_4 = new Vector<ISubscriber>();
        this.subscribers_5 = new Vector<ISubscriber>();
    }

    @Override
    public void subscribe(int componentID, int port) throws RemoteException {
        ISubscriber subscriber = new SubscriberProxy(port);

        switch(componentID) {
            case 1:
                this.subscribers_1.add(subscriber);
                System.out.println("[ManagerImpl] Adding subscriber with ComponentID: " + componentID);
                break;
            case 2:
                this.subscribers_2.add(subscriber);
                System.out.println("[ManagerImpl] Adding subscriber with ComponentID: " + componentID);
                break;
            case 3:
                this.subscribers_3.add(subscriber);
                System.out.println("[ManagerImpl] Adding subscriber with ComponentID: " + componentID);
                break;
            case 4:
                this.subscribers_4.add(subscriber);
                System.out.println("[ManagerImpl] Adding subscriber with ComponentID: " + componentID);
                break;
            case 5:
                this.subscribers_5.add(subscriber);
                System.out.println("[ManagerImpl] Adding subscriber with ComponentID: " + componentID);
                break;
            default:
                System.out.println("[ManagerImpl] Invalid ComponentID!");
        }
    }

    @Override
    public void sendNotification(AlertNotification alertNotification) throws RemoteException {
        
        lock.lock();

        try {
            int componentID = alertNotification.getComponentID();
            int criticality = alertNotification.getCriticality();
            int i;

            switch(componentID) {
                case 1:
                    if(this.subscribers_1.size() > 0) {
                        System.out.println("[ManagerImpl] Alerting all subscriber with ComponentID: " + componentID);
                        for(i = 0; i < this.subscribers_1.size(); i++) {
                            this.subscribers_1.get(i).notifyAlert(criticality);
                        }
                    }
                    break;
                case 2:
                    if(this.subscribers_2.size() > 0) {
                        System.out.println("[ManagerImpl] Alerting all subscriber with ComponentID: " + componentID);
                        for(i = 0; i < this.subscribers_2.size(); i++) {
                            this.subscribers_2.get(i).notifyAlert(criticality);
                        }
                    }
                    break;
                case 3:
                    if(this.subscribers_3.size() > 0) {
                        System.out.println("[ManagerImpl] Alerting all subscriber with ComponentID: " + componentID);
                        for(i = 0; i < this.subscribers_3.size(); i++) {
                            this.subscribers_3.get(i).notifyAlert(criticality);
                        }
                    }
                    break;
                case 4:
                    if(this.subscribers_4.size() > 0) {
                        System.out.println("[ManagerImpl] Alerting all subscriber with ComponentID: " + componentID);
                        for(i = 0; i < this.subscribers_4.size(); i++) {
                            this.subscribers_4.get(i).notifyAlert(criticality);
                        }
                    }
                    break;
                case 5:
                    if(this.subscribers_5.size() > 0) {
                        System.out.println("[ManagerImpl] Alerting all subscriber with ComponentID: " + componentID);
                        for(i = 0; i < this.subscribers_5.size(); i++) {
                            this.subscribers_5.get(i).notifyAlert(criticality);
                        }
                    }
                    break;
                default:
                    System.out.println("[ManagerImpl] Invalid ComponentID!");
            }
        } finally {
            lock.unlock();
        }
    }
}
