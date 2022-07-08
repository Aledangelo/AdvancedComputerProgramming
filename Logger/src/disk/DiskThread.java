package disk;

import java.net.DatagramSocket;
import java.net.SocketException;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import interfaces.ILogger;

public class DiskThread extends Thread {
    private MapMessage mapMessage;

    public DiskThread(MapMessage mapMessage) {
        this.mapMessage = mapMessage;
    }

    @Override
    public void run() {

        try {
            String action = this.mapMessage.getString("op");
            
            switch(action) {
                case "save":
                    int data = this.mapMessage.getInt("data");
                    int UdpPort = this.mapMessage.getInt("port");

                    System.out.println("[DiskThread] Received data: " + data);

                    DatagramSocket socket = new DatagramSocket();
                    ILogger logger = new DiskProxy(socket, UdpPort);
                    logger.setData(data);

                    break;
                default:
                    System.out.println("[DiskThread] Invalid Action!");
                    break;
            }
        } catch(JMSException e) {
            e.printStackTrace();
        } catch(SocketException e) {
            e.printStackTrace();
        }
    }
}
