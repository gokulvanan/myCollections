package org.my.collections.sorting;

import java.util.Comparator;

/**
 * 
 * @author Gokulvanan
 *
 */
public class QuickSort extends Sort {

	@Override
	public Comparable[] sort(Comparable[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] sort(Object[] data, Comparator c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer[] indexSort(Comparable[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	public int partion(Comparable[] data, int low, int high){
		int i = low, j = high+1;
		while(true){
			while(lesser(data[++i], data[low]) && i != high){/* do nothing	*/	}
			while(lesser(data[low],data[--j]) && j != low){ /* do nothing */ }
			if( i >= j) break;
			else swap(data, i, j);
		}
		swap(data, low, j);
		return j;
	}
	
	
}
