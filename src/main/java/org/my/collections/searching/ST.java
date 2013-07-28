package org.my.collections.searching;

/**
 * Symbol Table Interface
 * get return null in absence of value 
 * put overrides previous value if any
 * @author Gokulvanan
 *
 * @param <Key> - Assumed immutable
 * @param <Value> - should not be Null
 */
public interface ST<Key,Value> {

	public void put(Key k, Value v);
	
	public Value get(Key k);
	
	public void delete(Key k);
	
	public boolean contains(Key k);
	
	public boolean isEmpty();
	
	public int size();
	
	public Iterable<Key> keys();
}
