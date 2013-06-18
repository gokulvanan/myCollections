package org.my.collections.sorting;

import java.util.Comparator;

/**
 * Parent Class that encapsulates all sorting classes
 * @author Gokulvanan
 *
 * @param <T>
 */
public abstract class Sort{

	/**
	 * Sorts array of Objects
	 * @param data
	 * @return
	 */
	public abstract Comparable[] sort(Comparable[] data);
	
	/**
	 * Sorts array of objects based on Compartor logic
	 * @param data
	 * @param c
	 */
	public abstract Object[] sort(Object[] data, Comparator c);
	
	/**
	 * Sorts and return index of array in sorted order.. 
	 * Note: Array of objects is untouched
	 * @param data
	 * @return
	 */
	public abstract Integer[] indexSort(Comparable[] data);
	
//	public abstract void sort(List<T> data);
//	
//	public abstract void sort(List data, Comparator c);
	

	// HELPER METHODS
	
	protected boolean lesser(Comparable c,Comparable v){
		return c.compareTo(v)<0; // return true if c is less than v
	}
	
	protected boolean lesser(Object v,Object w, Comparator c){
		return c.compare(v,w)<0; // return true if c is less than v
	}
	
	protected void swap(Object[] data, int i, int j){
		Object buff = data[i];
		data[i] =data[j];
		data[j] = buff;
	}
	
	protected Integer[] initIndexArray(int len){
		Integer[] index = new Integer[len];
		for(int i=0; i<len; i++) index[i]=i;
		return index;
	}
}
