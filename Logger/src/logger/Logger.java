package logger;

import java.io.IOException;
import java.net.SocketException;

import interfaces.ILogger;


public class Logger {
    public static void main(String[] args) {
        int port = Integer.valueOf(args[0]).intValue();
        ILogger logger = new LoggerImpl();
        LoggerSkeleton loggerSkeleton = new LoggerSkeleton(port, logger);
        
        try {
            loggerSkeleton.runSkeleton();
        } catch(SocketException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
