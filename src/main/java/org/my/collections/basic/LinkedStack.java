package org.my.collections.basic;

import java.util.Iterator;



/**
 * Every operation takes guaranteed constant time
 * Uses extra time and space to deal with links
 * 
 * @author Gokulvanan
 *
 */
public class LinkedStack<Item> implements Stack<Item>, Bag<Item>{

	/**
	 * Sub Class of Linked List used to wrap over Nodes in the linked list.
	 * Memory foot print: 40 Bytes
	 * @author gokulvanan
	 *
	 * @param <T>
	 */
	private class Node<T>{
								//16 Bytes Object Overhead
								//8 Bytes Subclass overhead
		T val = null;      		//8 Bytes pointer
		Node<T> prev = null; 	//8 Bytes pointer
		
		Node(T val,Node<T> next){
			this.val=val;
			this.prev=next;
		}
	}
	
	private Node<Item> first = null; // 40 + 8 (pointer)
	private int size = 0;			 // 4 Bytes
	
	public void push(Item obj) {
		first = new Node<Item>(obj,first);
		size++;
	}
	
	public Item pop() {
		Item output = first.val;
		first = first.prev;
		size--;
		return output;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
	
	public Iterator<Item> iterator() {
		return new LinkedStackIterator();
	}
	
	
	private class LinkedStackIterator implements Iterator<Item>{
		private Node<Item> ref = first;

		public boolean hasNext() {
			return ref != null;
		}

		public Item next() {
			Item val = ref.val;
			ref = ref.prev;
			return val;
		}

		public void remove() {
			throw new UnsupportedOperationException(" Not implemented yet");
		}
	}

	public void add(Item x) {
		push(x);
	}
	
}
