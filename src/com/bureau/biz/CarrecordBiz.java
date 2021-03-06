package com.bureau.biz;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.pojo.Carrecord;
import com.bureau.pojo.PageData;


public interface CarrecordBiz {
	//分页查询所有
	public PageData<Carrecord> findAll(Carrecord ca, String idStart, String idEnd);
	//增加
	public boolean add(Carrecord ca);
	//修改
	public boolean upd(Carrecord ca);
	//删除
	public boolean del(Carrecord ca);
	//根据id查询
	public Carrecord findById(int id);
	public List<Carrecord> findBytime(String carid, String idStart, String idEnd);

	//按车牌号码分组汇总统计
	public List findAllSum(String carnumber,String idStart, String idEnd);
	public HSSFWorkbook export(List<Carrecord> carrecord);
	//查询所有
	public List<Carrecord> findAll01(Carrecord ca, String idStart, String idEnd);
}
