package org.my.collections;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Every operation takes constant amortized time
 * Less wasted space compared to Linked version
 * Resizing Strategy - double on full  half on 1/4 full
 * @author Gokulvanan
 *
 */
public class ArrayStack<Item> implements Stack<Item>{

	private Item [] data = null;
	private int pointer = 0;
	
	public ArrayStack(int N){
		data = (Item[]) new Object[N];
	}
	
	public void push(Item obj) {
		if(pointer+1 == data.length) resize(2*data.length);
		data[pointer++] = obj;
	}

	public Item pop() {
		if(size() == 0) throw new EmptyStackException();
		Item obj = data[--pointer];
		data[pointer]= null;
		if(pointer < data.length/4) resize(data.length/2);
		return obj;
	}

	public boolean isEmpty() {
		return data.length == 0;
	}

	public int size() {
		return data.length;
	}

	private void resize(int size){
		Item[] buff = (Item []) new Object[size];
		for(int i = 0; i < size; i++){
			try{
				buff[i]= data[i];
			}catch(ArrayIndexOutOfBoundsException a){
				break;
			}
		}
		data = buff;
	}

	public Iterator<Item> iterator() {
		return new ArrayStackIterator();
	}
	
	private class ArrayStackIterator implements Iterator<Item>{
		private Item[] buffData = (Item[]) data;
		int last = pointer;

		public boolean hasNext() {
			return last > 0;
		}

		public Item next() {
			return buffData[--last];
		}

		public void remove() {
			throw new UnsupportedOperationException(" Not implemented yet");
		}
		
	}
}
