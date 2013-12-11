package org.my.collections.basic;

/**
 * Interface over Linked Data structure stack/queue 
 * that holds collection of items
 * and also enables iteration through the given collection
 * add method has constant time operation.. (Linke Node creation overhead)
 * @author Gokulvanan
 *
 */
public interface Bag<T> extends Iterable<T>{

	public void add(T x);// insert a new item onto bag
	public int size();// number of items in bag
}
