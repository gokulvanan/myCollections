package org.my.collections.selection;

import java.util.Arrays;
import java.util.Comparator;

import org.my.collections.Compare;
import org.my.collections.sorting.InsertionSort;
import org.my.collections.utils.Shuffler;

/**
 * Applies QuickSort partition concept to find k^th largest/k^th smallest element in an array
 * This class has method return top k and bottom k elms
 * @author Gokulvanan
 */
public class QuickSelect extends Compare {
	
	
	private QuickSelect(){};
	
	private static QuickSelect selfRef = null;
	public static QuickSelect getInstance(){
		selfRef = (selfRef == null)? new QuickSelect() : selfRef;
		return selfRef;
	} 
	
	public <T extends Comparable> T findKthLargest(T[] data, int k){
		k =k-1;// as index starts from 0
		Integer [] index = initIndexArray(data.length);
		Shuffler.shuffle(index);
		int lo=0, hi=data.length-1;
		while(hi >= lo){
			int j = partionGreater(data, index, lo, hi);
			if(j < k) lo=j+1;
			else if(j > k) hi=j-1;
			else return data[index[j]];
		}
		return null;
	}
	
	public <T> T findKthLargest(T[] data, int k,Comparator<T> c){
		k =k-1;// as index starts from 0
		Integer [] index = initIndexArray(data.length);
		Shuffler.shuffle(index);
		int lo=0, hi=data.length-1;
		while(hi >= lo){
			int j = partionGreater(data, index, lo, hi,c);
			if(j < k) lo=j+1;
			else if(j > k) hi=j-1;
			else return data[index[j]];
		}
		return null;
	}
	
	public Comparable[] findTopK(Comparable[] data, int k){
		k =k-1;// as index starts from 0
		Integer [] index = initIndexArray(data.length);
		Shuffler.shuffle(index);
		int lo=0, hi=data.length-1;
		while(hi >= lo){
			int j = partionGreater(data, index, lo, hi);
			if(j < k) lo=j+1;
			else if(j > k) hi=j-1;
			else break;
		}
		Comparable [] output =  new Comparable[k+1]; 
		for(int i=0; i<k+1; i++) output[i] = data[index[i]];
		InsertionSort.getInstance().sort(output);
		return output;
	}
	
	public <T> T[] findTopK(T[] data, int k, Comparator c){
		k =k-1;// as index starts from 0
		Integer [] index = initIndexArray(data.length);
		Shuffler.shuffle(index);
		int lo=0, hi=data.length-1;
		while(hi >= lo){
			int j = partionGreater(data, index, lo, hi,c);
			if(j < k) lo=j+1;
			else if(j > k) hi=j-1;
			else break;
		}
		T [] output = (T[]) new Object[k+1]; 
		for(int i=0; i<k+1; i++) output[i] = data[index[i]];
		InsertionSort.getInstance().sort(output,c);
		return output;
	}
	
	public <T extends Comparable> T findKthSmallest(T[] data, int k){
		k =k-1;// as index starts from 0
		Integer [] index = initIndexArray(data.length);
		Shuffler.shuffle(index);
		int lo=0, hi=data.length-1;
		while(hi >= lo){
			int j = partion(data, index, lo, hi);
			if(j < k) lo=j+1;
			else if(j > k) hi=j-1;
			else return data[index[j]];
		}
		return null;
	}
	
	public <T> T findKthSmallest(T[] data,int k, Comparator<T> c){
		k =k-1;// as index starts from 0
		Integer [] index = initIndexArray(data.length);
		Shuffler.shuffle(index);
		int lo=0, hi=data.length-1;
		while(hi >= lo){
			int j = partion(data, index, lo, hi,c);
			if(j < k) lo=j+1;
			else if(j > k) hi=j-1;
			else return data[index[j]];
		}
		return null;
	}
	
	public <T extends Comparable> T[] findBottomK(T[] data, int k){
		k =k-1;// as index starts from 0
		Integer [] index = initIndexArray(data.length);
		Shuffler.shuffle(index);
		int lo=0, hi=data.length-1;
		while(hi >= lo){
			int j = partion(data, index, lo, hi);
			if(j < k) lo=j+1;
			else if(j > k) hi=j-1;
			else break;
		}
		T [] output = (T[]) new Object[k+1]; 
		for(int i=0; i<k+1; i++) output[i] = data[index[i]];
		InsertionSort.getInstance().sort(output);
		return output;
	}
	
	public <T> T[] findBottomK(T[] data, int k, Comparator c){
		k =k-1;// as index starts from 0
		Integer [] index = initIndexArray(data.length);
		Shuffler.shuffle(index);
		int lo=0, hi=data.length-1;
		while(hi >= lo){
			int j = partion(data, index, lo, hi,c);
			if(j < k) lo=j+1;
			else if(j > k) hi=j-1;
			else break;
		}
		T [] output = (T[]) new Object[k+1]; 
		for(int i=0; i<k+1; i++) output[i] = data[index[i]];
		InsertionSort.getInstance().sort(output,c);
		return output;
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
	
	private int partionGreater(Comparable[] data,Integer[] index, int low, int high){
		int i = low, j = high+1;
		while(true){
			while(greater(data[index[++i]], data[index[low]]) && i != high){/* do nothing	*/	}
			while(greater(data[index[low]],data[index[--j]]) && j != low){ /* do nothing */ }
			if( i >= j) break;
			else swap(index, i, j);
		}
		swap(index, low, j);
		return j;
	}
	
	private int partion(Object[] data,Integer[] index, int low, int high,Comparator c){
		int i = low, j = high+1;
		while(true){
			while(lesser(data[index[++i]], data[index[low]],c) && i != high){/* do nothing	*/	}
			while(lesser(data[index[low]],data[index[--j]],c) && j != low){ /* do nothing */ }
			if( i >= j) break;
			else swap(index, i, j);
		}
		swap(index, low, j);
		return j;
	}
	
	private int partionGreater(Object[] data,Integer[] index, int low, int high, Comparator c){
		int i = low, j = high+1;
		while(true){
			while(greater(data[index[++i]], data[index[low]],c) && i != high){/* do nothing	*/	}
			while(greater(data[index[low]],data[index[--j]],c) && j != low){ /* do nothing */ }
			if( i >= j) break;
			else swap(index, i, j);
		}
		swap(index, low, j);
		return j;
	}
}
