package com.bureau.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bureau.biz.GoodsinBiz;
import com.bureau.biz.LogBiz;
import com.bureau.pojo.Goodsin;
import com.bureau.pojo.Log;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class GoodsinAction extends BaseAction implements ModelDriven<Goodsin>{
	//注入操作Biz
	private GoodsinBiz gdBiz;
	public void setGdBiz(GoodsinBiz gdBiz) {
		this.gdBiz = gdBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}
	
	//定义页面需要传递的对象
	private Goodsin gd;
	public void setGd(Goodsin gd) {
		this.gd = gd;
	}
	public Goodsin getGd() {
		return gd;
	}
	//搜索物品名称参数
	private String lname;
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getLname() {
		return lname;
	}
	
	//查询所有
	public void findAll(){
		PageData<Goodsin> pd = gdBiz.findGoodsinAll(gd,lname);
		this.writeJSON(pd);
	}
	//根据Lname查找物品名称
	public void findGoodsByLname(){
		Goodsin g = gdBiz.findGoodsByLname(lname);
		this.writeJSON(g);
	}
	
	//提供下拉框选择Goodsin的list
	public void findGoodsList(){
		List<Goodsin> list = gdBiz.findGoods();
		this.writeJSON(list);
	}
	
	//添加
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		String nowTime = Quarter.getNowTime();//添加当前时间
		gd.setCreatetime(nowTime);
		boolean res = gdBiz.addGoodsin(gd);
		if(res){
			tj.setMessage(SysConst.ADDSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	//修改
	public void upd(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.UPDFAIL);
		String nowTime = Quarter.getNowTime();//添加当前时间
		gd.setCreatetime(nowTime);
		boolean res = gdBiz.updGoodsin(gd);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("修改办公物品信息");
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
		gd.setCreatetime(nowTime);
		boolean res = gdBiz.delGoodsin(gd);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("删除办公物品信息");
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
	public Goodsin getModel(){
		if(gd == null){
			gd = new Goodsin();
		}
		return gd;
	}
}
