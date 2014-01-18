package org.my.collections.apps;

import org.my.collections.basic.UF;

/**
 * Datastructure that applies UF datastructure to solve the connected problem to know if a grid system
 * percolates. ( Is there path from top to bottom  when random sites are opened.)
 * 
 * Memory Footprint:
 * N^2 + 32N + 236 (Attributes) + 16 (Object OverHead) + (padding based on value of N)
 * N^2 + 32N + 252 + padding (Bytes)
 * @author gokulvanan
 *
 */
public class Percolation {

	private UF store = null; // 8 (2N + 2) + 88 + 8 (pointer) => 16N + 112 Bytes
	private UF buff = null; //  8 (2N + 2) + 88 + 8 (pointer)=> 16N + 112 Bytes
	private boolean[][] isOpen = null; // N^2 + 8 (pointer) Bytes
	private int size = 0; //4 Bytes

	public Percolation(int N){
		size = N;
		store = new UF(2 * N + 2);
		buff = new UF(2 * N + 2);
		isOpen = new boolean[N][N];
	}

	public void open(int i, int j){
		boolean state = isOpen[i][j];
		if(state) throw new RuntimeException("cell is alreay open");
		int indx = translate(i,j);
		if(i == 0) {
			store.union(0, indx);
			buff.connected(0, indx);
		}else if (i == size-1){
			store.union(indx, 2 * size + 1);
		}
		updateNeighBours(i,j,indx);
		isOpen[i][j] = true;
	}

	public boolean isOpen(int i, int j){
		return isOpen[i][j];
	}

	//check if site is open and connected to the top
	public boolean isFull(int i, int j){
		return buff.connected(translate(i,j), 0); 
	}

	public boolean percolates(){
		return store.connected(0, (2 * size) + 1);
	}

	private int translate(int row, int col){
		return row * col + col + 1;
	}

	private void updateNeighBours(int i, int j, int indx){
		for(int z=-1; z<2; z+=2){
			for(int k=-1; k<2; k+=2){
				try{
					int a = i + z;
					int b = j + k;
					if(isOpen[a][b]) {
						store.union(indx,translate(a,b));
						buff.union(indx,translate(a,b));
					}
				}catch(IndexOutOfBoundsException e){
					continue;
				}
			}

		}

	}

}
