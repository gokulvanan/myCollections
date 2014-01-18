package org.my.collections.utils;

import java.util.Comparator;

/**
 * Parent class with helper methods used in compare base sorting and searching logics
 * @author Gokulvanan
 *
 */
public class Compare {

	
	protected boolean lesser(Comparable c,Comparable v){
		return c.compareTo(v)<0; // return true if c is less than v
	}
	
	protected boolean lesser(Object v,Object w, Comparator c){
		return c.compare(v,w)<0; // return true if c is less than v
	}
	
	protected boolean greater(Comparable c,Comparable v){
		return c.compareTo(v)>=0; // return true if c is greater than v
	}
	
	protected boolean greater(Object v,Object w, Comparator c){
		return c.compare(v,w)>=0; // return true if c is greater than v
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
