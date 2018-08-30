package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.Gascar;
import com.bureau.pojo.PageData;


public interface GascarBiz {
	//分页查询所有
	public PageData<Gascar> findAll(Gascar gascar, String idStart, String idEnd);
	//增加
	public boolean add(Gascar gascar);
	//修改
	public boolean upd(Gascar gascar);
	//删除
	public boolean del(Gascar gascar);
	//根据id查询
	public Gascar findById(int id);
	public List<Gascar> findBytime(String carid,String idStart, String idEnd);
	//按车牌号码分组汇总统计
	public List findAllSum(String carnumber,String idStart, String idEnd);
}
