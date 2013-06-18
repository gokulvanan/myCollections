package org.my.collections.sorting;

import java.util.Comparator;

/**
 * Guaranteed NLogN performance
 * Takes space proportional to N and 2N for index sorts
 * There is overhead of creation and initialization of sub arrays in merge sort.. 
 * for items less than 20-30 elements.. use insertion sort..
 * This code defaults to insertion sort for less than 10 items.. 
 * to avoid merge subroutine in case of lesser items.. 
 * Stable sort.. hence works well with cascaded sorting logic
 * @author Gokulvanan
 * @param <Comparable>
 */
public class MergeSort extends Sort{

	private static final int CUT_OFF = 10;
	//Singelton
	private  static MergeSort selfRef = null;
	private MergeSort(){}

	public static MergeSort getInstance(){
		if(selfRef == null) selfRef = new MergeSort();
		return selfRef;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Comparable[] sort(Comparable[] data) {
		int len = data.length;
		Comparable[] buff = (Comparable[]) new Comparable[len];
		recursiveSort(data,buff,0,len);
		return data;
	}


	@Override
	public Object[] sort(Object[] data, Comparator c) {
		int len = data.length;
		Object[] buff =  new Object[len];
		recursiveSort(data,buff,0,len,c);
		return data;
	}

	@Override
	public Integer[] indexSort(Comparable[] data) {
		int len = data.length;
		Integer [] index = new Integer [len];
		Integer [] buff =  new Integer[len];
		recursiveSort(data,buff,0,len,index);
		return index;
	}

	private void recursiveSort(Comparable[] data, Integer[] buff, int i, int len, Integer[] index) {
		if(i+CUT_OFF<len){
			index = InsertionSort.getInstance().indexSort(data,index,i,len);
			return;
		}
		int mid = (i + len) / 2;
		recursiveSort(data, buff, i, mid,index);
		recursiveSort(data, buff, mid, len,index);
		if (!lesser(data[index[mid+1]], data[index[mid]])) return; //avoid merge  if already sorted
		merge(data,buff,i,len,mid,index);
	}

	private void merge(Comparable[] data, Integer[] buff, int low, int high, int mid,Integer[] index) {
		for(int i=low; i<high; i++) buff[i] = index[i];
		int i = low, j = mid+1;
		for(int k=low; k < high; k++){
			if(i > mid)  									index[k] = buff[j++];
			else if(j < mid) 								index[k] = buff[i++];
			else if(lesser(data[buff[j]],data[buff[i]])) 	index[k] = buff[j++];
			else 							 				index[k] = buff[i++];
		}
	}

	private void recursiveSort(Comparable[] data, Comparable[] buff, int i, int len) {
		if(i+CUT_OFF<len){
			data = InsertionSort.getInstance().sort(data,i,len);
			return;
		}
		int mid = (i + len) / 2;
		recursiveSort(data, buff, i, mid);
		recursiveSort(data, buff, mid, len);
		if (!lesser(data[mid+1], data[mid])) return; //avoid merge  if already sorted
		merge(data,buff,i,len,mid);
	}

	private void merge(Comparable[] data, Comparable[] buff, int low, int high, int mid) {
		for(int i=low; i<high; i++) buff[i] = data[i];
		int i = low, j = mid+1;
		for(int k=low; k < high; k++){
			if(i > mid)  							data[k] = buff[j++];
			else if(j < mid) 						data[k] = buff[i++];
			else if(lesser(buff[j],buff[i])) 		data[k] = buff[j++];
			else 							 		data[k] = buff[i++];
		}
	}

	private void recursiveSort(Object[] data, Object[] buff, int i, int len, Comparator<Comparable> c) {
		if(i+CUT_OFF<len){
			data = InsertionSort.getInstance().sort(data,i,len,c);
			return;
		}
		int mid = (i + len) / 2;
		recursiveSort(data, buff, i, mid,c);
		recursiveSort(data, buff, mid, len,c);
		if (!lesser(data[mid+1], data[mid],c)) return; //avoid merge  if already sorted
		merge(data,buff,i,len,mid,c);
	}

	private void merge(Object[] data, Object[] buff, int low, int high, int mid,Comparator<Comparable> c) {
		for(int i=low; i<=high; i++) buff[i] = data[i];
		int i = low, j = mid+1;
		for(int k=low; k < high; k++){
			if(i > mid)  							data[k] = buff[j++];
			else if(j < mid) 						data[k] = buff[i++];
			else if(lesser(buff[j],buff[i],c)) 		data[k] = buff[j++];
			else 							 		data[k] = buff[i++];
		}
	}
}
