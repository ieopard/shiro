package com.java.framework.freemarker;
public class Models{
	
	private String name;
	
	private String packageName;
	
	
	public Models(String name, String packageName) {
		this.name = name;
		this.packageName = packageName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
}