package org.my.collections.selection;

import java.util.Iterator;

import org.my.collections.PQ;
import org.my.collections.PQ.TYPE;

/**
 * Class uses priority queue to filter Bottom N items from incoming stream
 * @author Gokulvanan
 *
 */
public class PQBottomSelect<Item extends Comparable<Item>> {

	private int index = 0;
	private PQ<Item> pq = null;
	
	public PQBottomSelect(int N){
		pq = new PQ<Item>(N,TYPE.MAX);
	}
	
	public void add(Item obj){
		if(index++ >= pq.size()){
			pq.deque(); // remove maximum when full
		}
		pq.enque(obj);
	}
	
	public Iterator<Item> getBottom(){
		return pq.iterator();
	}
	
	
	
}
