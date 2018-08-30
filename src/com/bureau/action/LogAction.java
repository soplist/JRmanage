package com.bureau.action;

import java.util.List;

import com.bureau.biz.LogBiz;
import com.bureau.pojo.Log;
import com.bureau.pojo.PageData;
import com.bureau.pojo.TempJson;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class LogAction extends BaseAction implements ModelDriven<Log>{
	//传递的实体
	private Log log;
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
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
	
	//查找所有的log
	public void findAllLogs(){
		PageData<Log> logs = logBiz.findAllLog(log,idStart,idEnd);
		this.writeJSON(logs);	
	}
	//删除记录
	public void logDel(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.DELFAIL);
		boolean res = logBiz.delLog(log);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	
	//按时间段查询日志
	public void findlogBytime(){
		List<Log> list = logBiz.findBytime(idStart,idEnd);
		this.writeJSON(list);
	}
	
	//ModelDriven
	public Log getModel() {
		if(log==null){
			log=new Log();
		}
		return log;
	}
}
