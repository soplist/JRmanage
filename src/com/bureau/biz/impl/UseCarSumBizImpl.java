package com.bureau.biz.impl;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;

import com.bureau.biz.UseCarSumBiz;
import com.bureau.pojo.UseCarSum;

public class UseCarSumBizImpl implements UseCarSumBiz{

	//统计展示列表,根据时间段查询统计，初始默认统计当月信息
	public UseCarSum findSumbyCar(String carnumber,Double balance,List re_list,List gas_list,List pay_list,String idStart,String idEnd){
		UseCarSum ucs = new UseCarSum();	
		ucs.setCarnumber(carnumber);
		if(re_list == null){
			ucs.setKm(0);//公里数汇总
			ucs.setMoneykm(0.0);//金额汇总
			ucs.setLastkm(0);//最后公里数
			ucs.setUsenum(0);//行车次数
		}else{
			ucs.setKm((Integer)(re_list.get(0)));//公里数汇总
			ucs.setMoneykm((Double)(re_list.get(1)));//金额汇总
			ucs.setLastkm((Integer)(re_list.get(2)));//最后公里数
			ucs.setUsenum((Integer)(re_list.get(3)));//行车次数
		}
		if(gas_list == null){
			ucs.setMoneygas(0.0);//加油金额
		}else{
			ucs.setMoneygas((Double)gas_list.get(1));//加油金额
		}
		ucs.setMoneylast(balance);//油卡余额
		if(pay_list == null){
			ucs.setMoneypay(0.0);
		}else{
			ucs.setMoneypay((Double)pay_list.get(0));//充值金额
		}
		ucs.setStarttime(idStart);//起始时间
		ucs.setEndtime(idEnd);//结束时间
		return ucs;
	}
	
	/**
	 * 实现导出列表功能
	 * @param eplist 用车统计信息
	 * @return  HSSFWorkbook 结果为excel表格式
	 */
	public HSSFWorkbook export(List<UseCarSum> list){
		String[] excelHeader = {"车牌号码", "行车次数 ","行驶里程","最后公里数","行驶里程-金额", "充值金额", "加油金额","油卡余额 ","起始时间","结束时间"};
		HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("用车统计信息列表");
        
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
        	 row.createCell(0).setCellValue(list.get(i).getCarnumber());//车牌号码
        	 if(list.get(i).getUsenum() == null){
        		 row.createCell(1).setCellValue(0);//行车次数
        	 }else{
        		 row.createCell(1).setCellValue(list.get(i).getUsenum());//行车次数
        	 }
        	 if(list.get(i).getKm() == null){
        		 row.createCell(2).setCellValue(0);//行驶里程
        	 }else{
        		 row.createCell(2).setCellValue(list.get(i).getKm());//行驶里程
        	 }
        	 if(list.get(i).getLastkm() == null){
        		 row.createCell(3).setCellValue(0);//最后公里数
        	 }else{
        		 row.createCell(3).setCellValue(list.get(i).getLastkm());//最后公里数
        	 }
        	 if(list.get(i).getMoneykm() == null){
        		 row.createCell(4).setCellValue(0);//行驶里程-金额
        	 }else{
        		 row.createCell(4).setCellValue(list.get(i).getMoneykm());//行驶里程-金额
        	 }
        	 if(list.get(i).getMoneypay() == null){
        		 row.createCell(5).setCellValue(0.0);//充值金额
        	 }else{
        		 row.createCell(5).setCellValue(list.get(i).getMoneypay());//充值金额
        	 }
        	if(list.get(i).getMoneygas() == null){
        		row.createCell(6).setCellValue(0.0);//加油金额
        	}else{
        		row.createCell(6).setCellValue(list.get(i).getMoneygas());//加油金额
        	}
        	if(list.get(i).getMoneylast() == null){
        		row.createCell(7).setCellValue(0.0);//油卡余额
        	}else{
        		row.createCell(7).setCellValue(list.get(i).getMoneylast());//油卡余额
        	} 
        	row.createCell(8).setCellValue(list.get(i).getStarttime());//起始时间
        	row.createCell(9).setCellValue(list.get(i).getEndtime());//结束时间
        }
        
        return wb;
	}
}
