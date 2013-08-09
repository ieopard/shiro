package com.java.framework.freemarker;

import java.io.*;
import java.util.*;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;


public class FreeMarkerGenerate {
	
	
	public void generate(File templateFile,String storagePath,Models model,String suffix) throws Exception{
		
		String templateName =  templateFile.getName();
		
		//获取Dao，Impl名称
		templateName = templateName.substring(0, templateName.indexOf("."));
		
		//固定
		Configuration config = new Configuration();
		config.setObjectWrapper(new DefaultObjectWrapper());
		
		//模板路径
		config.setDirectoryForTemplateLoading(new File(templateFile.getParent()));
		
		
		Map<String,Object> root = new HashMap<String,Object>();
		
		
		root.put("name",model.getName());
		root.put("packageName",model.getPackageName());
		
		Template template = config.getTemplate(templateFile.getName());
		
		//先创建文件夹路径
		createFolder(storagePath);
		//存储文件
		File file = new File(storagePath+"\\"+model.getName()+templateName+"."+suffix);
		
		FileOutputStream out = new FileOutputStream(file);
		
		Writer writer = new OutputStreamWriter(out);
		
		template.process(root, writer);
		
		writer.flush();

	}
	
	/**
	 * @param templatePath 模板地址
	 * @param storagePath  储存地址
	 * @param model  实体
	 * @param suffix 后缀
	 * @param templateName 模板名称
	 * @throws Exception
	 */
	public void copyFolder(String templatePath,String storagePath,Models model,String suffix,String templateName) throws Exception{
		
		File files = new File(templatePath);
		
		for(File file : files.listFiles()){
			
			if(file.isDirectory()){
				copyFolder(file.getPath(),storagePath,model,suffix,templateName);
			}else{
				if(file.getName().endsWith(".ftl")){
					//storagePath = storagePath+"\\"+model.getPackageName();
					//System.out.println(storagePath);
					generate(file,storagePath+resultPath(templateName,file.getPath()),model,suffix);
				}
			}
		}
	}
	/**
	 * @param name  sample
	 * @param path "E:\\environment\\springsource\\workspace\\mvcsh\\build\\sample\\dao\\impl\\a.??";\\dao\\impl
	 * @return
	 */
	public String resultPath(String name,String path){
		
		if(path.indexOf(name) == -1){
			return "";
		}
		String resultPath = path.substring(path.indexOf(name)+name.length(),path.lastIndexOf("\\"));
		
		return resultPath == null ? "" : resultPath;
	}
	
	
	public void createFolder(String storagePath){
		
		
		File file = new File(storagePath);
		
		try{
			if(!file.exists()){
				file.mkdirs();
				System.out.println("创建成功");
			}
		}catch(Exception e){
			System.out.println("创建失败");
		}
	}
	
	public static void main(String[] args) {
		
		String path = FreeMarkerGenerate.class.getClassLoader().getResource(".").getPath().substring(1);
		
//		System.out.println(path.substring(0,itemIndex+5));
		
		int itemIndex = path.indexOf("mvcsh");
		String srcPath = "E:\\environment\\springsource\\workspace\\mvcsh\\build\\sample";
//		String targetPath = path.substring(0,itemIndex+5)+"\\module\\Role";
		String targetPath = "E:\\environment\\springsource\\workspace\\mvcsh\\build\\test";
		
		try {
			
			new FreeMarkerGenerate().copyFolder(srcPath,targetPath,new Models("User","user"),"java","sample");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



