package com.bureau.biz.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.PrivatecarBiz;
import com.bureau.dao.PrivatecarDao;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Privatecar;
import com.bureau.pojo.Publiccar;

public class PrivatecarBizImpl implements PrivatecarBiz {
	//注入Dao
	private PrivatecarDao prDao;
	public void setPrDao(PrivatecarDao prDao) {
		this.prDao = prDao;
	}
	//分页查询
	public PageData<Privatecar> findAll(Privatecar pr) {
		String hql="from Privatecar p";
		String counthql="select count(p.id) from Privatecar p";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件
		if(pr.getCarnumber()!=null){
			hql+=" where p.carnumber like :carnumber";
			counthql+=" where p.carnumber like :carnumber";
			params.put("carnumber", "%"+pr.getCarnumber()+"%");
		}
		hql+=" order by p.id asc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Privatecar> prs = prDao.list(hql, params,
				(pr.getPage()-1)*pr.getRows(),pr.getRows());
		PageData<Privatecar> prr=new PageData<Privatecar>();
		//查询用户的数量
		Integer total = prDao.count(counthql, params);
		//放入pageDate
		prr.setRows(prs);
		prr.setTotal(total);
		return prr;
	}
	//增加
	public boolean add(Privatecar pr) {
		return prDao.add(pr);
	}
	//修改
	public boolean upd(Privatecar pr) {
		return prDao.upd(pr);
	}
	//删除
	public boolean del(Privatecar pr) {
		return prDao.del(pr);
	}
	//根据ID查询
	public Privatecar findById(int id) {
		return prDao.get(id);
	}
	//根据车牌查询
	public Privatecar findByCarnumber(String carnumber) {
		String hql="from Privatecar p where p.carnumber=:carnumber";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("carnumber", carnumber);
		return prDao.get(hql, params);
	}
	public List<Privatecar> findAll() {
		String hql="from Privatecar p";
		hql+=" order by p.id asc";
		
		return prDao.list(hql, null);
	}
		
}