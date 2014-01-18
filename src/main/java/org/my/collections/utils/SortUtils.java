package org.my.collections.utils;

import org.my.collections.basic.Shuffler;

public class SortUtils {

	public static boolean isSorted(Comparable[] c){
		for(int i=1,len=c.length;i<len; i++){
			if(c[i].compareTo(c[i-1])<0)	return false;
		}
		return true;
	}
	
	public static boolean isIndexSorted(Comparable[] c, Integer[] index){
		for(int i=1,len=c.length;i<len; i++){
			if(c[index[i]].compareTo(c[index[i-1]])<0)	return false;
		}
		return true;
	}
	
	public static Integer[] generateOrderedInput(int size){
		Integer [] output = new Integer[size];
		for(int i=0; i<size; i++) output[i]=i;
		return output;
	}
	
	
	public static Integer[] generateRandomInput(int size){
		Integer [] output = new Integer[size];
		for(int i=0; i<size; i++) output[i]=i;
		return (Integer[]) Shuffler.shuffle(output);
	}
	
}
