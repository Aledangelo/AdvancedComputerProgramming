package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import store.IStore;

public class StoreThread extends Thread {
    private Socket socket;
    private IStore store;

    public StoreThread(Socket s, IStore i) {
        this.socket = s;
        this.store = i;
    }

    public void run() {
        System.out.println("[Thread Store] Starting...");

        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String msg = in.readUTF();
            String item = in.readUTF();

            switch(msg) {
                case "deposit":
                    int id = in.readInt();
                    System.out.println("[Thread Store] - DEPOSIT: " + item + " : " + id);
                    store.deposit(item, id);
                    out.writeUTF("ack");
                    break;
                case "withdraw":
                    System.out.println("[Thread Store] - WITHDRAW: " + item);
                    int x = store.withdraw(item);
                    PrintStream fileOut = new PrintStream("./storeLog.txt");
                    fileOut.println("ID: " + x);
                    fileOut.close();
                    out.writeInt(x);
                    break;
                default:
                    System.out.println("[Error] Invalid Argument");
                    break;
            }

            in.close();
            out.close();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
