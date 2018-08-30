package com.bureau.biz.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.PaycarBiz;
import com.bureau.dao.PaycarDao;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Paycar;

public class PaycarBizImpl implements PaycarBiz {
	//注入Dao
	private PaycarDao paycarDao;
	
	public void setPaycarDao(PaycarDao paycarDao) {
		this.paycarDao = paycarDao;
	}
	//分页查询
	public PageData<Paycar> findAll(Paycar ca, String idStart, String idEnd) {
		String hql="from Paycar p";
		String counthql="select count(p.id) from Paycar p";
		Map<String, Object> params=new HashMap<String, Object>();
		System.out.println(ca.getCarid()+"+5555555555555");
		//模糊查询追加条件
		if(ca.getCarid()!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.carid like :carid";
			counthql+=" where p.carid like :carid";
			params.put("carid", "%"+ca.getCarid()+"%");
		}else if(ca.getCarid()==null && idStart!=null && idEnd!=null){
			hql+=" where p.paydate between :a and :b";
			counthql+=" where p.paydate between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(ca.getCarid()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			System.out.println(idEnd+idStart+ca.getCarid()+"4444");
			hql+=" where p.carid like :carid and p.paydate between :a and :b";
			counthql+=" where p.carid like :carid and p.paydate between :a and :b";
			params.put("carid", "%"+ca.getCarid()+"%");
			params.put("a", idStart);
			params.put("b", idEnd);
		}else{//查询获取所有(两个月内)总条数,从lastdate到nowdate
			hql+=" order by p.id desc";
			counthql+=" order by p.id desc";
		}
		
		
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Paycar> prs = paycarDao.list(hql, params,
				(ca.getPage()-1)*ca.getRows(),ca.getRows());
		PageData<Paycar> prr=new PageData<Paycar>();
		//查询用户的数量
		Integer total = paycarDao.count(counthql, params);
		//放入pageDate
		prr.setRows(prs);
		prr.setTotal(total);
		return prr;
	}
	public List<Paycar> findBytime(String carid,String idStart, String idEnd){
		String hql="from Paycar p";
		String counthql="select count(p.id) from Paycar p";
		Map<String, Object> params=new HashMap<String, Object>();
		System.out.println(carid+"+111111111111");
		if(carid!=null && idStart.isEmpty() && idEnd.isEmpty()){
			System.out.println(carid);
			hql+=" where p.carid like :carid";
			counthql+=" where p.carid like :carid";
			params.put("carid", "%"+carid+"%");
		}else if(carid==null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.paydate between :a and :b";
			counthql+=" where p.paydate between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(carid!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.carid like :carid and p.paydate between :a and :b";
			counthql+=" where p.carid like :carid and p.paydate between :a and :b";
			params.put("carid", "%"+carid+"%");
			params.put("a", idStart);
			params.put("b", idEnd);
		}else{//查询获取所有(两个月内)总条数,从lastdate到nowdate
			hql+=" order by p.id asc";
			counthql+=" order by p.id asc";
		}
		System.out.println(idEnd+idStart);
		System.out.println(hql);
		List<Paycar> car = paycarDao.list(hql, params);
		return car;
	}
	//增加
	public boolean add(Paycar ca) {
		return paycarDao.add(ca);
	}
	//修改
	public boolean upd(Paycar ca) {
		return paycarDao.upd(ca);
	}
	//删除
	public boolean del(Paycar ca) {
		return paycarDao.del(ca);
	}
	//根据ID查询
	public Paycar findById(int id) {
		return paycarDao.get(id);
	}
	
	
	//按车牌号码分组汇总统计 (充值金额) by-李金2018-8-16
	public List findAllSum(String carnumber,String idStart, String idEnd){
		String hql="from Paycar p where p.carid=:carnumber and p.paydate between :a and :b order by p.paydate desc";
		String counthql="select count(p.id) from Paycar p where p.carid=:carnumber and p.paydate between :a and :b order by p.id desc";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("carnumber", carnumber);
		params.put("a", idStart);
		params.put("b", idEnd);
		
		List<Paycar> car = paycarDao.list(hql, params);
		//查询记录条数
		Integer total = paycarDao.count(counthql, params);
		if(total == 0){
			return null;
		}else{
			Double sumpay=0.0;//充值金额
			for(int i=0; i<car.size();i++){
				sumpay += car.get(i).getMoney();
			}
			List li = new ArrayList();	
			li.add(sumpay);//充值金额0
			return li;
		}
	}
		
}