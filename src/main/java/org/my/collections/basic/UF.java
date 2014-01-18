package org.my.collections.basic;

/**
 * Simple Implementation of Union Find Algo.. (Weighted Quick Union with Path Compression);
 * Time to Instantiate of Object O(N)
 * Time to perform union of of 2 values O(lgN) 
 * Time to lookup if connected = O(lg*N) ~= constant between 1 to 5
 * Memory Footprint:
 * 8N + 68 (Attributes size)  + 16 (Object overhead) + 4 (Padding) = 8N + 88 <=> 8(N + 11)
 * 
 * @author Gokulvanan
 *
 */

public class UF {  
	
	int [] data = null; // 4N + 24 + 8 (Pointer ref)
	int [] size = null; // 4N + 24 + 8 (Pointer ref)
	int count = 0;		// 4
	
	/**
	 * Initialize data structure
	 * @param N
	 */
	public UF(int N){
		count = N;
		data = new int[N];
		size = new int[N];
		for(int i=0 ; i<N; i++){
			data[i]=i;
			size[i]=1;
		}
	}
	
	/**
	 * Establish connection between two elements
	 * @param p
	 * @param q
	 * @throws Exception
	 */
	public void union (int p, int q) {
		int rootP = parent(p);
		int rootQ = parent(q);
		if(rootP == rootQ) throw new RuntimeException("The elements are already connected");
		if(size[rootP] > size[rootQ]) 	{ data[rootQ] = data[rootP]; size[rootP] += size[rootQ];}
		else 							{ data[rootP] = data[rootQ]; size[rootQ] += size[rootP];}
		count --;// no of components reduces for every union operation
	}
	
	/**
	 * Return true if connected
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p, int q){
		return parent(p) == parent(q);
	}
	
	/**
	 * No of components
	 * @return
	 */
	public int noOfComponents(){
		return count;
	}
	
	/**
	 * return parent of element 
	 * @param i
	 * @return
	 */
	public int parent(int i){
		while(i != data[i]){
			i = data[i];
			data[i]=data[data[i]]; // path compression
		}
		return i;
	}
}
