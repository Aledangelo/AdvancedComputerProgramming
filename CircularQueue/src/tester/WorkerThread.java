package tester;

import coda.*;

public class WorkerThread extends Thread {
    private Coda wrapper;
    private boolean flag;   // If true, call insert method

    public WorkerThread(Coda c, boolean f) {
        wrapper = c;
        flag = f;
    }

    public void run() {
        if (flag) {
            wrapper.insert(1 + (int)(Math.random() * 100));
        } else {
            wrapper.withdraw();
        }
    }
}
