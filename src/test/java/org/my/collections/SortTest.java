package org.my.collections;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.my.collections.sorting.InsertionSort;
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
	
	public void testIndexSort(){
		Sort s  = InsertionSort.getInstance();
		String[] data ={"te","we","aed"};
		Integer [] indx = s.indexSort(data);
		String f = data[indx[0]];
		for(int i=1; i<indx.length; i++) assertTrue(f.compareTo(data[indx[i]])<=0);
	}
}
