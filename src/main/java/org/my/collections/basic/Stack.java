package org.my.collections.basic;

/**
 * 
 * Datastructure that implements LIFO logic
 * @author Gokulvanan
 *
 * @param <Item>
 */
public interface Stack<Item> extends Iterable<Item> {

	public void push(Item obj);
	
	public Item pop();
	
	public boolean isEmpty();
	
	public int size();
}
