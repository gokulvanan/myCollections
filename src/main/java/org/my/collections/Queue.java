package org.my.collections;

/**
 * 
 * @author Gokulvanan
 *
 * @param <Item>
 */
public interface Queue<Item>  extends Iterable<Item>{

	public void enque(Item obj);
	
	public Item deque();
	
	public boolean isEmpty();
	
	public int size();
}
