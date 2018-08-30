package com.bureau.biz.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.GascarBiz;
import com.bureau.dao.GascarDao;
import com.bureau.pojo.Gascar;
import com.bureau.pojo.PageData;

public class GascarBizImpl implements GascarBiz {
	//注入Dao
	private GascarDao gascarDao;
	
	
	public void setGascarDao(GascarDao gascarDao) {
		this.gascarDao = gascarDao;
	}
	//分页查询
	public PageData<Gascar> findAll(Gascar ca, String idStart, String idEnd) {
		String hql="from Gascar p";
		String counthql="select count(p.id) from Gascar p";
		Map<String, Object> params=new HashMap<String, Object>();
		System.out.println(ca.getCarid()+"+5555555555555");
		//模糊查询追加条件
		if(ca.getCarid()!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.carid like :carid";
			counthql+=" where p.carid like :carid";
			params.put("carid", "%"+ca.getCarid()+"%");
		}else if(ca.getCarid()==null && idStart!=null && idEnd!=null){
			hql+=" where p.ingas between :a and :b";
			counthql+=" where p.ingas between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(ca.getCarid()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			System.out.println(idEnd+idStart+ca.getCarid()+"4444");
			hql+=" where p.carid like :carid and p.ingas between :a and :b";
			counthql+=" where p.carid like :carid and p.ingas between :a and :b";
			params.put("carid", "%"+ca.getCarid()+"%");
			params.put("a", idStart);
			params.put("b", idEnd);
		}else{//查询获取所有(两个月内)总条数,从lastdate到nowdate
			hql+=" order by p.id desc";
			counthql+=" order by p.id desc";
		}
		
		
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Gascar> prs = gascarDao.list(hql, params,
				(ca.getPage()-1)*ca.getRows(),ca.getRows());
		PageData<Gascar> prr=new PageData<Gascar>();
		//查询用户的数量
		Integer total = gascarDao.count(counthql, params);
		//放入pageDate
		prr.setRows(prs);
		prr.setTotal(total);
		return prr;
	}
	public List<Gascar> findBytime(String carid,String idStart, String idEnd){
		String hql="from Gascar p";
		String counthql="select count(p.id) from Gascar p";
		Map<String, Object> params=new HashMap<String, Object>();
		System.out.println(carid+"+111111111111");
		if(carid!=null && idStart.isEmpty() && idEnd.isEmpty()){
			System.out.println(carid);
			hql+=" where p.carid like :carid";
			counthql+=" where p.carid like :carid";
			params.put("carid", "%"+carid+"%");
		}else if(carid==null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.ingas between :a and :b";
			counthql+=" where p.ingas between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(carid!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.carid like :carid and p.ingas between :a and :b";
			counthql+=" where p.carid like :carid and p.ingas between :a and :b";
			params.put("carid", "%"+carid+"%");
			params.put("a", idStart);
			params.put("b", idEnd);
		}else{//查询获取所有(两个月内)总条数,从lastdate到nowdate
			hql+=" order by p.id asc";
			counthql+=" order by p.id asc";
		}
		System.out.println(idEnd+idStart);
		System.out.println(hql);
		List<Gascar> car = gascarDao.list(hql, params);
		return car;
	}
	//增加
	public boolean add(Gascar ca) {
		return gascarDao.add(ca);
	}
	//修改
	public boolean upd(Gascar ca) {
		return gascarDao.upd(ca);
	}
	//删除
	public boolean del(Gascar ca) {
		return gascarDao.del(ca);
	}
	//根据ID查询
	public Gascar findById(int id) {
		return gascarDao.get(id);
	}
	
	
	//按车牌号码分组汇总统计 (加油金额、加油量) by-李金2018-8-16
	public List findAllSum(String carnumber,String idStart, String idEnd){
		String hql="from Gascar p where p.carid=:carnumber and p.ingas between :a and :b order by p.ingas desc";
		String counthql="select count(p.id) from Gascar p where p.carid=:carnumber and p.ingas between :a and :b order by p.id desc";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("carnumber", carnumber);
		params.put("a", idStart);
		params.put("b", idEnd);
		
		List<Gascar> car = gascarDao.list(hql, params);
		//查询记录条数
		Integer total = gascarDao.count(counthql, params);
		if(total == 0){
			return null;
		}else{
			Double sumgas=0.0;//加油量
			Double meneygas=0.0;//加油金额
			for(int i=0; i<car.size();i++){
				sumgas += car.get(i).getGas();
				meneygas += car.get(i).getMoney();
			}
			List li = new ArrayList();	
			li.add(sumgas);//加油量0
			li.add(meneygas);//加油金额1
			return li;
		}
	}
		
}