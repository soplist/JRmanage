package com.bureau.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bureau.biz.GlobalBiz;
import com.bureau.biz.LogBiz;
import com.bureau.pojo.Global;
import com.bureau.pojo.Log;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Personfile;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class GlobalAction extends BaseAction implements ModelDriven<Global>{
	//注入Biz
	private GlobalBiz globalBiz;
	public void setGlobalBiz(GlobalBiz globalBiz) {
		this.globalBiz = globalBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}

	//实体类
	private Global glb;
	public Global getGlb() {
		return glb;
	}
	public void setGlb(Global glb) {
		this.glb = glb;
	}
	
	//查询所有
	public void findAllGlobal(){
		PageData<Global> pd = globalBiz.findGlobalByAll(glb);
		this.writeJSON(pd);
	}

	//add添加方法
	public void add(){
		System.out.println(glb.getOutput()+" ###### "+glb.getHkm());
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		try{
			boolean res = globalBiz.addGlobal(glb);
			if(res){
				tj.setMessage(SysConst.ADDSUCCESS);
				tj.setSuccess(true);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		this.writeJSON(tj);
	} 
	
	//upd修改方法
	public void upd(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.UPDFAIL);
		boolean res = globalBiz.updGlobal(glb);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("修改全局配置参数");
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
	//del删除方法
	public void del(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		boolean res = globalBiz.delGlobal(glb);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("删除全局配置参数");
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
	
	//查看数据库是否已存在
	public void isbe(){
		if(glb.getOutput()!=null){
			Global g = null;
			g = globalBiz.findByOutput(glb.getOutput());
			boolean result = false;
			if(g!=null){
				result = true ;
			}
			this.writeJSON(result);
		}
	}
	public void findByOutput(){
		System.out.println(glb.getOutput()+"+1111");
		glb = globalBiz.findByOutput(glb.getOutput());
		System.out.println(glb.getOutput()+"+1111");
		this.writeJSON(glb);
	}
	public void findGlobal(){
		List<Global> tos = globalBiz.findAll();	
		this.writeJSON(tos);
	}
	public Global getModel() {
		if(glb==null){
			glb=new Global();
		}
		return glb;
	}
}
