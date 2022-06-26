package client;

import service.RemoteCounter;

public class Client {
    
    public static void main(String args[]) {
        String host = args[0];
        String op = args[1];

        if (!op.equals("sum") && !op.equals("inc") && !op.equals("get") && !op.equals("sqr")) {
            System.out.println("[ERROR] Invalid Argument");
        } else {
            // Proxy
            RemoteCounter counter = new CounterProxy(host, 6969);

            switch(op) {
                case "sum":
                    try {
                        int val = Integer.parseInt(args[2]);
                        System.out.println("[SUM] Counter: " + counter.sum(val));
                    } catch(NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case "inc":
                    System.out.println("[INC] Counter: " + counter.inc());
                    break;
                case "get":
                    System.out.println("[GET] Counter: " + counter.get());
                    break;
                case "sqr":
                    System.out.println("[SQR] Counter: " + counter.sqr());
                    break;
                default:
                    System.out.println("Invalid Method");
                    break;
            }
        }
    }
}
