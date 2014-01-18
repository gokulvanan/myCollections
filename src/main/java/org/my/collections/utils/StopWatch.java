package org.my.collections.utils;

/**
 * Utilty class to measure performance of Algo and datastructures
 * @author gokulvanan
 *
 */
public class StopWatch {
	private long start;
	
	public StopWatch(){
		start = System.currentTimeMillis();
	}
	
	public long elapsedTime(){
		return System.currentTimeMillis() - start;
	}
}
