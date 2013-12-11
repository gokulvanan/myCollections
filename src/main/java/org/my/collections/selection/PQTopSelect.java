package org.my.collections.selection;

import java.util.Iterator;

import org.my.collections.basic.PQ;
import org.my.collections.basic.PQ.TYPE;

/**
 * Class uses priority queue to filter Top N items from incoming stream
 * @author Gokulvanan
 *
 */
public class PQTopSelect<Item extends Comparable<Item>> {

	private int index = 0;
	private PQ<Item> pq = null;
	public PQTopSelect(int N){
		pq = new PQ<Item>(N,TYPE.MIN);
	}
	
	public void add(Item obj){
		if(index++ >= pq.size()){
			pq.deque(); // remove minimun when full
		}
		pq.enque(obj);
	}
	
	public Iterator<Item> getTop(){
		return pq.iterator();
	}
	
	
	
}
