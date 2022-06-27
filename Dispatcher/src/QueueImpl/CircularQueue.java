package QueueImpl;

import Queue.Queue;

public class CircularQueue implements Queue {
    private int data[];

    private int head;
    private int tail;

    private int size;
    private int nelem;

    public CircularQueue(int size) {
        this.size = size;
        this.nelem = 0;
        this.head = 0;
        this.tail = 0;
        this.data = new int[size];
    }

    public int withdraw() {
        int x = data[head % size];
        System.out.println("[Queue] Withdraw " + x);

        nelem = nelem - 1;
        head = head + 1;

        return x;
    }

    public void insert(int value) {
        data[tail % size] = value;
        System.out.println("[Queue] Insert " + value);
        
        nelem = nelem + 1;
        tail = tail + 1;
    }

    public boolean full() {
        if(nelem != size) {
            return false;
        } else {
            return true;
        }
    }

    public boolean empty() {
        if(nelem != 0) {
            return false;
        } else {
            return true;
        }
    }

    public int getSize() {
        return size;
    }
}
