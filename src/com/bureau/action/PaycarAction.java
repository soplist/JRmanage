package com.bureau.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bureau.biz.CarrecordBiz;
import com.bureau.biz.GascarBiz;
import com.bureau.biz.LogBiz;
import com.bureau.biz.PaycarBiz;
import com.bureau.pojo.Carrecord;
import com.bureau.pojo.Gascar;
import com.bureau.pojo.Log;
import com.bureau.pojo.Lunch;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Paycar;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class PaycarAction extends BaseAction implements ModelDriven<Paycar>{
	private Paycar paycar;

	public Paycar getPaycar() {
		return paycar;
	}
	public void setPaycar(Paycar paycar) {
		this.paycar = paycar;
	}
	public Paycar getModel() {
		if(paycar==null){
			paycar=new Paycar();
		}
		return paycar;
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
	private PaycarBiz paycarBiz;
	public void setPaycarBiz(PaycarBiz paycarBiz) {
		this.paycarBiz = paycarBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}
	
	
	//列表展示
	public void list(){
		PageData<Paycar> tos = paycarBiz.findAll(paycar,idStart,idEnd);	
		this.writeJSON(tos);
	}
	public void findBytime(){
		System.out.println("idEnd+"+idEnd+"idStart+"+idStart+"carnum+"+paycar.getCarid());
		List<Paycar> list = paycarBiz.findBytime(paycar.getCarid(),idStart,idEnd);
		
		this.writeJSON(list);
	}
	//增加
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		String nowTime = Quarter.getNowTime01();
		paycar.setCreatetime(nowTime);
  		boolean res = paycarBiz.add(paycar);
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
		paycar.setCreatetime(nowTime);
		boolean res=paycarBiz.upd(paycar);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	//删除
	public void del(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.DELFAIL);
		boolean res = paycarBiz.del(paycar);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("删除一条充值记录");
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
		paycar=paycarBiz.findById(paycar.getId());
		this.writeJSON(paycar);
	}
}
