package org.my.collections.sorting;

import java.util.Comparator;

/**
 * Implements Merge Subroutine used in both MergeSort and BUMergeSort
 * @author Gokulvanan
 *
 */
public abstract class Merge extends Sort {

	protected void merge(Comparable[] data, Integer[] buff, int low, int high, int mid,Integer[] index) {
		for(int i=low; i<high; i++) buff[i] = index[i];
		int i = low, j = mid+1;
		for(int k=low; k < high; k++){
			if(i > mid)  									index[k] = buff[j++];
			else if(j > high) 								index[k] = buff[i++];
			else if(lesser(data[buff[j]],data[buff[i]])) 	index[k] = buff[j++];
			else 							 				index[k] = buff[i++];
		}
	}
	
	protected void merge(Comparable[] data, Comparable[] buff, int low, int high, int mid) {
		for(int i=low; i<high; i++) buff[i] = data[i];
		int i = low, j = mid+1;
		for(int k=low; k < high; k++){
			if(i > mid)  							data[k] = buff[j++];
			else if(j > high) 						data[k] = buff[i++];
			else if(lesser(buff[j],buff[i])) 		data[k] = buff[j++];
			else 							 		data[k] = buff[i++];
		}
	}
	
	protected void merge(Object[] data, Object[] buff, int low, int high, int mid,Comparator<Comparable> c) {
		for(int i=low; i<=high; i++) buff[i] = data[i];
		int i = low, j = mid+1;
		for(int k=low; k < high; k++){
			if(i > mid)  							data[k] = buff[j++];
			else if(j > high) 						data[k] = buff[i++];
			else if(lesser(buff[j],buff[i],c)) 		data[k] = buff[j++];
			else 							 		data[k] = buff[i++];
		}
	}
}
