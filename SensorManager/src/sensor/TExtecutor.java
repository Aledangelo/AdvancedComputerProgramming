package sensor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import queue.IQueue;

public class TExtecutor extends Thread {
    private String pathFile;
    private IQueue queue;
    
    public TExtecutor(String pathFile, IQueue queue) {
        this.pathFile = pathFile;
        this.queue = queue;
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(10000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("[TExecutor] Cleaning queue");

            try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.pathFile, true)));
    
                for(int i = 0; i < this.queue.getSize(); i++) {
                    pw.println("Command: " + this.queue.withdraw());
                    pw.flush();
                }
    
                pw.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
