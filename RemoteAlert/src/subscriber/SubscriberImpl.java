package subscriber;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import interfaces.ISubscriber;

public class SubscriberImpl implements ISubscriber {
    private String pathFile;

    public SubscriberImpl(String pathFile) {
        this.pathFile = pathFile;
    }

    @Override
    public void notifyAlert(int criticality) {
        System.out.println("[SubscriberImpl] Criticality: " + criticality);
        
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.pathFile)));
            pw.println("Criticality: " + criticality);
            pw.flush();
            pw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
