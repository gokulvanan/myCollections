package org.my.collections.searching;

public class BST<Key extends Comparable<Key>,Value> implements OrderedOperations<Key,Value>{

	private class Node{
		private Key key;
		private Value value;
		private Node left,right;
		private int count;
		
		Node(Key k, Value val){
			this.key=k;
			this.value=val;
			this.count=0;
		}
	}
	
	private Node root;
	
	public void put(Key k, Value v) {
		root=put(root,k,v);
	}

	public Value get(Key k) {
		Node n = root;
		while(n != null){
			int cmp = k.compareTo(n.key);
			if(cmp > 0) n = n.right;
			else if (cmp < 0) n = n.left;
			else return n.value;
		}
		return null;
	}

	public void delete(Key k) {
		// TODO Auto-generated method stub
		
	}

	public boolean contains(Key k) {
		return get(k)!= null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return size(root);
	}

	public Iterable<Key> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	private Node put(Node n, Key k, Value v) {
		if(n == null) return new Node(k, v);
		int cmp = k.compareTo(n.key);
		if(cmp > 0) n.right = put(n.right,k,v);
		else if(cmp < 0) n.left = put(n.left,k,v);
		else	n.value=v;
		n.count= 1 + (size(n.left) + size(n.right));
		return n;
	}

	public Key min() {
		Node n = root;
		Key k = null;
		while(n != null){
			k = n.key;
			n = n.left;
		}
		return k;
	}

	public Key max() {
		Node n = root;
		Key k = null;
		while(n != null){
			k = n.key;
			n = n.right;
		}
		return k;
	}

	// largest key that less than or equal to  k 
	// more like <= k first item in desc sort
	public Key floor(Key k) {
		Node n = floor(root,k);
		return n.key;
	}
	
	private Node floor(Node x , Key k){
		if(x == null) return null;
		int cmp = k.compareTo(x.key);
		if(cmp == 0) return x;
		if(cmp < 0) return floor(x.left,k);
		
		Node t = floor(x.right,k);
		if(t == null) return x;
		else return t;
	}

	// smallest key thats greater than or equal to  k 
	// more like >= k  first item in asc sort
	public Key cieling(Key k) {
		Node n = cieling(root,k);
		return n.key;
	}

	private Node cieling(Node x , Key k){
		if(x == null) return null;
		int cmp = k.compareTo(x.key);
		if(cmp == 0) return x;
		if(cmp > 0) return cieling(x.right,k);
		
		Node t = cieling(x.left,k);
		if(t == null) return x;
		else return t;
	}
	public int rank(Key k) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Key select(int rank) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteMin() {
		// TODO Auto-generated method stub
		
	}

	public void deleteMax() {
		// TODO Auto-generated method stub
		
	}

	public int size(Key lo, Key high) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Iterable<Key> keys(Key lo, Key high) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int size(Node x){
		return (x == null) ? 0 : x.count;
	}
}
