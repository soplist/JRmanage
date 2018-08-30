package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.Memo;
import com.bureau.pojo.PageData;

public interface MemoBiz {
	
	public List<Memo> findBytime(String title, String idStart, String idEnd);
	
	//查询所有
	public PageData<Memo> findAll(Memo m,String idStart, String idEnd);
	
	public boolean addMemo(Memo m);
	public boolean updMemo(Memo m);
	public boolean delMemo(Memo m);
}
