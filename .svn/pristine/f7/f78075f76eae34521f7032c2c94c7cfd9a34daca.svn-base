package com.bureau.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bureau.biz.LogBiz;
import com.bureau.biz.MemoBiz;
import com.bureau.pojo.Log;
import com.bureau.pojo.Memo;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class MemoAction extends BaseAction implements ModelDriven<Memo>{
	//注入操作Biz
	private MemoBiz memoBiz;	
	public void setMemoBiz(MemoBiz memoBiz) {
		this.memoBiz = memoBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}
	
	//实体类
	private Memo m;
	public void setM(Memo m) {
		this.m = m;
	}
	public Memo getM() {
		return m;
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
	
	//查询所有
	public void findAll(){
		PageData<Memo> pd = memoBiz.findAll(m,idStart,idEnd);
		this.writeJSON(pd);
	}
	//根据时间段查询
	public void findBytime(){
		List<Memo> g = memoBiz.findBytime(m.getTitle(),idStart,idEnd);
		this.writeJSON(g);
	}
	
	//添加
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		String nowTime = Quarter.getNowTime01();//添加当前时间
		m.setCreatetime(nowTime);
		boolean res = memoBiz.addMemo(m);
		if(res){
			tj.setMessage(SysConst.ADDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("添加备忘录记录@！");
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
	
	//修改
	public void upd(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.UPDFAIL);
		String nowTime = Quarter.getNowTime();//添加当前时间
		m.setCreatetime(nowTime);
		boolean res = memoBiz.updMemo(m);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("修改备忘录记录@！");
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
		String nowTime = Quarter.getNowTime();//添加当前时间
		m.setCreatetime(nowTime);
		boolean res = memoBiz.delMemo(m);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("删除备忘录记录@！");
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
	
	//ModelDriven
	public Memo getModel(){
		if(m == null)
			m = new Memo();
		return m;
	}
}
