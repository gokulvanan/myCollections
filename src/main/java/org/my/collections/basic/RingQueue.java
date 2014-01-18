package org.my.collections.basic;
import java.util.EmptyStackException;
import java.util.Iterator;


/**
 * Fixed length array based queue implementation.
 * Data in the array is looped around as ring structure rather than reseting
 * by replacing the data back to index 0 once array end is reached an elements have been dequeued
 * 
 * This queue provides constant time peformance in enque and deque operations
 * Use this queue when max size of queue is known and there are multiple enque , deque toggles
 *
 **/
public class RingQueue<T> implements Queue<T> {

	private T[] data = null;
	private int head = 0, tail = 0;
	private int count = 0;

	@SuppressWarnings("unchecked")
	public RingQueue(int size){
		this.data = (T []) new Object[size];
	}

	public void enque(T obj){
		tail = incrTail();
		this.data[tail] = obj;
		this.count++;
	}

	public T deque() throws EmptyStackException{
		if(isEmpty()) throw new EmptyStackException();
		T obj = this.data[head];
		this.data[head]=null;
		head = incrHead();
		count--;
		return obj;
	}

	public boolean isEmpty(){
		return this.count == 0;
	}

	public int size(){
		return this.count;
	}

	private int incrHead(){
		if(head+1 == data.length) return 0;
		else return head+1;
	}
	
	private int incrTail(){
		if(tail == data.length){
			if(head == 0) throw new IndexOutOfBoundsException();
			return 0;
		}else if(isEmpty() ||  tail + 1 < head){
			return tail + 1;
		}else throw new IndexOutOfBoundsException();
	}



	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}