package org.my.collections.sorting;

import java.util.Comparator;

/**
 * An extension to insertion short.... 
 * H-sorts the array.. H = 3x+1.. greatest value less that size of input	
 * Note: Not a Stable Sort	
 * @author Gokulvanan
 *
 */
public class ShellSort extends Sort{

	//Singelton
	private  static ShellSort selfRef = null;
	private ShellSort(){}
	
	public static ShellSort getInstance(){
		if(selfRef == null) selfRef = new ShellSort();
		return selfRef;
	}
	
	@Override
	public Comparable[] sort(Comparable[] data) {
		int h = computeH(data.length);
		while(h >=1){
			for(int i=h; i<data.length; i++){
				for(int j=i; j>=h && lesser(data[j],data[j-h]); j-=h)
					swap(data, j, j-h);
			}
			h = h/3;
		}
		return data;
	}


	@Override
	public Object[] sort(Object[] data, Comparator c) {
		int h = computeH(data.length);
		while(h >=1){
			for(int i=h; i<data.length; i++){
				for(int j=i; j>=h && lesser(data[j],data[j-h],c); j-=h)
					swap(data, j, j-h);
			}
			h = h/3;
		}
		return data;
	}

	@Override
	public Integer[] indexSort(Comparable[] data) {
		int len=data.length;
		int h = computeH(len);
		Integer[] index = initIndexArray(len);
		while(h >=1){
			for(int i=h; i<data.length; i++){
				for(int j=i; j>=h && lesser(data[index[j]],data[index[j-h]]); j-=h)
					swap(index, j, j-h);
			}
			h = h/3;
		}
		return index;
	}
	

	private int computeH(int N) {
		int h = 1;
		while (h < N/3) h = 3*h+1;
		return h;
	}	
}
