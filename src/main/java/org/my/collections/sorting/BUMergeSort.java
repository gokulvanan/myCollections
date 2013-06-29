package org.my.collections.sorting;

import java.util.Comparator;

/**
 * Bottom Up Merge Sort
 * Non recursive but 10% slower than Merge Sort. 
 * Use when you want to avoid recursive calls-- less storage on memory stack
 * @author Gokulvanan
 * @param <Comparable>
 */
public class BUMergeSort extends Merge{

	private static final int CUT_OFF = 10;
	//Singelton
	private  static BUMergeSort selfRef = null;
	private BUMergeSort(){}

	public static BUMergeSort getInstance(){
		if(selfRef == null) selfRef = new BUMergeSort();
		return selfRef;
	}

	@Override
	public void sort(Comparable[] data) {
		int N = data.length;
		Comparable[] buff = new Comparable[N];
		for(int sz=1; sz < N; sz+=sz)	{
			for(int low=0; low<N-sz; low +=sz+sz){
				merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1,false);
			}
		}
	}

	@Override
	public void sort(Object[] data, Comparator c) {
		int N = data.length;
		Object[] buff = new Object[N];
		for(int sz=1; sz < N; sz+=sz)	{
			for(int low=0; low<N-sz; low +=sz+sz){
				merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1,c);
			}
		}
	}

	@Override
	public Integer[] indexSort(Comparable[] data) {
		int N = data.length;
		Integer[] index = initIndexArray(data.length);
		Integer[] buff = new Integer[N];
		for(int sz=1; sz < N; sz+=sz)	{
			boolean cutOff=(2*sz<CUT_OFF);
			for(int low=0; low<N-sz; low +=sz+sz){
				merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1,index,false);
			}
		}
		return index;
	}

	@Override
	public void reverseSort(Comparable[] data) {
		int N = data.length;
		Comparable[] buff = new Comparable[N];
		for(int sz=1; sz < N; sz+=sz)	{
			for(int low=0; low<N-sz; low +=sz+sz){
				merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1,true);
			}
		}
	}

	@Override
	public Integer[] indexSort(Object[] data, Comparator c) {
		int N = data.length;
		Integer[] index = initIndexArray(data.length);
		Integer[] buff = new Integer[N];
		for(int sz=1; sz < N; sz+=sz)	{
			boolean cutOff=(2*sz<CUT_OFF);
			for(int low=0; low<N-sz; low +=sz+sz){
				merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1,index,c);
			}
		}
		return index;
	}

	@Override
	public Integer[] reveseIndexSort(Comparable[] data) {
		int N = data.length;
		Integer[] index = initIndexArray(data.length);
		Integer[] buff = new Integer[N];
		for(int sz=1; sz < N; sz+=sz)	{
			boolean cutOff=(2*sz<CUT_OFF);
			for(int low=0; low<N-sz; low +=sz+sz){
				merge(data, buff, low, Math.min(low+sz+sz-1, N-1), low+sz-1,index,true);
			}
		}
		return index;
	}
}
