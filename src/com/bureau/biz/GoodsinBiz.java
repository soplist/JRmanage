package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.Goodsin;
import com.bureau.pojo.PageData;


public interface GoodsinBiz {
	/**
	 * 根据Lname查找物品名称
	 * @param lname 物品名称
	 */
	public Goodsin findGoodsByLname(String lname);
	
	//add添加
	public boolean addGoodsin(Goodsin g);
	//upd修改
	public boolean updGoodsin(Goodsin g);
	//del删除
	public boolean delGoodsin(Goodsin g);
	//查询所有
	public PageData<Goodsin> findGoodsinAll(Goodsin g,String lname);
	//提供下拉框选择Goodsin的list
	public List<Goodsin> findGoods();
}
