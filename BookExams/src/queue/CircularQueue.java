package queue;

import interfaces.IQueue;

public class CircularQueue implements IQueue {
	private int[] data;
	
	private int head;
	private int tail;
	
	private int size;
	private int nelem;
	
	public CircularQueue(int size) {
		this.data = new int[size];
		this.size = size;
		this.nelem = 0;
		this.head = 0;
		this.tail = 0;
	}

	@Override
	public void insert(int id) {
		this.data[this.tail % this.size] = id;
		
		this.tail += 1;
		this.nelem += 1;
	}

	@Override
	public int get() {
		int x = this.data[this.head % this.size];
		
		this.head += 1;
		this.nelem -= 1;
		
		return x;
	}

	@Override
	public boolean full() {
		if(this.nelem == this.size) {
			return true;
		}
		return false;
	}

	@Override
	public boolean empty() {
		if(this.nelem == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int getSize() {
		return this.size;
	}
	
	
}
