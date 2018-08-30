package com.bureau.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.GoodsinBiz;
import com.bureau.dao.GoodsinDao;
import com.bureau.pojo.Goodsin;
import com.bureau.pojo.PageData;

public class GoodsinBizImpl implements GoodsinBiz{
	//Dao层注入
	private GoodsinDao gdDao;
	public void setGdDao(GoodsinDao gdDao) {
		this.gdDao = gdDao;
	}

	/**
	 * 根据Lname查找物品名称
	 * @param lname 物品名称
	 */
	public Goodsin findGoodsByLname(String lname){
		if(lname==null){
			return null;
		}
		String hql="from Goodsin g where g.name like :name";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("name", "%"+lname+"%");
		return gdDao.get(hql, params);
	}
	
	//提供下拉框选择Goodsin的list
	public List<Goodsin> findGoods(){
		String hql="from Goodsin g order by g.id asc";
		Map<String, Object> params=new HashMap<String, Object>();
		return gdDao.list(hql, params);
	}
	
	//add添加
	public boolean addGoodsin(Goodsin g){
		return gdDao.add(g);
	}
	//upd修改
	public boolean updGoodsin(Goodsin g){
		return gdDao.upd(g);
	}
	//del删除
	public boolean delGoodsin(Goodsin g){
		return gdDao.del(g);
	}
	//查询所有
	public PageData<Goodsin> findGoodsinAll(Goodsin g,String lname){
		String hql="from Goodsin g ";
		String counthql="select count(g.id) from Goodsin g ";
		Map<String, Object> params=new HashMap<String, Object>();
		if(lname != null && !lname.equals("")){
			params.put("name", "%"+lname+"%");
			hql+= "where g.name like :name ";
			counthql += "where g.name like :name ";
		}
		hql+=" order by g.id asc";
		
		//查询所有用户
		List<Goodsin> list = gdDao.list(hql, params,(g.getPage()-1)*g.getRows(),g.getRows());
		PageData<Goodsin> pd = new PageData<Goodsin>();
		//查询数量
		Integer total = gdDao.count(counthql, params);
		//放入pageDate
		pd.setRows(list);
		pd.setTotal(total);
		return pd;
	}
}
