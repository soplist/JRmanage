package com.bureau.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bureau.biz.DictionaryBiz;
import com.bureau.biz.LogBiz;
import com.bureau.pojo.Dictionary;
import com.bureau.pojo.Log;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class DictionaryAction extends BaseAction implements ModelDriven<Dictionary>{

	//注入dicBiz
	private DictionaryBiz dicBiz;
	public void setdicBiz(DictionaryBiz dicBiz) {
		this.dicBiz = dicBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}
	
	//实体类
	private Dictionary dic;
	public Dictionary getDic() {
		return dic;
	}
	public void setDic(Dictionary dic) {
		this.dic = dic;
	}
	
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		boolean res = dicBiz.addDictionary(dic);
		if(res){
			tj.setMessage(SysConst.ADDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("添加字典表信息(父子节点)");
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
	
	//通过父类获取字典表数据
	public void findDicByParent(){
		List<Dictionary> dics=dicBiz.findDicByParent(dic);
		this.writeJSON(dics,new String[]{"declares","tbcounts","objectives","honors","bases","brands","qUsers","abilities","projectsesForStateId","projectsesForNature"});	
	} 
	
	
	//通过父类ID获取对应的表格数据
	public void findDicPageDataByPid(){
		PageData<Dictionary> pd=dicBiz.findDicPageDataByPid(dic);
		this.writeJSON(pd,new String[]{"declares","tbcounts","objectives","honors","bases","brands","qUsers","abilities","projectsesForStateId","projectsesForNature"});	
	} 
	
	//通过级别查找字典表数据
	public void findDicByLev(){
		List<Dictionary> dics=dicBiz.findDicByLev(dic.getLev());
		this.writeJSON(dics,new String[]{"declares","tbcounts","objectives","honors","bases","brands","qUsers","abilities","projectsesForStateId","projectsesForNature"});	
	} 
	
	//通过ID查找字典表数据
	public void findById(){
		Dictionary dictionary=dicBiz.findById(dic.getId());
		this.writeJSON(dictionary,new String[]{"declares","tbcounts","objectives","honors","bases","brands","qUsers","abilities","projectsesForStateId","projectsesForNature"});	
	} 
	
	//更新字典表数据
	public void upd(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.UPDFAIL);
		boolean res = dicBiz.updDictionary(dic);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("更新字典表数据");
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
	

	public Dictionary getModel() {
		if(dic==null){
			dic=new Dictionary();
		}
		return dic;
	}
	
	
}
