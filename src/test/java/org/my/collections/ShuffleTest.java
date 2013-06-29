package org.my.collections;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.my.collections.utils.Shuffler;
import org.my.collections.utils.SortUtils;

public class ShuffleTest extends TestCase{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public ShuffleTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( ShuffleTest.class );
	}

	/**
	 * Test for Shuffle
	 */
	public void testShuffle()
	{
		Integer[] input = SortUtils.generateOrderedInput(20);
		Shuffler.shuffle(input);
		assertTrue(!(SortUtils.isSorted(input)));
	}
}
