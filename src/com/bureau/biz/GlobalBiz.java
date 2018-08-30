package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.Global;
import com.bureau.pojo.PageData;

public interface GlobalBiz {
	
	//add添加
	public boolean addGlobal(Global glb);
	//upd修改
	public boolean updGlobal(Global glb);
	//del删除
	public boolean delGlobal(Global glb);
	//按排量查询是否存在
	public Global findByOutput(Double output);
	//查询所有
	public PageData<Global> findGlobalByAll(Global glb);
	public List<Global> findAll();
}
