package com.bureau.action;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bureau.biz.LogBiz;
import com.bureau.biz.PrivatecarBiz;
import com.bureau.pojo.Log;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Privatecar;
import com.bureau.pojo.Publiccar;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class PrivatecarAction extends BaseAction implements ModelDriven<Privatecar>{
	private Privatecar pr;
	public Privatecar getPr() {
		return pr;
	}
	public void setPr(Privatecar pr) {
		this.pr = pr;
	}
	
	public Privatecar getModel() {
		if(pr==null){
			pr=new Privatecar();
		}
		return pr;
	}
	
	//注入BIZ
	private PrivatecarBiz prBiz;
	public void setPrBiz(PrivatecarBiz prBiz) {
		this.prBiz = prBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}
	
	
	//列表展示
	public void list(){
		PageData<Privatecar> tos = prBiz.findAll(pr);	
		this.writeJSON(tos,new String[]{"privatecar"});
	}
	//增加
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		String nowTime = Quarter.getNowTime();
		System.out.println(pr.getBuytime());
		pr.setCreatetime(nowTime);
  		boolean res = prBiz.add(pr);
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
		pr.setCreatetime(nowTime);
		boolean res=prBiz.upd(pr);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("修改私车信息");
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
		boolean res = prBiz.del(pr);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("删除私车信息");
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
		pr=prBiz.findById(pr.getId());
		this.writeJSON(pr);
	}
	//根据车牌号查询
	public void findByCarnumber(){
		pr=prBiz.findByCarnumber(pr.getCarnumber());
		System.out.println(pr.getOutput());
		this.writeJSON(pr);
	}
	//查询所有车牌
	public void findcar(){
		List<Privatecar> tos = prBiz.findAll();	
		this.writeJSON(tos,new String[]{"privatecar"});
	}
}
