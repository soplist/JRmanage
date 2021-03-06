package com.bureau.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.GlobalBiz;
import com.bureau.dao.GlobalDao;
import com.bureau.pojo.Global;
import com.bureau.pojo.PageData;

public class GlobalBizImpl implements GlobalBiz{
	//注入glbDao
	private GlobalDao globalDao;
	public void setGlobalDao(GlobalDao globalDao) {
		this.globalDao = globalDao;
	}
	
	
	//add添加
	public boolean addGlobal(Global g){
		return globalDao.add(g);
	}
	//upd修改
	public boolean updGlobal(Global g){
		return globalDao.upd(g);
	}
	//del删除
	public boolean delGlobal(Global g){
		return globalDao.del(g);
	}
	//按排量查询是否存在
	public Global findByOutput(Double output){
		String hql="from Global g where g.output=:output";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("output", output);
		System.out.println(output+"+6565+"+hql);
		return globalDao.get(hql, params);
	}
	//查询所有
	public PageData<Global> findGlobalByAll(Global g){
		String hql="from Global g order by id asc";
		String counthql="select count(g.id) from Global g order by id asc";
		Map<String, Object> params=new HashMap<String, Object>();
		//查询所有用户
		List<Global> gd = globalDao.list(hql, params,(g.getPage()-1)*g.getRows(),g.getRows());
		PageData<Global> pd=new PageData<Global>();
		//查询用户的数量
		Integer total = globalDao.count(counthql, params);
		//放入pageDate
		pd.setRows(gd);
		pd.setTotal(total);
		return pd;
	}
	//查询全部
		public List<Global> findAll() {
			String hql="from Global g";
			hql+=" order by g.id asc";
			return globalDao.list(hql, null);
		}
}
