package com.bureau.unit;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

//文件工具类
public class FileUtil {
	//上传路径
	public static final String uploadpath=
		ServletActionContext.getServletContext().getRealPath("/upload/");
	
	/**
	 * 
	 * @param p_fName 文件名
	 * @return 新的文件名
	 */
	public static String newFileName(String p_fName){
		//取后缀名
		String p_n=p_fName.substring(p_fName.indexOf("."));
		
		//构建新文件名 =f_当前时间毫秒值.后缀
		String newName="f_"+System.currentTimeMillis()+p_n;
				
		return newName;
	}
	
	/**
	 * 
	 * @param file 临时文件对象
	 * @param newFileName 处理过的文件名
	 * @return 成功与否
	 */
	public static boolean uploadFile(File file,String newFileName){
		boolean res=true;
		System.out.println("1234566545645");
		if(file !=null){
			
			//文件夹对象
			File saveFileDir= new File(uploadpath);
			
			//第一次上传目录是不存在的
			if(!saveFileDir.exists()){
				saveFileDir.mkdirs();
			}
			
			//构建一个目的地的文件对象
			File saveFile =new File(saveFileDir,newFileName);
			
			try {
				//从临时区域拷贝到正式区域
				FileUtils.copyFile(file, saveFile);
			} catch (Exception e) {
				res=false;
				e.printStackTrace();
			}
			
		}
		
		return res;
	}
	
	
	/***
	 * 
	 * @param fileName 待下载文件名
	 * @param out 输出流
	 * @return 成功与否
	 */
	public static boolean downloadImg(String fileName,OutputStream out){
		boolean r=false;
		
		try {
			//构建待下载的文件对象
			File f =new File(uploadpath+"\\"+fileName);			
			
			if(f.exists()){
				
				//得到字节流输入流
				InputStream in =new FileInputStream(f);
				
				//循环提取字节流
				byte[] b =new byte[1024];
				int i=0;
				
				while((i=in.read(b))!=-1){
					out.write(b,0,i);
				}
				
				//关闭流
				in.close();
				out.close();
			}
			
		} catch (Exception e) {
			
		}
		
		return r;
	}
	
	/***
	 * 
	 * @param fileName 待删除文件名
	 * @return 成功与否
	 */
	public static boolean delImg(String fileName){
		boolean r=true;
		try {
			//构建待删除的文件对象
			File f =new File(uploadpath+"\\"+fileName);	
			
			if(f.exists()){
				f.delete();
			}
			
		} catch (Exception e) {
			r=false;
		}
		
		return r;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
