package com.bureau.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import Decoder.BASE64Encoder;

import com.bureau.biz.LogBiz;
import com.bureau.biz.SoftflagBiz;
import com.bureau.biz.UserBiz;
import com.bureau.pojo.Log;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;
import com.bureau.pojo.Softflag;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-9 下午4:19:56 
 * 类说明 
 */
public class SoftflagAction extends BaseAction implements ModelDriven<Softflag>   {
	
	private Softflag sf;
	private SoftflagBiz sfBiz;
	private File sffile;
	private String sffileContextType;
	private String sffileFileName;
	private String fileName;
	InputStream fileInputStream;
	OutputStream out;
	private UserBiz userBiz;
	
	public File getSffile() {
		return sffile;
	}
	public void setSffile(File sffile) {
		this.sffile = sffile;
	}
	public String getSffileContextType() {
		return sffileContextType;
	}
	public void setSffileContextType(String sffileContextType) {
		this.sffileContextType = sffileContextType;
	}
	public String getSffileFileName() {
		return sffileFileName;
	}
	public void setSffileFileName(String sffileFileName) {
		this.sffileFileName = sffileFileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public OutputStream getOut() {
		return out;
	}
	public void setOut(OutputStream out) {
		this.out = out;
	}

	public void setSfBiz(SoftflagBiz sfBiz) {
		this.sfBiz = sfBiz;
	}
	public Softflag getSf() {
		return sf;
	}
    public void setSf(Softflag sf) {
		this.sf = sf;
	}
    
    public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
    
  //注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}

	public Softflag getModel() {
		// TODO Auto-generated method stub
		if (sf == null) {
			sf = new Softflag();
		}
		return sf;
	}

	public void findAll(){
		PageData<Softflag> pd = new PageData<Softflag>();
		pd = sfBiz.findAll(sf);
		this.writeJSON(pd);
	}
	
	private static String getFileCode(String fileName){
		String  extension=fileName.substring(fileName.lastIndexOf("."), fileName.length());
		//获得时间代码
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String code = df.format(new Date(System.currentTimeMillis()));
        return code+extension;
	}
	
	public void add() throws UnsupportedEncodingException{
			TempJson tj=new TempJson();
			tj.setMessage(SysConst.ADDFAIL);
			boolean res = false;	
			//上传文件
			if(sffileFileName == null){//如果不上传附件
				sf.setFilename("无");
	        }else{//如果上传附件
	        	String fileCode = getFileCode(sffileFileName);
	            String dic=ServletActionContext.getServletContext().getRealPath("softflag");
	    		String path=dic+File.separator+fileCode;
	    		//保存文件
	    		try {
	    			IOUtils.copy(new FileInputStream(sffile), new FileOutputStream(new File(path)));
	    			sf.setFilename(sffileFileName);
	    			sf.setFilecode(fileCode);
	    		} catch (FileNotFoundException e) {
	    			e.printStackTrace();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	        }
			QUser us = (QUser) session.get("login_users");
			QUser qu = userBiz.findUserByLname(us.getLname());
			sf.setCreatetime(Quarter.getNowTime());
			sf.setPeopleid(qu.getRname());
			res = sfBiz.add(sf);
			if(res){
				tj.setMessage(SysConst.ADDSUCCESS);
				tj.setSuccess(true);
			}
			this.writeJSON(tj);
		}
	
        public void filedown() throws IOException{
		
	        Softflag s = sfBiz.findById(sf.getId());
		    fileName = s.getFilename();
		
		    HttpServletRequest request = ServletActionContext.getRequest();
		    String userAgent = ((HttpServletRequest) request).getHeader("User-Agent");
		    if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
			    fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		    }else if (userAgent.contains("Firefox")) {
			    BASE64Encoder base64Encoder = new BASE64Encoder();
			    fileName = "=?utf-8?B?"+ base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";
		    }
		    else {
			    // 非IE浏览器的处理：
			    fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		    }
		
		    fileName = fileName.toString().trim();
		    String dic=ServletActionContext.getServletContext().getRealPath("softflag/"+s.getFilecode());
		    //response.setHeader("content-disposition", "attachment;fileName="+URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20").replaceAll("%28", "\\(").replaceAll("%29", "\\)").replaceAll("%3B", ";").replaceAll("%40", "@").replaceAll("%23", "\\#").replaceAll("%26", "\\&").replaceAll("%2C","\\,"));
		    response.setHeader("content-disposition", "attachment;fileName="+fileName.replaceAll("\\+", "%20").replaceAll("%28", "\\(").replaceAll("%29", "\\)").replaceAll("%3B", ";").replaceAll("%40", "@").replaceAll("%23", "\\#").replaceAll("%26", "\\&").replaceAll("%2C","\\,"));
		    byte[] bytes=new byte[1024];
		    int len=0;
		    try {
			    fileInputStream = new FileInputStream(dic);
			    out=response.getOutputStream();
			    while((len=fileInputStream.read(bytes))>0){
				    out.write(bytes, 0, len);
			    }
		    } catch (FileNotFoundException e) {
			    e.printStackTrace();
		    }finally{
			    if(fileInputStream!=null){
				    fileInputStream.close();
			    }
			    if(out!=null){
				    out.close();
			    }
		    }
      }
        
       public void del(){
    		try{
    		    TempJson tj=new TempJson();
    		    tj.setMessage(SysConst.DELFAIL);
    		    
    		    sf = sfBiz.findById(sf.getId());
    		    //删除文件夹的文件
    		    String dic = ServletActionContext.getServletContext().getRealPath("softflag/"+sf.getFilecode());
    		    File file = new File(dic);
    		    if (file.isFile())
                    file.delete();
    		    boolean res = sfBiz.del(sf);
    		
    		    if(res){
    			    tj.setMessage(SysConst.DELSUCCESS);
    			    tj.setSuccess(true);
    		    }
    		  //写入日志
    			if(res){
    				//存入日志
    				QUser us = (QUser) session.get("login_users");
    				Log log = new Log();
    				log.setTitle("删除项目立项记录一条！");
    				log.setPeople(us.getLname());
    				//时间
    				Date day=new Date();    
    				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    				log.setCreatetime(df.format(day));
    				log.setIsfinish(1);
    				logBiz.addLog(log);	
    			}
    		    this.writeJSON(tj);
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}
    	}
}
