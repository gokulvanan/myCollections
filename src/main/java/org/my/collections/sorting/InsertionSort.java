package org.my.collections.sorting;

import java.util.Comparator;

//TODO : verify test result on size
/**
 * Ideal to use for short arrays 
 * Can be used to check arrays that are already sorted.. (N-1 compares)
 * Worst for reverse sorted arrays O(N^2)
 * More effective than Merge/Quick for size < 30 
 * Stable sort.. hence works well with cascaded sorting logic
 * @author Gokulvanan
 *
 * @param <Comparable>
 */
public class InsertionSort extends Sort{
	
	//Singelton
	private  static InsertionSort selfRef = null;
	private InsertionSort(){}
	
	public static InsertionSort getInstance(){
		if(selfRef == null) selfRef = new InsertionSort();
		return selfRef;
	}
	
	@Override
	public Comparable[] sort(Comparable[] data) {
		return sort(data,0,data.length);
	}

	@Override
	public Object[] sort(Object[] data, Comparator c) {
		return sort(data,0,data.length,c);
	}

	@Override
	public Integer[] indexSort(Comparable[] data) {
		int len=data.length;
		Integer[] index = initIndexArray(len);
		return indexSort(data, index, 0, len);
	}

	public Integer[] indexSort(Comparable[] data, Integer[] index, int start, int len) {
		for(int i=start; i<len; i++){
			for(int j=i-1; j>=0 && lesser(data[index[j+1]], data[index[j]]); j--)
				swap(index, j+1, j);
		}
		return index;
	}

	public Comparable[] sort(Comparable[] data, int start, int len) {
		for(int i=start; i<len; i++){
			for(int j=i-1; j>=0 && lesser(data[j+1],data[j]); j--)	
				swap(data, j+1, j);
		}
		return data;
		
	}

	public Object[] sort(Object[] data, int start, int len, Comparator<Comparable> c) {
		for(int i=start; i<len; i++){
			for(int j=i-1; j>=0 && lesser(data[j+1],data[j],c); j--)	
				swap(data,j+1,j);
		}
		return data;
	}
}
