package queueimpl;

import queue.IQueue;

public class CircularQueue implements IQueue {
    private int data[];

    private int head;
    private int tail;

    private int size;
    private int nelem;

    public CircularQueue(int size) {
        this.size = size;
        this.data = new int[size];
        this.nelem = 0;
        this.head = 0;
        this.tail = 0;
    }

    public boolean full() {
        if(this.nelem != this.size) {
            return false;
        } else {
            return true;
        }
    }

    public boolean empty() {
        if(this.nelem != 0) {
            return false;
        } else {
            return true;
        }
    }

    public int getSize() {
        return this.size;
    }

    public void insert(int value) {
        this.data[this.tail % this.size] = value;

        this.nelem++;
        this.tail++;
    }

    public int withdraw() {
        int x = this.data[this.head % this.size];

        this.nelem--;
        this.head++;

        return x;
    }
}
