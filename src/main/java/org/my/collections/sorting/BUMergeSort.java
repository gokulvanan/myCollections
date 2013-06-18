package org.my.collections.sorting;

import java.util.Comparator;

/**
 * Bottom Up Merge Sort
 * Non recursive but 10% slower than Merge Sort. 
 * @author Gokulvanan
 * @param <Comparable>
 */
public class BUMergeSort extends Sort{

	@Override
	public Comparable[] sort(Comparable[] data) {
		int N = data.length;
		Comparable[] buff = new Comparable[N];
		for(int mid=1; mid < N; mid+=mid)	{
			for(int low=0; low<N-mid; low +=mid+mid){
					merge(data, buff, low, Math.min(low+mid+mid-1, N-1), low+mid-1);
			}
		}
		return data;
	}
	
	

	@Override
	public Object[] sort(Object[] data, Comparator c) {
		int N = data.length;
		Comparable[] buff = new Comparable[N];
		for(int mid=1; mid < N; mid+=mid)	{
			for(int low=0; low<N-mid; low +=mid+mid){
					merge(data, buff, low, Math.min(low+mid+mid-1, N-1), low+mid-1,c);
			}
		}
		return data;
	}

	@Override
	public Integer[] indexSort(Comparable[] data) {
		int N = data.length;
		Integer[] index = new Integer[N];
		Integer[] buff = new Integer[N];
		for(int mid=1; mid < N; mid+=mid)	{
			for(int low=0; low<N-mid; low +=mid+mid){
					merge(data, buff, low, Math.min(low+mid+mid-1, N-1), low+mid-1,index);
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
