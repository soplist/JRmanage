package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.Goodsout;
import com.bureau.pojo.PageData;


public interface GoodsoutBiz {
	/**
	 * 根据Lname查找物品名称
	 * @param lname 物品名称
	 */
	public List<Goodsout> findBytime(String idStart, String idEnd);
	
	//add添加
	public boolean addGoodsout(Goodsout g);
	//del删除
	public boolean delGoodsout(Goodsout g);
	//查询所有
	public PageData<Goodsout> findGoodsoutAll(Goodsout g,String idStart, String idEnd);
}
