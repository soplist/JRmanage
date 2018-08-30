package com.bureau.biz;

import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.bureau.pojo.ChangeCarSum;

public interface ChangeCarSumBiz {
	
	//统计展示列表
	public ChangeCarSum findSumbyCar(String carstylename,Integer newsum,Integer updsum,Integer oldsum,String idStart,String idEnd);
	
	//导出列表数据功能
	public HSSFWorkbook export(List<ChangeCarSum> list);
}
