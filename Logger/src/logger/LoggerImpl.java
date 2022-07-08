package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import interfaces.ILogger;

public class LoggerImpl implements ILogger {

    @Override
    public synchronized void setData(int data) {
        System.out.println("[LoggerImpl] Received data: " + data);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("../log.txt", true)));
            pw.println("DATA: " + data);
            pw.flush();
            pw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
