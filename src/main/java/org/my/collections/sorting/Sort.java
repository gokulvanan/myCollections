package org.my.collections.sorting;

import java.util.Comparator;

import org.my.collections.Compare;

/**
 * Parent Class that encapsulates all sorting classes
 * @author Gokulvanan
 *
 * @param <T>
 */
public abstract class Sort extends Compare{

	/**
	 * Sorts array of Objects in asc
	 * @param data
	 * @return
	 */
	public abstract void sort(Comparable[] data);
	
	public abstract void reverseSort(Comparable[] data);
	/**
	 * Sorts array of objects based on Compartor logic
	 * @param data
	 * @param c
	 */
	public abstract void sort(Object[] data, Comparator c);
	
	/**
	 * Sorts and return index of array in sorted order.. 
	 * Note: Array of objects is untouched
	 * @param data
	 * @return
	 */
	public abstract Integer[] indexSort(Comparable[] data);
	
	public abstract Integer[] indexSort(Object[] data, Comparator c);
	
	public abstract Integer[] reveseIndexSort(Comparable[] data);
	
}
