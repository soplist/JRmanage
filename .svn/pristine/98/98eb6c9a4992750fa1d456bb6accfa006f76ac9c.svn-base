package com.bureau.biz.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.MemoBiz;
import com.bureau.biz.RepairBiz;
import com.bureau.dao.MemoDao;
import com.bureau.dao.RepairDao;
import com.bureau.pojo.Carrecord;
import com.bureau.pojo.Memo;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Repair;

public class RepairBizImpl implements RepairBiz{
	//注入Dao
	private RepairDao repairDao;
	public void setRepairDao(RepairDao repairDao) {
		this.repairDao = repairDao;
	}

	public List<Repair> findBytime(String carnumber, String idStart, String idEnd){
		String hql="from Repair p";
		String counthql="select count(p.id) from Repair p";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件
		if(carnumber!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.carnumber like :carnumber";
			counthql+=" where p.carnumber like :carnumber";
			params.put("carnumber", "%"+carnumber+"%");
		}else if(carnumber==null && idStart !=null && idEnd != null){
			hql+=" where p.retime between :a and :b";
			counthql+=" where p.retime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(carnumber!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.carnumber like :carnumber and p.retime between :a and :b";
			counthql+=" where p.carnumber like :carnumber and p.retime between :a and :b";
			params.put("carnumber", "%"+carnumber+"%");
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
			hql+=" where p.retime between :a and :b";
			counthql+=" where p.retime between :a and :b";
			params.put("a", lastdate);
			params.put("b", nowdate);
		}
		
		hql+=" order by p.id desc";
		counthql+=" order by p.id desc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Repair> prs = repairDao.list(hql, params);
		
		return prs;
	}
	
	//查询所有
	public PageData<Repair> findAll(Repair m,String idStart, String idEnd){
		String hql="from Repair p";
		String counthql="select count(p.id) from Repair p";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件
		if(m.getCarnumber()!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.carnumber like :carnumber";
			counthql+=" where p.carnumber like :carnumber";
			params.put("carnumber", "%"+m.getCarnumber()+"%");
		}else if(m.getCarnumber()==null && idStart !=null && idEnd != null){
			hql+=" where p.retime between :a and :b";
			counthql+=" where p.retime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(m.getCarnumber()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.carnumber like :carnumber and p.retime between :a and :b";
			counthql+=" where p.carnumber like :carnumber and p.retime between :a and :b";
			params.put("carnumber", "%"+m.getCarnumber()+"%");
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
			hql+=" where p.retime between :a and :b";
			counthql+=" where p.retime between :a and :b";
			params.put("a", lastdate);
			params.put("b", nowdate);
		}
		
		hql+=" order by p.id desc";
		counthql+=" order by p.id desc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Repair> prs = repairDao.list(hql, params,
				(m.getPage()-1)*m.getRows(),m.getRows());
		PageData<Repair> prr=new PageData<Repair>();
		//查询用户的数量
		Integer total = repairDao.count(counthql, params);
		//放入pageDate
		prr.setRows(prs);
		prr.setTotal(total);
		return prr;
	}
	
	public boolean add(Repair repair){
		return repairDao.add(repair);
	}
	public boolean upd(Repair repair){
		return repairDao.upd(repair);
	}
	public boolean del(Repair repair){
		return repairDao.del(repair);
	}
}
