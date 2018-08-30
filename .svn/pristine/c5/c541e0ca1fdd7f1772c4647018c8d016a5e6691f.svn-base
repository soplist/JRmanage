package com.bureau.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.GoodsoutBiz;
import com.bureau.dao.GoodsoutDao;
import com.bureau.pojo.Goodsout;
import com.bureau.pojo.PageData;

public class GoodsoutBizImpl implements GoodsoutBiz{
	//Dao层注入
	private GoodsoutDao gdoDao;
	public void setGdoDao(GoodsoutDao gdoDao) {
		this.gdoDao = gdoDao;
	}

	/**
	 * 根据Lname查找物品名称
	 * @param lname 物品名称
	 */
	public List<Goodsout> findBytime(String idStart, String idEnd){
		Map<String, Object> params=new HashMap<String, Object>();	
		String hql="from Goodsout g where g.createtime between :a and :b";
		params.put("a", idStart);
		params.put("b", idEnd);
		return gdoDao.list(hql, params);
	}
	
	//add添加
	public boolean addGoodsout(Goodsout g){
		return gdoDao.add(g);
	}
	//del删除
	public boolean delGoodsout(Goodsout g){
		return gdoDao.del(g);
	}
	//查询所有
	public PageData<Goodsout> findGoodsoutAll(Goodsout g,String idStart, String idEnd){
		String hql="from Goodsout g ";
		String counthql="select count(g.id) from Goodsout g ";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件:按时间段查询
		if(idStart !=null && idEnd != null){
			hql+=" where g.createtime between :a and :b";
			counthql+=" where g.createtime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}
		hql+=" order by g.id desc";
		
		//查询所有用户
		List<Goodsout> list = gdoDao.list(hql, params,(g.getPage()-1)*g.getRows(),g.getRows());
		PageData<Goodsout> pd = new PageData<Goodsout>();
		//查询数量
		Integer total = gdoDao.count(counthql, params);
		//放入pageDate
		pd.setRows(list);
		pd.setTotal(total);
		return pd;
	}
}
