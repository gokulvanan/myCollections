package org.my.collections;

import java.util.Arrays;

import org.my.collections.selection.QuickSelect;
import org.my.collections.utils.SortUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SelectionTest extends TestCase{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public SelectionTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( SelectionTest.class );
	}

	/**
	 * Test for QuickSelect find Largest
	 */
	public void testFindLargest()
	{
		Integer [] data = SortUtils.generateRandomInput(20);
		Integer val = QuickSelect.getInstance().findKthLargest(data, 5);
		assertTrue(val == 15);
	}
	
	public void testFindSmallest()
	{
		Integer [] data = SortUtils.generateRandomInput(20);
		Integer val = QuickSelect.getInstance().findKthSmallest(data, 5);
		assertTrue(val == 4);
	}
	
	public void testTopFive()
	{
		Integer [] data = SortUtils.generateRandomInput(20);
		QuickSelect.getInstance().findTopK(data, 5);
		Comparable[] vals = QuickSelect.getInstance().findTopK(data, 5);
		assertTrue(vals.length == 5);
		System.out.println(Arrays.toString(vals));
		Integer[] expected = {19,18,17,16,15};
//		assertSame(Arrays.toString(vals),Arrays.toString(expected));
	}
	/*
	public void testBottomFive()
	{
		Integer [] data = SortUtils.generateRandomInput(20);
		Integer[] vals = QuickSelect.getInstance().findBottomK(data, 5);
		assertTrue(vals.length == 5);
		Integer[] expected = {0,1,2,3,4};
//		assertSame(vals, expected);
	}
*/
}
