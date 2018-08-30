package com.bureau.action;


import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.biz.DictionaryBiz;
import com.bureau.biz.LogBiz;
import com.bureau.biz.NewepcarBiz;
import com.bureau.pojo.Dictionary;
import com.bureau.pojo.Log;
import com.bureau.pojo.Newepcar;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class NewepcarAction extends BaseAction implements ModelDriven<Newepcar>{
	private Newepcar ca;
	public Newepcar getCa() {
		return ca;
	}

	public void setCa(Newepcar ca) {
		this.ca = ca;
	}

	public Newepcar getModel() {
		if(ca==null){
			ca=new Newepcar();
		}
		return ca;
	}
	//接收前台参数
	private String idStart;
	public void setIdStart(String idStart) {
		this.idStart = idStart;
	}
	public String getIdStart() {
		return idStart;
	}
	private String idEnd;
	public void setIdEnd(String idEnd) {
		this.idEnd = idEnd;
	}
	public String getIdEnd() {
		return idEnd;
	}
	//注入BIZ
	private NewepcarBiz newepcarBiz;
	public void setNewepcarBiz(NewepcarBiz newepcarBiz) {
		this.newepcarBiz = newepcarBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}
	
	
	private static List<Newepcar>  ne = null;
	//列表展示
	public void list(){
		PageData<Newepcar>  newepcar = newepcarBiz.findAll(ca,idStart,idEnd);
		this.writeJSON(newepcar,new String[]{"customer"});
	}
	public void list01(){
		ne = newepcarBiz.findAll01(ca,idStart,idEnd);
		this.writeJSON(ne,new String[]{"customer"});
	}
	public void findBytime(){
		List<Newepcar> list = newepcarBiz.findBytime(ca.getCarnumber(),idStart, idEnd);
		System.out.println(idEnd+idStart);
		this.writeJSON(list);
	}
	//增加
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		String nowTime = Quarter.getNowTime01();
		ca.setCreatetime(nowTime);
  		boolean res = newepcarBiz.add(ca);
		if(res){
			tj.setMessage(SysConst.ADDSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	//修改
	public void updata(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.UPDFAIL);
		String nowTime = Quarter.getNowTime();
		ca.setCreatetime(nowTime);
		boolean res=newepcarBiz.upd(ca);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("修改新装车辆信息！");
			log.setPeople(us.getLname());
			//时间
			Date day=new Date();    
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			log.setCreatetime(df.format(day));
			log.setIsfinish(1);
			logBiz.addLog(log);	
		}
		this.writeJSON(tj);
	}
	//删除
	public void del(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.DELFAIL);
		boolean res = newepcarBiz.del(ca);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("删除新装车辆信息记录一条！");
			log.setPeople(us.getLname());
			//时间
			Date day=new Date();    
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			log.setCreatetime(df.format(day));
			log.setIsfinish(1);
			logBiz.addLog(log);	
		}
		this.writeJSON(tj);
	}
	//根据id查询
	public void findById(){
		ca=newepcarBiz.findById(ca.getId());
		this.writeJSON(ca);
	}
	//实现导出列表功能
	public void exportExcel() throws IOException{
		HSSFWorkbook wb=null;
		try{
			wb = newepcarBiz.export(ne);
			response.reset();//清除缓冲中的数据
			response.setContentType("application/vnd.ms-excel;charset=utf-8");  
			response.setHeader("Content-disposition", "attachment;filename=export.xls");
			response.flushBuffer();//将缓冲区中的所有数据发送到客户端
			OutputStream ouputStream = response.getOutputStream();  
			wb.write(ouputStream);  
			ouputStream.flush();  
			ouputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private DictionaryBiz dicBiz;
	public void setDicBiz(DictionaryBiz dicBiz) {
		this.dicBiz = dicBiz;
	}
	//查询字典表中所有存在的车辆类型
	public void findCarstyleList(){
		List<Dictionary> dparts=newepcarBiz.findCarstyleList();
		this.writeJSON(dparts,new String[]{"qUsers","parent","customer"});
	}
}
