package com.bureau.biz.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.PubliccarBiz;
import com.bureau.dao.PubliccarDao;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Publiccar;

public class PubliccarBizImpl implements PubliccarBiz {
	//注入Dao
	private PubliccarDao publiccarDao;
	public void setPubliccarDao(PubliccarDao publiccarDao) {
		this.publiccarDao = publiccarDao;
	}
	//分页查询
	public PageData<Publiccar> findAll(Publiccar pu) {
		String hql="from Publiccar p";
		String counthql="select count(p.id) from Publiccar p";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件
		if(pu.getCarnumber()!=null){
			hql+=" where p.carnumber like :carnumber";
			counthql+=" where p.carnumber like :carnumber";
			params.put("carnumber", "%"+pu.getCarnumber()+"%");
		}
		hql+=" order by p.id asc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Publiccar> pus = publiccarDao.list(hql, params,
				(pu.getPage()-1)*pu.getRows(),pu.getRows());
		PageData<Publiccar> prr=new PageData<Publiccar>();
		//查询用户的数量
		Integer total = publiccarDao.count(counthql, params);
		//放入pageDate
		prr.setRows(pus);
		prr.setTotal(total);
		return prr;
	}
	//增加
	public boolean add(Publiccar pu) {
		return publiccarDao.add(pu);
	}
	//修改
	public boolean upd(Publiccar pu) {
		return publiccarDao.upd(pu);
	}
	//修改余额
	public boolean updTable01(Publiccar pu) {//1-2改动
		String hql="update Publiccar b set b.balance=:balance where b.carnumber=:carnumber";
		Map<String, Object> params=new HashMap<String, Object>();
		System.out.println(pu.getCarnumber()+"*********"+pu.getBalance());
		params.put("balance", pu.getBalance());
		params.put("carnumber", pu.getCarnumber());
		if(publiccarDao.execute(hql, params)>-1){
			return true;
		}
		return false;
	}
	//删除
	public boolean del(Publiccar pu) {
		return publiccarDao.del(pu);
	}
	//根据ID查询
	public Publiccar findById(int id) {
		return publiccarDao.get(id);
	}
	public List<Publiccar> findAll() {
		String hql="from Publiccar p";
		hql+=" order by p.id asc";
		
		return publiccarDao.list(hql, null);
	}
	public Publiccar findByCarnumber(String carnumber) {
		String hql="from Publiccar p where p.carnumber=:carnumber";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("carnumber", carnumber);
		return publiccarDao.get(hql, params);
	}
		
}