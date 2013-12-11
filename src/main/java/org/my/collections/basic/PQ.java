package org.my.collections.basic;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;

import org.my.collections.exception.MyCollectionException;


/**
 * Priority Queue implementations - can be used for both Max/Min
 * use swim and sink implementations which are the brain behind binary heap 
 * that enables efficient ordering of data from quick look up and insert of data 
 * Take space of M and look up time of NlogM (N no of items and M = capacity of the heap)
 * @author Gokulvanan
 *
 * @param <Item> - assumed to be immutable
 */
public class PQ<Item extends Comparable<Item>> implements Queue<Item> {

	public static enum TYPE{
		MAX, MIN
	}
	private Item[] data = null;
	private int count = 0;
	private TYPE type = null;
	
	public PQ(int N, TYPE type){
		this.data = (Item[]) new Object[N];
		this.type = type;
	}
	public Iterator<Item> iterator() {
		return new PQIterator();
	}
	
	public void enque(Item obj)  {
		data[++count] = obj;
		swim(count);
	}

	/**
	 * Removes  max/min element in queue
	 * @throws MyCollectionException 
	 */
	public Item deque() throws EmptyStackException {
		if(count == 0) throw new EmptyStackException();
		Item output = data[1];// note 0 is not in use.. heap logic starts from 1
		swap(data,1, count);
		data[count--]=null;
		sink(1,count);
		return output;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public int size() {
		return count;
	}

	private class PQIterator implements Iterator<Item>{
		private int index = count;
		private Item[] vals = Arrays.copyOf(data, 0);

		public boolean hasNext() {
			return index > 0;
		}

		public Item next() {
			Item val = vals[1];
			swap(vals,1, index);
			vals[index--]=null;
			sink(1,index);
			return val;
		}

		public void remove() {
			throw new UnsupportedOperationException(" Not implemented yet");
		}
	}
	
	private void  swim(int k){
		while(k > 1){
			int cmp = data[k].compareTo(data[k/2]);
			if((type == TYPE.MAX && cmp > 0) || (type == TYPE.MIN && cmp <= 0)){
				swap(data,k,k/2);
				k=k/2;
			}else 
				break;
		}
	}
	
	private void sink(int k,int N){
		while(2*k <= N){
			int j = 2*k;
			if(j < N ){
				int cmp = data[j].compareTo(data[j+1]);
				if((type == TYPE.MAX && cmp <= 0) || (type == TYPE.MIN && cmp >0)) j++;
				cmp = data[k].compareTo(data[j]);
				if((type == TYPE.MAX && cmp <= 0 ) || (type == TYPE.MIN && cmp > 0)){
					swap(data,k,j);
					k=j;
				}else 
					break;
			}
		}
	}
	
	private void swap(Item[] data,int i, int j) {
		Item tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
}
