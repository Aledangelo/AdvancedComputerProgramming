package queue;

import interfaces.IQueue;

public class CircularQueue implements IQueue {
	private int[] data;
	
	private int head;
	private int tail;
	
	private int nelem;
	private int size;
	
	public CircularQueue(int size) {
		this.data = new int[size];
		this.size = size;
		this.nelem = 0;
		this.head = 0;
		this.tail = 0;
	}

	@Override
	public void insert(int value) {
		this.data[this.tail % this.size] = value;
		this.nelem += 1;
		this.tail += 1;
		
	}

	@Override
	public int get() {
		int x = this.data[this.head % this.size];
		this.nelem -= 1;
		this.head += 1;
		
		return x;
	}

	@Override
	public boolean empty() {
		if(this.nelem == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean full() {
		if(this.nelem == this.size) {
			return true;
		}
		return false;
	}

	@Override
	public int getSize() {
		return this.size;
	}
	
	
}
