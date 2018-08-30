package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.PageData;
import com.bureau.pojo.Paycar;


public interface PaycarBiz {
	//分页查询所有
	public PageData<Paycar> findAll(Paycar paycar, String idStart, String idEnd);
	//增加
	public boolean add(Paycar paycar);
	//修改
	public boolean upd(Paycar paycar);
	//删除
	public boolean del(Paycar paycar);
	//根据id查询
	public Paycar findById(int id);
	public List<Paycar> findBytime(String carid,String idStart, String idEnd);
	//按车牌号码分组汇总统计 (充值金额) by-李金2018-8-16
	public List findAllSum(String carnumber,String idStart, String idEnd);
}
