package com.bureau.biz;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.pojo.Dictionary;
import com.bureau.pojo.Newepcar;
import com.bureau.pojo.PageData;


public interface NewepcarBiz {
	//分页查询所有
	public PageData<Newepcar> findAll(Newepcar newepcar, String idStart, String idEnd);
	//增加
	public boolean add(Newepcar newepcar);
	//修改
	public boolean upd(Newepcar newepcar);
	//删除
	public boolean del(Newepcar newepcar);
	//根据id查询
	public Newepcar findById(int id);
	//根据时间查询
	public List<Newepcar> findBytime(String carid, String idStart, String idEnd);
	//导出报表
	public HSSFWorkbook export(List<Newepcar> newepcar);
	/**
	 * 查询所有的车辆类型
	 * @return
	 */
	public List<Dictionary> findCarstyleList();
	public List<Newepcar> findAll01(Newepcar ca, String idStart, String idEnd);
	
	//按车辆类型统计(新装)车辆数量      by-李金2018-8-21
	public Integer findAllSum(Integer carstyle,String idStart, String idEnd);

}
