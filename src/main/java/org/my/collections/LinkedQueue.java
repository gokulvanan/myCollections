package org.my.collections;

import java.util.EmptyStackException;
import java.util.Iterator;


/**
 * 
 * @author Gokulvanan
 *
 * @param <Item>
 */
public class LinkedQueue<Item> implements Queue<Item>, Bag<Item> {

	private class Node<T>{
		T val = null;
		Node<T> next = null;
		
		Node(T val){
			this.val=val;
		}
	}
	
	private Node<Item> first, last;
	private int count = 0;
	
	public void enque(Item obj) {
		Node<Item> ref = new Node<Item>(obj);
		last = (last == null) ? last : last.next;
		last = ref;
		if(isEmpty()) first = last;
		count++;
	}

	public Item deque() {
		if(isEmpty()) throw new EmptyStackException();
		Item val = first.val;
		first = first.next;
		if(isEmpty()) last = null; // nullify last pointer to same ref
		count--;
		return val;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return count;
	}
	
	public Iterator<Item> iterator() {
		return new LinkedQueueIterator();
	}
	
	private class LinkedQueueIterator implements Iterator<Item>{
		private Node<Item> refF = first;

		public boolean hasNext() {
			return refF != null;
		}

		public Item next() {
			Item val = refF.val;
			refF = refF.next;
			return val;
		}

		public void remove() {
			throw new UnsupportedOperationException(" Not implemented yet");
		}
	}

	public void add(Item x) {
		enque(x);
	}

}
