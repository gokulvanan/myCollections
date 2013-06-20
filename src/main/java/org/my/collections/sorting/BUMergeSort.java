package org.my.collections.sorting;

import java.util.Comparator;

/**
 * Bottom Up Merge Sort
 * Non recursive but 10% slower than Merge Sort. 
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
			boolean cutOff=(2*sz<CUT_OFF);
			for(int low=0; low<N-sz; low +=sz+sz){
				if(lesser(data[low+sz-1],data[low+sz])) continue;// avoid merger if allready sorted
				if(cutOff)	data = InsertionSort.getInstance().sort(data,low,low+sz+sz-1); // cutoff to insertion sort for less records
				else 		merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1);
			}
		}
		return data;
	}
	
	

	@Override
	public Object[] sort(Object[] data, Comparator c) {
		int N = data.length;
		Object[] buff = new Object[N];
		for(int sz=1; sz < N; sz+=sz)	{
			boolean cutOff=(2*sz<CUT_OFF);
			for(int low=0; low<N-sz; low +=sz+sz){
				if(lesser(data[low+sz-1],data[low+sz],c)) continue;// avoid merger if allready sorted
				if(cutOff)	data = InsertionSort.getInstance().sort(data,low,low+sz+sz-1,c); // cutoff to insertion sort for less records
				else 		merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1,c);
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
				if(lesser(data[index[low+sz-1]],data[index[low+sz]])) continue;// avoid merger if already sorted
				if(cutOff)	data = InsertionSort.getInstance().indexSort(data,index,low,low+sz+sz-1); // cutoff to insertion sort for less records
				else		merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1,index);
			}
		}
		return index;
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

	private void merge(Object[] data, Object[] buff, int low, int high, int mid,Comparator c) {
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
