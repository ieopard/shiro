package com.java.test;

public class FactoryBeanTest {
	
	
	private String attribute;
	
	Bean bean;
	
	
	public Object getObject(){
		
		FactoryBeanTest t = new FactoryBeanTest();
		
		bean = t.new Bean();
		
		return bean;
	}
	
	public static void main(String[] args) {
		
		
		System.out.println(new FactoryBeanTest().getObject());
		
	}
	
	class Bean{
		
		private String name;
		
		private String pwd;
		
	}
}


