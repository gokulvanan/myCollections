package org.my.collections.searching;

import java.util.InputMismatchException;

import org.my.collections.utils.SortUtils;

/**
 * Simple implementation of Binary search logic
 * @author Gokulvanan
 *
 * @param <T>
 */
public class BinarySearch<T extends Comparable<T>> {

	private T[] objs;
	public BinarySearch(T[] data){
		objs = data;
		if(!SortUtils.isSorted(data)) throw new InputMismatchException("Input array is expected to be sorted in asc order");
	}

	public T search(T elm){
		int lo = 0, high=objs.length;
		while(lo < high){
			int mid = (lo + high) / 2;
			int cmp = objs[mid].compareTo(elm);
			if(cmp > 0) high=mid;
			else if(cmp < 0) lo=mid;
			else return objs[mid];
		}
		return null;
	}
}
