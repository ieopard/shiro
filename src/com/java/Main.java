package com.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.java.framework.freemarker.FreeMarkerGenerate;
import com.java.framework.freemarker.Models;

public class Main {

	
	public static void main(String[] args) {
		
		String srcPath = "E:\\environment\\springsource\\workspace\\shiro\\build\\sample";
//		String targetPath = path.substring(0,itemIndex+5)+"\\module\\Role";
//		String targetPath = "E:\\environment\\springsource\\workspace\\shiro\\src\\com\\java\\module\\viewUser";
//		String targetPath = "E:\\environment\\springsource\\workspace\\shiro\\src\\com\\java\\module\\privilegeRole";
//		String targetPath = "E:\\environment\\springsource\\workspace\\msh\\src\\com\\java\\module";
		String targetPath = "E:\\environment\\springsource\\workspace\\exam\\src\\com\\java\\module";
		try {
			
			new FreeMarkerGenerate().copyFolder(srcPath,targetPath+"\\paper",new Models("Paper","paper"),"java","sample");
			
//			Collection<String> c = new ArrayList<String>();
			
//			c = Arrays.asList("a","b","c","d");
			
//			System.out.println(c.toArray(new String[]{}).length);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
}

