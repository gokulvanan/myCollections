package org.my.collection.utils;

import java.util.Random;


public class RandomUtils {

	private static Random random;    // pseudo-random number generator
	private static long seed;        // pseudo-random number generator seed

	static {
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}
	
	public static void shuffle(Object[] data){
		int N = data.length;
		for(int i = 0; i < N; i++){
			int r = random.nextInt(i+1);// Return an integer uniformly between 0 and N-1.
			swap(data,i,r);
		}
	}
	
	private static void swap(Object[] data, int i, int j){
		Object buff = data[i];
		data[i] =data[j];
		data[j] = buff;
	}
}
