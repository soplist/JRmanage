package com.bureau.biz;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.pojo.Carrecord;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Pricarrecord;


public interface PricarrecordBiz {
	//分页查询所有
	public PageData<Pricarrecord> findAll(Pricarrecord ca, String idStart, String idEnd);
	//增加
	public boolean add(Pricarrecord ca);
	//修改
	public boolean upd(Pricarrecord ca);
	//删除
	public boolean del(Pricarrecord ca);
	//根据id查询
	public Pricarrecord findById(int id);
	public List<Pricarrecord> findBytime(String carid, String idStart, String idEnd);
	public HSSFWorkbook export(List<Pricarrecord> ca);
	//查询所有
	public List<Pricarrecord> findAll01(Pricarrecord ca, String idStart, String idEnd);
}
