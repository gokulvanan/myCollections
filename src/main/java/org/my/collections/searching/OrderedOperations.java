package org.my.collections.searching;

/**
 * Interface that defines a list of orderd operations used
 * in BST
 * @author Gokulvanan
 *
 * @param <Key>
 * @param <Value>
 */
public interface OrderedOperations<Key,Value> extends  ST<Key,Value> {

	public Key min();
	
	public Key max();
	
	public Key floor(Key k);
	
	public Key cieling(Key k);
	
	public int rank(Key k);
	
	public Key select(int rank);
	
	public void deleteMin();
	
	public void deleteMax();
	
	public int size(Key lo, Key high);
	
	public Iterable<Key> keys(Key lo, Key high);
	
}
