package org.my.collections.utils;

import java.util.Random;

/**
 * Class that implements Knut-Shuffle Algo
 * @author Gokulvanan
 *
 */
public class Shuffler {

	private static Random random;    // pseudo-random number generator
	private static long seed;        // pseudo-random number generator seed

	static {
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}
	
	public static Object[] shuffle(Object[] data){
		int N = data.length;
		for(int i=N-1; i>0; i--){
			int r = random.nextInt(i); // return an integer between 0 and i
			swap(data,i,r);
		}
		return data;
	}
	
	private static void swap(Object[] data, int i, int j){
		Object buff = data[i];
		data[i] =data[j];
		data[j] = buff;
	}
}
