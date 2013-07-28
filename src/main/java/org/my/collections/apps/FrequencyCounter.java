package org.my.collections.apps;

import java.util.Scanner;

import org.my.collections.searching.HashST;
import org.my.collections.searching.ST;

/**
 * Uses Symbol table Implementation to count 
 * frequency of words from input stream wrapped 
 * in a Scanner
 
 * @author Gokulvanan
 *
 */
public class FrequencyCounter {

	public static ST<String,Integer> count (Scanner sc) {
		ST<String,Integer> output = new HashST<String,Integer>();
		while(sc.hasNext()){
			String k = sc.next();
			int count = (output.contains(k))? output.get(k) : 0;
			output.put(sc.next(), ++count);
		}
		return output;
	}
}
