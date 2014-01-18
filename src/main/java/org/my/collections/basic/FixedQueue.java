package org.my.collections.basic;

import java.util.Iterator;


/**
 * Simple Fixed array implementation of queue datastructure
 * If data entered in greater that size on queue it throws IndexOutOfBoundsException
 * Constant amortized time performance (Exception during checkAndShift operation which would take linear time in worst case)
 *
 * Use when fixed number of enque operations followed by deque operations is expected.
 * Not to be used if there is frequent toggle of enque and deque use RingQueue instead
 *
 * */
public class FixedQueue<T> implements Queue<T> {

	private T [] data;
	private int head=0,tail=0;

	@SuppressWarnings("unchecked")
	public FixedQueue(int size){
		T[] data = (T []) new Object[size];
	}

	public void enque(T obj) {
		if(tail+1 == data.length) checkAndShift(); //move back if earlier content were remove
		data[tail++] = obj;
	}

	public T deque() {
		if(head == tail) throw new IndexOutOfBoundsException();
		T obj = data[head];
		data[head++]=null;
		return obj;
	}

	public boolean isEmpty() {
		return (tail - head) == 0;
	}

	public int size() {
		return (tail - head);
	}

	/**
	 * Move back contents if head is not at 0th index
	 * Else throw IndexOutOfBoundsExceptoin
	 */
	private void checkAndShift(){
		if(head == 0) throw new IndexOutOfBoundsException();
		for(int i=head; i<tail; i++){
			data[i-head] = data[i];
		}
		tail = tail - head;
		head = 0;
	}

	public Iterator<T> iterator() {
		return null;
	}

}
