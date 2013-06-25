package org.my.collections.sorting;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.my.collections.utils.Shuffler;

/**
 * InPlace sort - no extra space
 * Recursive sort but not stable sort
 * Also 1.39NLogN but not Guarantee NLogN
 * 39% more compares than Merget sort but lesser data movement than Merge causes quicker sorting
 * Not the best sort in case of many duplicate data
 * Cut offs to insertion sort for less than 10 items
 * @author Gokulvanan
 *
 */
public class QuickSort extends Sort {

	public enum TYPE {
		NORMAL,MEDIAN3
	}
	
	private TYPE type = null;
	private static final int CUT_OFF = 10;
	
	private  static Map<TYPE,QuickSort> selfRefs = null; //TODO changes this from hashMap to ST
	private QuickSort(TYPE type){ this.type=type;}
	
	static{ // Load different configurations
		selfRefs = new HashMap<QuickSort.TYPE, QuickSort>();
		selfRefs.put(TYPE.NORMAL, new QuickSort(TYPE.NORMAL));
		selfRefs.put(TYPE.MEDIAN3, new QuickSort(TYPE.MEDIAN3));
	}

	public static QuickSort getInstance(){
		return getInstance(TYPE.NORMAL);
	}
	public static QuickSort getInstance(TYPE type){
		return selfRefs.get(type);
	}
	@Override
	public Comparable[] sort(Comparable[] data) {
		Shuffler.shuffle(data);
		sort(data, 0, data.length-1);
		return data;
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

	private void sort(Comparable[] data, int low, int high){
		if(high - low < CUT_OFF) {
			InsertionSort.getInstance().sort(data,low,high);
			return;
		}
		if(this.type == TYPE.MEDIAN3){ //mild improvement
			int midean = medianOf3(low,low+((high-low)/2),high);
			swap(data, low, midean);
		}
		int j = partion(data, low, high);
		sort(data,low,j-1);
		sort(data,j+1,high);
	}
//	TODO
	private int medianOf3(int low, int i, int high) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int partion(Comparable[] data, int low, int high){
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
