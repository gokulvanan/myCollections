package org.my.collections.exception;

public class MyCollectionException extends Exception{

	public MyCollectionException(String msg){
		super(msg);
		System.out.println(msg);
	}
	
	public MyCollectionException(Exception e){
		super(e);
		e.printStackTrace();
	}
}
