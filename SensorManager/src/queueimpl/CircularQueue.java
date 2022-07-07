package queueimpl;

import queue.IQueue;

public class CircularQueue implements IQueue {
    private String data[];

    private int size;
    private int nelem;

    private int head;
    private int tail;

    public CircularQueue(int size) {
        this.data = new String[size];
        this.size = size;
        this.nelem = 0;
        this.head = 0;
        this.tail = 0;
    }

    @Override
    public void insert(String value) {
        this.data[this.tail % this.size] = value;
        this.nelem++;
        this.tail++;
    }

    @Override
    public String withdraw() {
        String x = this.data[this.head % this.size];
        this.nelem--;
        this.head++;
        return x;
    }

    @Override
    public boolean empty() {
        if(this.nelem != 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean full() {
        if(this.nelem != this.size) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
