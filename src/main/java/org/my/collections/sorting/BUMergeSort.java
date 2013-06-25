package org.my.collections.sorting;

import java.util.Comparator;

/**
 * Bottom Up Merge Sort
 * Non recursive but 10% slower than Merge Sort. 
 * Use when you want to avoid recursive calls-- less storage on memory stack
 * @author Gokulvanan
 * @param <Comparable>
 */
public class BUMergeSort extends Sort{

	private static final int CUT_OFF = 10;
	//Singelton
	private  static BUMergeSort selfRef = null;
	private BUMergeSort(){}

	public static BUMergeSort getInstance(){
		if(selfRef == null) selfRef = new BUMergeSort();
		return selfRef;
	}

	@Override
	public Comparable[] sort(Comparable[] data) {
		int N = data.length;
		Comparable[] buff = new Comparable[N];
		for(int sz=1; sz < N; sz+=sz)	{
			for(int low=0; low<N-sz; low +=sz+sz){
				merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1);
			}
		}
		return data;
	}
	
	

	@Override
	public Object[] sort(Object[] data, Comparator c) {
		int N = data.length;
		Object[] buff = new Object[N];
		for(int sz=1; sz < N; sz+=sz)	{
			for(int low=0; low<N-sz; low +=sz+sz){
				merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1,c);
			}
		}
		return data;
	}

	@Override
	public Integer[] indexSort(Comparable[] data) {
		int N = data.length;
		Integer[] index = new Integer[N];
		Integer[] buff = new Integer[N];
		for(int sz=1; sz < N; sz+=sz)	{
			boolean cutOff=(2*sz<CUT_OFF);
			for(int low=0; low<N-sz; low +=sz+sz){
				merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1,index);
			}
		}
		return index;
	}

	private void merge(Comparable[] data, Comparable[] buff, int low, int high, int mid) {
		for(int i=low; i<=high; i++) buff[i] = data[i];
		int i = low, j = mid+1;
		for(int k=low; k <= high; k++){
			if(i > mid)  							data[k] = buff[j++];
			else if(j > high) 						data[k] = buff[i++];
			else if(lesser(buff[j],buff[i])) 		data[k] = buff[j++];
			else 							 		data[k] = buff[i++];
		}
	}
	
	
	private void merge(Comparable[] data, Integer[] buff, int low, int high, int mid,Integer[] index) {
		for(int i=low; i<=high; i++) buff[i] = index[i];
		int i = low, j = mid+1;
		for(int k=low; k <= high; k++){
			if(i > mid)  									index[k] = buff[j++];
			else if(j > high) 								index[k] = buff[i++];
			else if(lesser(data[buff[j]],data[buff[i]])) 	index[k] = buff[j++];
			else 							 				index[k] = buff[i++];
		}
	}

	private void merge(Object[] data, Object[] buff, int low, int high, int mid,Comparator c) {
		for(int i=low; i<=high; i++) buff[i] = data[i];
		int i = low, j = mid+1;
		for(int k=low; k <= high; k++){
			if(i > mid)  							data[k] = buff[j++];
			else if(j > high) 						data[k] = buff[i++];
			else if(lesser(buff[j],buff[i],c)) 		data[k] = buff[j++];
			else 							 		data[k] = buff[i++];
		}
	}
}
