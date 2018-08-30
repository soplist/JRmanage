package com.bureau.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bureau.biz.CarrecordBiz;
import com.bureau.biz.GascarBiz;
import com.bureau.biz.LogBiz;
import com.bureau.pojo.Carrecord;
import com.bureau.pojo.Gascar;
import com.bureau.pojo.Log;
import com.bureau.pojo.Lunch;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class GascarAction extends BaseAction implements ModelDriven<Gascar>{
	private Gascar gascar;

	public Gascar getGascar() {
		return gascar;
	}
	public void setGascar(Gascar gascar) {
		this.gascar = gascar;
	}
	public Gascar getModel() {
		if(gascar==null){
			gascar=new Gascar();
		}
		return gascar;
	}
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
	private GascarBiz gascarBiz;
	public void setGascarBiz(GascarBiz gascarBiz) {
		this.gascarBiz = gascarBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}
	
	//列表展示
	public void list(){
		PageData<Gascar> tos = gascarBiz.findAll(gascar,idStart,idEnd);	
		this.writeJSON(tos);
	}
	public void findBytime(){
		System.out.println("idEnd+"+idEnd+"idStart+"+idStart+"carnum+"+gascar.getCarid());
		List<Gascar> list = gascarBiz.findBytime(gascar.getCarid(),idStart,idEnd);
		
		this.writeJSON(list);
	}
	//增加
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		String nowTime = Quarter.getNowTime();
		gascar.setCreatetime(nowTime);
  		boolean res = gascarBiz.add(gascar);
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
		gascar.setCreatetime(nowTime);
		boolean res=gascarBiz.upd(gascar);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("修改一条加油记录信息");
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
		boolean res = gascarBiz.del(gascar);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("删除一条加油记录");
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
		gascar=gascarBiz.findById(gascar.getId());
		this.writeJSON(gascar);
	}
}
