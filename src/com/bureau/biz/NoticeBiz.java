package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.Notice;
import com.bureau.pojo.PageData;



public interface NoticeBiz {
	//add
	public boolean add(Notice nc);
	//update
	public boolean upd(Notice nc);
	//delete
	public boolean del(Notice nc);
	//findAll
	public PageData<Notice> findAll(Notice nc);
	//find by id
	public Notice findById(Integer id);
	List<Notice> findAll();//查询前十条公告12-20吕文
}
