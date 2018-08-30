package com.bureau.biz.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.MemoBiz;
import com.bureau.dao.MemoDao;
import com.bureau.pojo.Carrecord;
import com.bureau.pojo.Memo;
import com.bureau.pojo.PageData;

public class MemoBizImpl implements MemoBiz{
	//注入MemoDao
	private MemoDao memoDao;
	public void setMemoDao(MemoDao memoDao) {
		this.memoDao = memoDao;
	}

	public List<Memo> findBytime(String title, String idStart, String idEnd){
		String hql="from Memo p";
		String counthql="select count(p.id) from Memo p";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件
		if(title!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.title like :title";
			counthql+=" where p.title like :title";
			params.put("title", "%"+title+"%");
		}else if(title==null && idStart !=null && idEnd != null){
			hql+=" where p.createtime between :a and :b";
			counthql+=" where p.createtime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(title!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.title like :title and p.createtime between :a and :b";
			counthql+=" where p.title like :title and p.createtime between :a and :b";
			params.put("title", "%"+title+"%");
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
			System.out.println(idEnd+idStart);
			hql+=" where p.createtime between :a and :b";
			counthql+=" where p.createtime between :a and :b";
			params.put("a", lastdate);
			params.put("b", nowdate);
		}
		
		hql+=" order by p.id desc";
		counthql+=" order by p.id desc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Memo> prs = memoDao.list(hql, params);
		
		return prs;
	}
	
	//查询所有
	public PageData<Memo> findAll(Memo m,String idStart, String idEnd){
		String hql="from Memo p";
		String counthql="select count(p.id) from Memo p";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件
		if(m.getTitle()!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.title like :title";
			counthql+=" where p.title like :title";
			params.put("title", "%"+m.getTitle()+"%");
		}else if(m.getTitle()==null && idStart !=null && idEnd != null){
			hql+=" where p.createtime between :a and :b";
			counthql+=" where p.createtime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(m.getTitle()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.title like :title and p.createtime between :a and :b";
			counthql+=" where p.title like :title and p.createtime between :a and :b";
			params.put("title", "%"+m.getTitle()+"%");
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
			System.out.println(idEnd+idStart);
			hql+=" where p.createtime between :a and :b";
			counthql+=" where p.createtime between :a and :b";
			params.put("a", lastdate);
			params.put("b", nowdate);
		}
		
		hql+=" order by p.id desc";
		counthql+=" order by p.id desc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Memo> prs = memoDao.list(hql, params,
				(m.getPage()-1)*m.getRows(),m.getRows());
		PageData<Memo> prr=new PageData<Memo>();
		//查询用户的数量
		Integer total = memoDao.count(counthql, params);
		//放入pageDate
		prr.setRows(prs);
		prr.setTotal(total);
		return prr;
	}
	
	public boolean addMemo(Memo m){
		return memoDao.add(m);
	}
	public boolean updMemo(Memo m){
		return memoDao.upd(m);
	}
	public boolean delMemo(Memo m){
		return memoDao.del(m);
	}
}
