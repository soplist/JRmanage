package com.bureau.biz.impl;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;

import com.bureau.biz.ChangeCarSumBiz;
import com.bureau.pojo.ChangeCarSum;

public class ChangeCarSumBizImpl implements ChangeCarSumBiz{

	//统计展示列表,根据时间段查询统计，初始默认统计当月信息
	public ChangeCarSum findSumbyCar(String carstylename,Integer newsum,Integer updsum,Integer oldsum,String idStart,String idEnd){
		ChangeCarSum ccs = new ChangeCarSum();
		ccs.setCarstyle(carstylename);
		ccs.setNewsum(newsum);
		ccs.setUpdsum(updsum);
		ccs.setOldsum(oldsum);
		ccs.setStarttime(idStart);
		ccs.setEndtime(idEnd);
		return ccs;
	}
	
	/**
	 * 实现导出列表功能
	 * @param eplist 车辆维修记录统计信息
	 * @return  HSSFWorkbook 结果为excel表格式
	 */
	public HSSFWorkbook export(List<ChangeCarSum> list){
		String[] excelHeader = {"车辆类型", "新装车辆数","换装车辆数","报废车辆数","起始日期", "结束日期"};
		HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("车辆登记统计信息");
        
        HSSFRow row = sheet.createRow((int) 0);  
        row.setHeightInPoints(14F);
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        
        // 设置字体样式  
        Font titleFont = wb.createFont();  
        titleFont.setFontHeightInPoints((short)14);// 字体高度  
        titleFont.setFontName("黑体");// 字体样式  
        style.setFont(titleFont);
        //表头
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);  
            cell.setCellValue(excelHeader[i]);  
            cell.setCellStyle(style);  
            sheet.autoSizeColumn(i);// 设置宽度 
        }
        
        //表体内容
        for (int i = 0; i < list.size(); i++) {  
        	 row = sheet.createRow(i + 1); 	 
        	 row.createCell(0).setCellValue(list.get(i).getCarstyle());//车辆类型
        	 row.createCell(1).setCellValue(list.get(i).getNewsum());//新装车辆数
        	 row.createCell(2).setCellValue(list.get(i).getUpdsum());//换装车辆数
        	 row.createCell(3).setCellValue(list.get(i).getOldsum());//报废车辆数
        	 row.createCell(4).setCellValue(list.get(i).getStarttime());//起始日期
        	 row.createCell(5).setCellValue(list.get(i).getEndtime());//结束日期
        }
        return wb;
	}
}
