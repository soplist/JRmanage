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
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import Decoder.BASE64Encoder;

import com.bureau.biz.LogBiz;
import com.bureau.biz.NoticeBiz;
import com.bureau.biz.UserBiz;
import com.bureau.pojo.Log;
import com.bureau.pojo.Notice;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class NoticeAction extends BaseAction implements ModelDriven<Notice> {
	//注入ncBiz/userBiz
	private NoticeBiz ncBiz;
	private UserBiz userBiz;
	private File daily;
	private String dailyContextType;
	private String dailyFileName;
	private String fileName;
	InputStream fileInputStream;
	OutputStream out;
	//模糊搜索企业参数
	private String schep;
    //实体类 and model
	private Notice nc;
	
	public String getSchep() {
		return schep;
	}
	public void setSchep(String schep) {
		this.schep = schep;
	}
	public File getDaily() {
		return daily;
	}
	public void setDaily(File daily) {
		this.daily = daily;
	}
	public String getDailyContextType() {
		return dailyContextType;
	}
	public void setDailyContextType(String dailyContextType) {
		this.dailyContextType = dailyContextType;
	}
	public String getDailyFileName() {
		return dailyFileName;
	}
	public void setDailyFileName(String dailyFileName) {
		this.dailyFileName = dailyFileName;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}
	
	
	public Notice getModel() {
		if (nc == null) {
			nc = new Notice();
		}
		return nc;
	}
	public Notice getNc() {
		return nc;
	}
	public void setNc(Notice nc) {
		this.nc = nc;
	}

	

	/**
	 * @return the name
	 */
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setNcBiz(NoticeBiz ncBiz) {
		this.ncBiz = ncBiz;
	}
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	private static String getFileCode(String fileName){
		String  extension=fileName.substring(fileName.lastIndexOf("."), fileName.length());
		//获得时间代码
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String code = df.format(new Date(System.currentTimeMillis()));
        return code+extension;
	}
	//增加日常文件
	public void add() throws UnsupportedEncodingException{
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		boolean res = false;	
		//上传文件
		if(dailyFileName == null){//如果不上传附件
        	nc.setFname("无");
        }else{//如果上传附件
        	String fileCode = getFileCode(dailyFileName);
            String dic=ServletActionContext.getServletContext().getRealPath("daily");
    		String path=dic+File.separator+fileCode;
    		//保存文件
    		try {
    			IOUtils.copy(new FileInputStream(daily), new FileOutputStream(new File(path)));
    			nc.setFname(dailyFileName);
    			nc.setFilecode(fileCode);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }
		QUser us = (QUser) session.get("login_users");
		QUser qu = userBiz.findUserByLname(us.getLname());
		nc.setCreatetime(Quarter.getNowTime());
		nc.setPeople(qu.getRname());
		res = ncBiz.add(nc);
		if(res){
			tj.setMessage(SysConst.ADDSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	public void filedown() throws IOException{
		
		Notice n = ncBiz.findById(nc.getId());
		fileName = n.getFname();
		
		
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
		String dic=ServletActionContext.getServletContext().getRealPath("daily/"+n.getFilecode());
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
	//删除新闻
	public void del(){
		try{
		    TempJson tj=new TempJson();
		    tj.setMessage(SysConst.DELFAIL);
		    
		    nc = ncBiz.findById(nc.getId());
		    //删除文件夹的文件
		    String dic = ServletActionContext.getServletContext().getRealPath("daily/"+nc.getFilecode());
		    File file = new File(dic);
		    if (file.isFile())
                file.delete();
		    boolean res = ncBiz.del(nc);
		
		    if(res){
			    tj.setMessage(SysConst.DELSUCCESS);
			    tj.setSuccess(true);
		    }
		  //写入日志
			if(res){
				//存入日志
				QUser us = (QUser) session.get("login_users");
				Log log = new Log();
				log.setTitle("删除一条公告记录###");
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
	//分页查询文件列表
	public void findAll(){
		PageData<Notice> pd = new PageData<Notice>();
		pd = ncBiz.findAll(nc);
		this.writeJSON(pd);
	}
	//查询倒序前十条公告12-20吕文
	public void list(){
		List<Notice> nc=ncBiz.findAll();
		this.writeJSON(nc,new String[]{"projectses","notices","logs","supers","qRoles","declares","tbcounts","objectives","honors","bases","brands","qUsers","abilities","projectsesForStateId","projectsesForNature"});
	}
	//查询公告详情12-20吕文
	public void findById(){
		System.out.println("id="+nc.getId());
		nc=ncBiz.findById(nc.getId());
		this.writeJSON(nc,new String[]{"projectses","notices","logs","supers","qRoles","declares","tbcounts","objectives","honors","bases","brands","qUsers","abilities","projectsesForStateId","projectsesForNature"});
	}
	

}
