package org.my.collections.basic;
import java.util.EmptyStackException;


/**
 * Fixed length array based queue implementation.
 * Data in the array is looped around as ring structure rather than reseting
 * by replacing the data back to index 0 once array end is reached an elements have been dequeued
 *
 **/
public class RingQueue<T> implements Queue<T> {

  private T[] data = null;
  private int head = 0, tail = 0;
  private int count = 0;

  public RingQueue(int size){
    this.data = (T []) new Object[size];
  }

	public void enque(T obj){
    checkAndIncrTail();
    this.data[tail++] = obj;
    this.count++;
  }

	public T deque() throws EmptyStackException{

  }

	public boolean isEmpty(){
    return this.count == 0;
  }
	
	public int size(){
    return this.count;
  }

 private void checkAndIncrTail(){
   if(tail+1 == data.length){
     if(head == 0) throw new IndexOutOfBoundsException();
     
}
	
