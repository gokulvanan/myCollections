package org.my.collections.basic;

import java.util.Iterator;


/*
 * Simple Fixed array queue implementation of queue datastructure
 * If data entered in greater that size on queue it throws IndexOutOfBoundsException
 *
 * */
public class FixedQueue<T> implements Queue<T> {

  private T [] data;
  private int head=0,tail=0;

  public FixedQueue(int size){
    T[] data = (T []) new Object[size];
  }

  public void enque(T obj) {
    if(tail+1 == data.length) checkAndShift();
    data[tail++] = obj;
  }

  public T deque() {
    if(head+1 > tail) throw new IndexOutOfBoundsException();
    T obj = data[head];
    data[head++]=null;
    return obj;
  }

  public boolean isEmpty() {
    return (tail - head) == 0;
  }

  public int size() {
    return (tail - head);
  }

  private void checkAndShift(){
    if(head == 0) throw new IndexOutOfBoundsException();
    for(int i=head; i<tail; i++){
      data[i-head] = data[i];
    }
    tail = tail - head;
    head = 0;
  }

  public Iterator<T> iterator() {
    return null;
  }

}
