package com.bureau.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.LogBiz;
import com.bureau.dao.LogDao;
import com.bureau.pojo.Log;
import com.bureau.pojo.PageData;

public class LogBizImpl implements LogBiz{
	//注入logDao
	private LogDao logDao;
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	public PageData<Log> findAllLog(Log log,String idStart, String idEnd) {
		String hql="from Log l";
		String counthql="select count(l.id) from Log l";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件:按时间段查询日志
		if(idStart !=null && idEnd != null){
			hql+=" where l.createtime between :a and :b";
			counthql+=" where l.createtime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}
		hql+=" order by id desc";
		//查询所有log信息
		List<Log> logs = logDao.list(hql, params, (log.getPage()-1)*log.getRows(),log.getRows());
		int total = logDao.count(counthql, params);
		PageData<Log> pd = new PageData<Log>();
		pd.setRows(logs);
		pd.setTotal(total);
		return pd;
	}
	
	public List<Log> findBytime(String idStart, String idEnd){
		String hql="from Log l where l.createtime between :a and :b";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("a", idStart);
		params.put("b", idEnd);
		List<Log> logs = logDao.list(hql, params);
		return logs;
	}
	

	public boolean addLog(Log log) {
		//时间
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		log.setCreatetime(df.format(day));
		return logDao.add(log);
	}

	public boolean delLog(Log log) {
		return logDao.del(log);
	}
}
