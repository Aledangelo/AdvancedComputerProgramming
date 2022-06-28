package QueueImpl;

import queue.Queue;

public class CircularQueue implements Queue {
    private int data[];

    private int head;
    private int tail;

    private int size;
    private int nelem;

    public CircularQueue(int size) {
        this.size = size;
        this.nelem = 0;
        this.tail = 0;
        this.head = 0;
        this.data = new int[size];
    }

    public boolean full() {
        if (size != nelem) {
            return false;
        } else {
            return true;
        }
    }

    public boolean empty() {
        if (nelem != 0) {
            return false;
        } else {
            return true;
        }
    }

    public int getSize() {
        return size;
    }

    public void insert(int value) {
        data[tail % size] = value;
        nelem = nelem + 1;
        tail = tail + 1;
    }

    public int withdraw() {
        int x = data[head % size];
        nelem = nelem - 1;
        head = head + 1;
        return x;
    }
}
