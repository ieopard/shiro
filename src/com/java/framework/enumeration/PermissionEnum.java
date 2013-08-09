package com.java.framework.enumeration;

public enum PermissionEnum {
	
	
	SUPERADMIN(0,"超级管理员"),
	FIRSTLEVEL(1,"一级授权"),
	SECONDLEVEL(2,"二级授权"),
	THREELEVEL(3,"三级授权");
	
	
	private int num;
	
	private String name;
	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private PermissionEnum(int num,String name){
		this.num = num;
		this.name = name;
		
	}
	
	public PermissionEnum switchType(PermissionEnum clazz){
		
		for(PermissionEnum permissionEnum : values()){
			if(permissionEnum == clazz){
				return permissionEnum;
			}
		}
		return null;
	}
}
