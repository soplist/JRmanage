package com.bureau.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bureau.biz.GoodsinBiz;
import com.bureau.biz.GoodsoutBiz;
import com.bureau.biz.LogBiz;
import com.bureau.pojo.Goodsin;
import com.bureau.pojo.Goodsout;
import com.bureau.pojo.Log;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class GoodsoutAction extends BaseAction implements ModelDriven<Goodsout>{
	//注入操作Biz
	private GoodsoutBiz gdoBiz;
	public void setGdoBiz(GoodsoutBiz gdoBiz) {
		this.gdoBiz = gdoBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}
	//注入操作Biz
	private GoodsinBiz gdBiz;
	public void setGdBiz(GoodsinBiz gdBiz) {
		this.gdBiz = gdBiz;
	}
	
	//定义页面需要传递的对象
	private Goodsout gdo;
	public Goodsout getGdo() {
		return gdo;
	}
	public void setGdo(Goodsout gdo) {
		this.gdo = gdo;
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
		PageData<Goodsout> pd = gdoBiz.findGoodsoutAll(gdo,idStart,idEnd);
		this.writeJSON(pd);
	}
	//根据时间段查询
	public void findBytime(){
		List<Goodsout> g = gdoBiz.findBytime(idStart,idEnd);
		this.writeJSON(g);
	}
	
	//添加
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		String nowTime = Quarter.getNowTime();//添加当前时间
		gdo.setCreatetime(nowTime);
		
		//修改：2018-08-24  by 李金  （领用后物品管理剩余数量变化）
		boolean result = false,res = false;
		Goodsin gd = gdBiz.findGoodsByLname(gdo.getNameid());//得到物品id
		if(gdo.getNum() <= gd.getRenum()){
			gd.setRenum(gd.getRenum()-gdo.getNum());
			gdBiz.updGoodsin(gd);
			result = true;
		}
		if(result == true){
			res = gdoBiz.addGoodsout(gdo);
		}
		
		//写入日志
		if(res){
			tj.setMessage(SysConst.ADDSUCCESS);
			tj.setSuccess(true);
	
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("物品领用添加操作");
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

		boolean res = gdoBiz.delGoodsout(gdo);		
		
		//写入日志
		if(res){
			
			
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("物品领用删除操作");
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
	public Goodsout getModel(){
		if(gdo == null){
			gdo = new Goodsout();
		}
		return gdo;
	}
}
