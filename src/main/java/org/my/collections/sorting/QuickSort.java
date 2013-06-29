package org.my.collections.sorting;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.my.collections.utils.Shuffler;

/**
 * InPlace sort - no extra space
 * Recursive sort but not stable sort
 * Also 1.39NLogN but not Guarantee NLogN-- killer input could take N^2 time
 * 39% more compares than Merget sort but lesser data movement than Merge causes quicker sorting
 * Not the best sort in case of many duplicate data
 * Cut offs to insertion sort for less than 10 items
 * Two types of quickSort Logic.  NORMAL, MEDIAN3 - median is under construction(slightly better performance in presence duplicates)
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
		Shuffler.shuffle(data);
		sort(data, 0, data.length-1,c);
		return data;
	}

	@Override
	public Integer[] indexSort(Comparable[] data) {
		Integer[] index =initIndexArray(data.length);
		Shuffler.shuffle(index);
		sort(data,index, 0, index.length-1);
		return index;
	}

	private void sort(Comparable[] data, int low, int high){
		if(high - low < CUT_OFF) {
			InsertionSort.getInstance().sort(data,low,high+1);// high+1 as insertion sort work till < high not <=high
			return;
		}
		if(this.type == TYPE.MEDIAN3){ //mild improvement
			int median = medianOf3(low,((low+high)/2),high);
			swap(data, low, median);
		}
		int j = partion(data, low, high);
		sort(data,low,j-1);
		sort(data,j+1,high);
	}
	
	private void sort(Object[] data, int low, int high,Comparator c){
		if(high - low < CUT_OFF) {
			InsertionSort.getInstance().sort(data,low,high+1,c); // high+1 as insertion sort work till < high not <=high
			return;
		}
		if(this.type == TYPE.MEDIAN3){ //mild improvement
			int median = medianOf3(low,((low+high)/2),high);
			swap(data, low, median);
		}
		int j = partion(data, low, high,c);
		sort(data,low,j-1,c);
		sort(data,j+1,high,c);
	}
	
	private void sort(Comparable[] data,Integer[] index,int low, int high){
		if(high - low < CUT_OFF) {
			InsertionSort.getInstance().indexSort(data,index,low,high+1); // high+1 as insertion sort work till < high not <=high
			return;
		}
		if(this.type == TYPE.MEDIAN3){ //mild improvement
			int median = medianOf3(low,((low+high)/2),high);
			swap(index, low, median);
		}
		int j = partion(data,index, low, high);
		sort(data,index,low,j-1);
		sort(data,index,j+1,high);
	}
//	TODO
	private int medianOf3(int low, int i, int high) {
		// TODO Auto-generated method stub
		return low;
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
	
	private int partion(Object[] data, int low, int high,Comparator c){
		int i = low, j = high+1;
		while(true){
			while(lesser(data[++i], data[low],c) && i != high){/* do nothing	*/	}
			while(lesser(data[low],data[--j],c) && j != low){ /* do nothing */ }
			if( i >= j) break;
			else swap(data, i, j);
		}
		swap(data, low, j);
		return j;
	}
	
	private int partion(Comparable[] data,Integer[] index, int low, int high){
		int i = low, j = high+1;
		while(true){
			while(lesser(data[index[++i]], data[index[low]]) && i != high){/* do nothing	*/	}
			while(lesser(data[index[low]],data[index[--j]]) && j != low){ /* do nothing */ }
			if( i >= j) break;
			else swap(index, i, j);
		}
		swap(index, low, j);
		return j;
	}
}
