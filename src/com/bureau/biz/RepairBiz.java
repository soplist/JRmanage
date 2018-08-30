package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.Memo;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Repair;

public interface RepairBiz {
	
	//查询所有
	public PageData<Repair> findAll(Repair repair,String idStart, String idEnd);
	//查询所有
	public List<Repair> findBytime(String carnumber, String idStart, String idEnd);
	//增加
	public boolean add(Repair repair);
	//修改
	public boolean upd(Repair repair);
	//删除
	public boolean del(Repair repair);
}
