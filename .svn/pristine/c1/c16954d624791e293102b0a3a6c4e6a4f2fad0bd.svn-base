package com.bureau.biz;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.pojo.Carrecord;
import com.bureau.pojo.Publiccar;
import com.bureau.pojo.UseCarSum;

public interface UseCarSumBiz {
	//统计展示列表
	public UseCarSum findSumbyCar(String carnumber,Double balance,List re_list,List gas_list,List pay_list,String idStart,String idEnd);
	
	//导出列表数据功能
	public HSSFWorkbook export(List<UseCarSum> list);
}
