package org.my.collections;

import java.util.EmptyStackException;


/**
 * 
 * @author Gokulvanan
 *
 * @param <Item>
 */
public interface Queue<Item>  extends Iterable<Item>{

	public void enque(Item obj);
	
	public Item deque() throws EmptyStackException;
	
	public boolean isEmpty();
	
	public int size();
}
