package Actuator;

import client.ClientProxy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Actuator {
    public static void main(String[] args) {
        try {
            ClientProxy proxy = new ClientProxy(7070);
            try (PrintStream fileOut = new PrintStream(new FileOutputStream("./cmdlog.txt"))) {
                while(true) {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("[ACTUATOR] Getting command...");
                    int cmd = proxy.getCmd();

                    fileOut.println("CMD: " + cmd);
                    System.out.println("[ACTUATOR] CMD: " + cmd);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
