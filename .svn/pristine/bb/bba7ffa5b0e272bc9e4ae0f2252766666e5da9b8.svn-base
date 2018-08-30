package com.bureau.biz.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.LunchBiz;
import com.bureau.dao.LunchDao;
import com.bureau.pojo.Lunch;
import com.bureau.pojo.PageData;

public class LunchBizImpl implements LunchBiz{
	//注入LunchDao
	private LunchDao lchDao;
	public void setLchDao(LunchDao lchDao) {
		this.lchDao = lchDao;
	}

	public PageData<Lunch> findAll(Lunch lch,String idStart, String idEnd){
		String hql="from Lunch l";
		String counthql="select count(l.id) from Lunch l";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件:按时间段查询
		if(idStart !=null && idEnd != null){
			hql+=" where l.createtime between :a and :b";
			counthql+=" where l.createtime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else{//查询获取所有(两个月内)总条数,从lastdate到nowdate
			DateFormat dformat = DateFormat.getDateInstance();
	  		String nowdate = dformat.format(new Date());//当月
	  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	 		 Calendar lastDate = Calendar.getInstance();  
	 		 lastDate.set(Calendar.DATE, 1);// 设为当前月的1号  
	 		 lastDate.add(Calendar.MONTH, -2);//两月前  
	 		String lastdate = sdf.format(lastDate.getTime()); 
			
			hql+=" where l.createtime between :a and :b";
			counthql+=" where l.createtime between :a and :b";
			params.put("a", lastdate);
			params.put("b", nowdate);
		}
		hql+=" order by id desc";
		//查询所有log信息
		List<Lunch> logs = lchDao.list(hql, params, (lch.getPage()-1)*lch.getRows(),lch.getRows());
		int total = lchDao.count(counthql, params);
		PageData<Lunch> pd = new PageData<Lunch>();
		pd.setRows(logs);
		pd.setTotal(total);
		return pd;
	}
	
	public List<Lunch> findBytime(String idStart, String idEnd){
		String hql="from Lunch l where l.createtime between :a and :b";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("a", idStart);
		params.put("b", idEnd);
		List<Lunch> lunch = lchDao.list(hql, params);
		return lunch;
	}
	
	public boolean addLunch(Lunch lch){
		return lchDao.add(lch);
	}
	public boolean updLunch(Lunch lch){
		return lchDao.upd(lch);
	}
	public boolean delLunch(Lunch lch){
		return lchDao.del(lch);
	}
}
