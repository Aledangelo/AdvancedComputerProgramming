package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import service.Counter;

public class CounterWorker extends Thread {
    private DatagramSocket socket;
    private DatagramPacket req;
    private Counter skeleton;

    public CounterWorker(DatagramSocket s, DatagramPacket r, Counter c) {
        socket = s;
        req = r;
        skeleton = c;
    }

    public void run() {
        String msg = new String(req.getData(), 0, req.getLength());
        System.out.println("[Counter Worker] Processing packet...\nRequest size: " + req.getLength() + "\nMessage: " + msg);

        StringTokenizer msgTokens = new StringTokenizer(msg, "#");
        String method = msgTokens.nextToken();

        switch(method) {
            case "setCount":
                String id = msgTokens.nextToken();
                int x_set = Integer.valueOf(msgTokens.nextToken()).intValue();

                skeleton.setCount(id, x_set);   // upcall

                String rplySet = "ack";
                DatagramPacket replySet = new DatagramPacket(rplySet.getBytes(), rplySet.getBytes().length, req.getAddress(), req.getPort());

                try {
                    socket.send(replySet);
                } catch(IOException e) {
                    e.printStackTrace();
                }
                break;
            case "sum":
                int x_sum = Integer.valueOf(msgTokens.nextToken()).intValue();
                int res_sum = skeleton.sum(x_sum);

                String rplySum = Integer.toString(res_sum);
                DatagramPacket replySum = new DatagramPacket(rplySum.getBytes(), rplySum.getBytes().length, req.getAddress(), req.getPort());

                try {
                    socket.send(replySum);
                } catch(IOException e) {
                    e.printStackTrace();
                }
                break;
            case "inc":
                int res_inc = skeleton.inc();

                String rplyInc = Integer.toString(res_inc);
                DatagramPacket replyInc = new DatagramPacket(rplyInc.getBytes(), rplyInc.getBytes().length, req.getAddress(), req.getPort());

                try {
                    socket.send(replyInc);
                } catch(IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("BAD METHOD");
                break;

        }
    }
}
