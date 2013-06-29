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
 * Two types of quickSort Logic.  NORMAL, THREE_WAY - median is under construction(slightly better performance in presence duplicates)
 * @author Gokulvanan
 *
 */
public class QuickSort extends Sort {

	public enum TYPE {
		NORMAL,THREE_WAY
	}
	
	private TYPE type = null;
	private static final int CUT_OFF = 10;
	
	private  static Map<TYPE,QuickSort> selfRefs = null; //TODO changes this from hashMap to ST
	private QuickSort(TYPE type){ this.type=type;}
	
	static{ // Load different configurations
		selfRefs = new HashMap<QuickSort.TYPE, QuickSort>();
		selfRefs.put(TYPE.NORMAL, new QuickSort(TYPE.NORMAL));
		selfRefs.put(TYPE.THREE_WAY, new QuickSort(TYPE.THREE_WAY));
	}

	public static QuickSort getInstance(){
		return getInstance(TYPE.THREE_WAY);
	}
	public static QuickSort getInstance(TYPE type){
		return selfRefs.get(type);
	}
	@Override
	public void sort(Comparable[] data) {
		Shuffler.shuffle(data);
		sort(data, 0, data.length-1,false);
	}

	@Override
	public void sort(Object[] data, Comparator c) {
		Shuffler.shuffle(data);
		sort(data, 0, data.length-1,c);
	}

	@Override
	public Integer[] indexSort(Comparable[] data) {
		Integer[] index =initIndexArray(data.length);
		Shuffler.shuffle(index);
		sort(data,index, 0, index.length-1,false);
		return index;
	}

	@Override
	public void reverseSort(Comparable[] data) {
		Shuffler.shuffle(data);
		sort(data, 0, data.length-1,true);
		
	}
	@Override
	public Integer[] indexSort(Object[] data, Comparator c) {
		Integer[] index =initIndexArray(data.length);
		Shuffler.shuffle(index);
		sort(data,index, 0, index.length-1,c);
		return index;
	}
	@Override
	public Integer[] reveseIndexSort(Comparable[] data) {
		Integer[] index =initIndexArray(data.length);
		Shuffler.shuffle(index);
		sort(data,index, 0, index.length-1,true);
		return index;
	}
	private void sort(Comparable[] data, int low, int high,boolean reverse){
		if(high - low < CUT_OFF) {
			InsertionSort.getInstance().sort(data,low,high+1,reverse);// high+1 as insertion sort work till < high not <=high
			return;
		}
		if(this.type == TYPE.THREE_WAY){ //Good improvement for duplicates
			int[] parts = partion3Way(data, low, high,reverse);
			sort(data,low,parts[0],reverse);
			sort(data,parts[1],high,reverse);
		}else{
			int j = partion(data, low, high,reverse);
			sort(data,low,j-1,reverse);
			sort(data,j+1,high,reverse);
		}
		
	}
	
	private void sort(Object[] data, int low, int high,Comparator c){
		if(high - low < CUT_OFF) {
			InsertionSort.getInstance().sort(data,low,high+1,c); // high+1 as insertion sort work till < high not <=high
			return;
		}
		if(this.type == TYPE.THREE_WAY){ //Good improvement for duplicates
			int[] ind = partion3Way(data, low, high,c);
			sort(data,low,ind[0],c);
			sort(data,ind[1],high,c);
		}else{
			int j = partion(data, low, high,c);
			sort(data,low,j-1,c);
			sort(data,j+1,high,c);	
		}
		
	}
	
	private void sort(Comparable[] data,Integer[] index,int low, int high, boolean reverse){
		if(high - low < CUT_OFF) {
			InsertionSort.getInstance().indexSort(data,index,low,high+1,reverse); // high+1 as insertion sort work till < high not <=high
			return;
		}
		if(this.type == TYPE.THREE_WAY){ //Good improvement for duplicates
			int[] ind = partion3Way(data,index, low, high,reverse);
			sort(data,index,low,ind[0],reverse);
			sort(data,index,ind[1],high,reverse);
		}else{
			int j = partion(data,index, low, high,reverse);
			sort(data,index,low,j-1,reverse);
			sort(data,index,j+1,high,reverse);
		}
	}

	private void sort(Object[] data,Integer[] index,int low, int high, Comparator c){
		if(high - low < CUT_OFF) {
			InsertionSort.getInstance().indexSort(data,index,low,high+1,c); // high+1 as insertion sort work till < high not <=high
			return;
		}
		if(this.type == TYPE.THREE_WAY){ //Good improvement for duplicates
			int[] ind = partion3Way(data,index, low, high,c);
			sort(data,index,low,ind[0],c);
			sort(data,index,ind[1],high,c);
		}else{
			int j = partion(data,index, low, high,c);
			sort(data,index,low,j-1,c);
			sort(data,index,j+1,high,c);
		}
	}
	private int partion(Comparable[] data, int low, int high, boolean reverse){
		int i = low, j = high+1;
		while(true){
			if(reverse){
				while(greater(data[++i], data[low]) && i != high){/* do nothing	*/	}
				while(greater(data[low],data[--j]) && j != low){ /* do nothing */ }
			}else{
				while(lesser(data[++i], data[low]) && i != high){/* do nothing	*/	}
				while(lesser(data[low],data[--j]) && j != low){ /* do nothing */ }
			}
			if( i >= j) break;
			else swap(data, i, j);
		}
		swap(data, low, j);
		return j;
	}
	
	private int[] partion3Way(Comparable[] data, int low, int high, boolean reverse){
		int lo = low, i=low, j=high;
		Comparable cmp = data[low];
		while(i<=j){
			int flag = (reverse) ? cmp.compareTo(data[i]): data[i].compareTo(cmp);
			if( flag < 0) swap(data, i++, lo++);
			else if(flag > 0) swap(data, i, j--);
			else i++;
		}
		int[] indx ={lo-1,i};
		return indx ;
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
	
	private int[] partion3Way(Object[] data, int low, int high,Comparator c){
		int lo = low, i=low, j=high;
		Object cmp = data[low];
		while(i<=j){
			int flag =c.compare(data[i],cmp);
			if( flag < 0) swap(data, i++, lo++);
			else if(flag > 0) swap(data, i, j--);
			else i++;
		}
		int[] indx ={lo-1,i};
		return indx ;
	}
	
	private int partion(Comparable[] data,Integer[] index, int low, int high,boolean reverse){
		int i = low, j = high+1;
		while(true){
			if(reverse){
				while(greater(data[index[++i]], data[index[low]]) && i != high){/* do nothing	*/	}
				while(greater(data[index[low]], data[index[--j]]) && j != low){ /* do nothing */ }
			}else{
				while(lesser(data[index[++i]], data[index[low]]) && i != high){/* do nothing	*/	}
				while(lesser(data[index[low]], data[index[--j]]) && j != low){ /* do nothing */ }
			}
			
			if( i >= j) break;
			else swap(index, i, j);
		}
		swap(index, low, j);
		return j;
	}
	
	private int[] partion3Way(Comparable[] data,Integer[] index, int low, int high, boolean reverse){
		int lo = low, i=low, j=high;
		Comparable cmp = data[index[low]];
		while(i<=j){
			int flag = (reverse) ? cmp.compareTo(data[index[i]]): data[index[i]].compareTo(cmp);
			if( flag < 0) swap(index, i++, lo++);
			else if(flag > 0) swap(index, i, j--);
			else i++;
		}
		int[] indx ={lo-1,i};
		return indx ;
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
	
	private int[] partion3Way(Object[] data,Integer[] index, int low, int high, Comparator c){
		int lo = low, i=low, j=high;
		Object cmp = data[index[low]];
		while(i<=j){
			int flag =  c.compare(data[index[i]],cmp);
			if( flag < 0) swap(index, i++, lo++);
			else if(flag > 0) swap(index, i, j--);
			else i++;
		}
		int[] indx ={lo-1,i};
		return indx ;
	}
	
}
