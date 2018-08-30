package com.bureau.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bureau.biz.GlobalBiz;
import com.bureau.biz.LogBiz;
import com.bureau.biz.PubliccarBiz;
import com.bureau.pojo.Global;
import com.bureau.pojo.Log;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Publiccar;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class PubliccarAction extends BaseAction implements ModelDriven<Publiccar>{
	private Publiccar pu;
	public Publiccar getPu() {
		return pu;
	}
	public void setPu(Publiccar pu) {
		this.pu = pu;
	}
	
	public Publiccar getModel() {
		if(pu==null){
			pu=new Publiccar();
		}
		return pu;
	}
	
	//注入BIZ
	private PubliccarBiz publiccarBiz;
	public void setPubliccarBiz(PubliccarBiz publiccarBiz) {
		this.publiccarBiz = publiccarBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}

	//列表展示
	public void list(){
		PageData<Publiccar> tos = publiccarBiz.findAll(pu);	
		this.writeJSON(tos,new String[]{"carrecord"});
	}
	//增加
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		String nowTime = Quarter.getNowTime();
		System.out.println(pu.getBuytime());
		pu.setCreatetime(nowTime);
  		boolean res = publiccarBiz.add(pu);
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
		System.out.println(nowTime);
		pu.setCreatetime(nowTime);
		boolean res=publiccarBiz.upd(pu);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("修改公司车辆信息");
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
	//修改余额
	public void updBalance(){		
		boolean res=publiccarBiz.updTable01(pu);
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.UPDFAIL);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("修改油卡余额");
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
		boolean res = publiccarBiz.del(pu);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("删除公司车辆信息");
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
		pu=publiccarBiz.findById(pu.getId());
		this.writeJSON(pu);
	}
	//根据车牌号查询
	public void findByCarnumber(){
		pu=publiccarBiz.findByCarnumber(pu.getCarnumber());
		this.writeJSON(pu);
	}
	
	//查询所有车牌
	public void findcar(){
		System.out.println("persionFileBiz");
		List<Publiccar> tos = publiccarBiz.findAll();	
		this.writeJSON(tos);
	}
}
