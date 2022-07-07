package sensor;

import queue.IQueue;

public class TManager extends Thread {
    private String command;
    private IQueue queue;

    public TManager(String command, IQueue queue) {
        this.command = command;
        this.queue = queue;
    }

    public void run() {
        this.queue.insert(command);
        System.out.println("[TManager] Insert: " + command);
    }
}
