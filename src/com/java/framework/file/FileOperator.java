/**
 * 
 */
package com.java.framework.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author pengluwei
 * @since 2013-6-21上午11:33:52
 * @version 1.0
 */
public class FileOperator {


	/**
	 * @param req
	 * @param fileInputName  input名称
	 * @param uploadFolder   上传目录
	 */
	public static void FileUploads(MultipartHttpServletRequest req,String fileInputName,String uploadFolder) {
		 List<MultipartFile> files = req.getFiles(fileInputName);
		 String savePath = req.getSession().getServletContext().getRealPath(uploadFolder); 
		 
		 System.out.println("-------------------------------");
		 System.out.println(savePath);
		 
		 File saveFile = new File(savePath);
			if(!saveFile.exists()) saveFile.mkdirs();
		 
		 FileOutputStream fos = null;
		 
		 try {
			for(MultipartFile file : files){
				String fileName = file.getOriginalFilename();
				if(fileName == null || fileName.trim().length() == 0) continue;
				System.out.println(savePath+"\\"+fileName);
				fos = new FileOutputStream(new File(savePath+"\\"+fileName));
				fos.write(file.getBytes());
				fos.flush();
			 }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param req
	 * @param fileInputName  input名称
	 * @param uploadFolder   上传目录
	 */
	public static String FileUploadResultName(MultipartHttpServletRequest req,String fileInputName,String uploadFolder) {
		List<MultipartFile> files = req.getFiles(fileInputName);
		 String savePath = req.getSession().getServletContext().getRealPath(uploadFolder); 
		 File saveFile = new File(savePath);
			if(!saveFile.exists()) saveFile.mkdirs();
		 
		 FileOutputStream fos = null;
		 String fileName = null;
		 try {
			for(MultipartFile file : files){
				fileName = file.getOriginalFilename();
				if(fileName == null || fileName.trim().length() == 0) continue;
				fos = new FileOutputStream(new File(savePath+"\\"+fileName));
				fos.write(file.getBytes());
				fos.flush();
			 }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}


	

	/**
	 * @param request
	 * @param response
	 * @param uploadFolder 			 文件夹名称
	 * @param downloadFileName      下载文件名称
	 * @param downSetName			下载后设置名称
	 */
	public static void downloadFile(HttpServletRequest request,HttpServletResponse response,String downloadFolder,String downloadFileName,String downSetName){
		
		String realPath = request.getSession().getServletContext().getRealPath(downloadFolder);
		
		//设置响应类型
		response.setContentType("application/x-download");
		String downPath = realPath +"\\"+ downloadFileName;
		
		//读取准备下载的文件
		FileInputStream  input = null;
		//二进制流输出文件
		OutputStream out = null;
		
		try {
			downSetName = URLEncoder.encode(downSetName,"UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename="+downSetName);
			
			input = new FileInputStream(downPath);
			//输出文件
			out = response.getOutputStream();
			
			byte[] bytes = new byte[1024];
			int i;
			while((i = input.read(bytes)) != -1){
				out.write(bytes,0,i);
			}
			out.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(input != null)
					input.close();
				if(out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
