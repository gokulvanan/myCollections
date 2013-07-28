package org.my.collections.sorting;

import java.util.Comparator;

/**
 * In place sort. with worst case 2NlogN -- a little slower than merge and quick sort
 * Inner loop longer than quick sort - not effective for small/medium sizes
 * Makes poor use of cache memory and not stable.
 * @author Gokulvanan
 *
 */
public class HeapSort extends Sort{

	@Override
	public void sort(Comparable[] data) {
		int len = data.length;
		// sink  each element from halfway to maintain heap order from bottom up
		for(int i=len/2; i>0; i--){ 
			sink(data,i,len);
		}
		//in place sort by removing max and placing it a bottom of the stack
		while(len > 1){
			swap(data, 0, (len-1));
			sink(data,1,--len);
		}
	}

	@Override
	public void reverseSort(Comparable[] data) {
		int len = data.length;
		// sink  each element from halfway to maintain heap order from bottom up
		for(int i=len/2; i>0; i--){ 
			sinkOnGreater(data,i,len);
		}
		//in place sort by removing max and placing it a bottom of the stack
		while(len > 1){
			swap(data, 0, (len-1));
			sinkOnGreater(data,1,--len);
		}		
	}

	@Override
	public void sort(Object[] data, Comparator c) {
		int len = data.length;
		// sink  each element from halfway to maintain heap order from bottom up
		for(int i=len/2; i>0; i--){ 
			sink(data,i,len,c);
		}
		//in place sort by removing max and placing it a bottom of the stack
		while(len > 1){
			swap(data, 0, (len-1));
			sink(data,1,--len,c);
		}
	}

	@Override
	public Integer[] indexSort(Comparable[] data) {
		int len = data.length;
		Integer[] index = initIndexArray(len);
		// sink  each element from halfway to maintain heap order from bottom up
		for(int i=len/2; i>0; i--){ 
			sink(data,i,len,index);
		}
		//in place sort by removing max and placing it a bottom of the stack
		while(len > 1){
			swap(index, 0, (len-1));
			sink(data,1,--len,index);
		}
		return index;
	}

	@Override
	public Integer[] indexSort(Object[] data, Comparator c) {
		int len = data.length;
		Integer[] index = initIndexArray(len);
		// sink  each element from halfway to maintain heap order from bottom up
		for(int i=len/2; i>0; i--){ 
			sink(data,i,len,index,c);
		}
		//in place sort by removing max and placing it a bottom of the stack
		while(len > 1){
			swap(index, 0, (len-1));
			sink(data,1,--len,index,c);
		}
		return index;
	}

	@Override
	public Integer[] reveseIndexSort(Comparable[] data) {
		int len = data.length;
		Integer[] index = initIndexArray(len);
		// sink  each element from halfway to maintain heap order from bottom up
		for(int i=len/2; i>0; i--){ 
			sinkGreater(data,i,len,index);
		}
		//in place sort by removing max and placing it a bottom of the stack
		while(len > 1){
			swap(index, 0, (len-1));
			sinkGreater(data,1,--len,index);
		}
		return index;
	}

	// note j-1 k-1 used to remember 0 index during comparison and sorting 
	private void sink(Comparable[] data,int k,int N){
		while(2*k <= N){
			int j = 2*k;
			if(j < N  &&  lesser(data[j-1],data[j])) j++;
			if(lesser(data[k-1],data[j-1])) swap(data,k-1,j-1);
			else break;
		}
	}
	
	private void sinkOnGreater(Comparable[] data,int k,int N){
		while(2*k <= N){
			int j = 2*k;
			if(j < N  &&  greater(data[j-1],data[j])) j++;
			if(greater(data[k-1],data[j-1])) swap(data,k-1,j-1);
			else break;
		}
	}
	
	private void sink(Object[] data,int k,int N,Comparator c){
		while(2*k <= N){
			int j = 2*k;
			if(j < N  &&  lesser(data[j-1],data[j],c)) j++;
			if(lesser(data[k-1],data[j-1],c)) swap(data,k-1,j-1);
			else break;
		}
	}
	
	private void sink(Comparable[] data,int k,int N,Integer[] index){
		while(2*k <= N){
			int j = 2*k;
			if(j < N  &&  lesser(data[index[j-1]],data[index[j]])) j++;
			if(lesser(data[index[k-1]],data[index[j-1]])) swap(index,k-1,j-1);
			else break;
		}
	}
	
	private void sink(Object[] data,int k,int N,Integer[] index,Comparator c){
		while(2*k <= N){
			int j = 2*k;
			if(j < N  &&  lesser(data[index[j-1]],data[index[j]],c)) j++;
			if(lesser(data[index[k-1]],data[index[j-1]],c)) swap(index,k-1,j-1);
			else break;
		}
	}
	
	private void sinkGreater(Comparable[] data,int k,int N,Integer[] index){
		while(2*k <= N){
			int j = 2*k;
			if(j < N  &&  greater(data[index[j-1]],data[index[j]])) j++;
			if(greater(data[index[k-1]],data[index[j-1]])) swap(index,k-1,j-1);
			else break;
		}
	}
}
