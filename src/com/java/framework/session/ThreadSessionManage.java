package com.java.framework.session;

public class ThreadSessionManage {

	private ThreadSessionManage(){
		
	}
	private static ThreadSessionManage instance = new ThreadSessionManage();
	
	private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
	
	public ThreadSessionManage getInstance(){
		return instance;
	}
	
	public static void set(Object obj){
		threadLocal.set(obj);
	}
	
	public static Object get(){
		return threadLocal.get();
	}
	
}
