package org.my.collections;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.my.collections.sorting.BUMergeSort;
import org.my.collections.sorting.InsertionSort;
import org.my.collections.sorting.MergeSort;
import org.my.collections.sorting.QuickSort;
import org.my.collections.sorting.Sort;
import org.my.collections.utils.SortUtils;

/**
 * Unit test for simple App.
 */
public class SortTest extends TestCase{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public SortTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( SortTest.class );
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testInsertionSort()
	{
		Sort s  = InsertionSort.getInstance();
		String[] data ={"te","we","aed"};
		data = (String[]) s.sort(data);
		assertTrue( SortUtils.isSorted(data)); 
	}

	public void testInsertionIndexSort(){
		Sort s  = InsertionSort.getInstance();
		String[] data ={"te","we","aed"};
		Integer [] indx = s.indexSort(data);
		assertTrue(SortUtils.isIndexSorted(data, indx));
	}

	public void testMergeSort(){
		Sort s = MergeSort.getInstance();
		Integer[] input = SortUtils.generateRandomInput(2000);
		input = (Integer[]) s.sort(input);
		assertTrue( SortUtils.isSorted(input));
	}

	public void testMergeIndexSort(){
		Sort s = MergeSort.getInstance();
		Integer[] input = SortUtils.generateRandomInput(2000);
		Integer[] indx = (Integer[]) s.indexSort(input);
		assertTrue( SortUtils.isIndexSorted(input, indx));
	}

	public void testBUMergeSort(){
		Sort s = BUMergeSort.getInstance();
		Integer[] input = SortUtils.generateRandomInput(20);
		input = (Integer[]) s.sort(input);
		assertTrue( SortUtils.isSorted(input));
	}

	public void testBUMergeIndexSort(){
		Sort s = BUMergeSort.getInstance();
		Integer[] input = SortUtils.generateRandomInput(20);
		Integer[] indx = (Integer[]) s.indexSort(input);
		assertTrue( SortUtils.isIndexSorted(input,indx));
	}

	public void testQuickSort(){
		Sort s = QuickSort.getInstance();
		Integer[] input = SortUtils.generateRandomInput(2000);
		input = (Integer[]) s.sort(input);
		assertTrue( SortUtils.isSorted(input));
	}

	public void testQuickIndexSort(){
		Sort s = QuickSort.getInstance();
		Integer[] input = SortUtils.generateRandomInput(2000);
		Integer[] indx = (Integer[]) s.indexSort(input);
		assertTrue( SortUtils.isIndexSorted(input,indx));
	}
}
