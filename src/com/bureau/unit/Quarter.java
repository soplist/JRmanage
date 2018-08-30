package com.bureau.unit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Quarter {

	//根据当前日期计算应该申报的季度
	public static String getQuarter(){
		//获取当前时间格式化为【yyyy,MM】格式的日期字符
		SimpleDateFormat df = new SimpleDateFormat("yyyy,MM"); 
		String yearMonth = df.format(new Date());
		//分割字符并转化成对应的数字
		String[] yymm = new String[2];
		yymm = yearMonth.split(",");
		Integer year= Integer.parseInt(yymm[0]);
		Integer month = Integer.parseInt(yymm[1]);
		//定义每年所有季度的数组
		String[] months = {"第一季度","第二季度","第三季度","第四季度"};
		if(month>=1&&month<=3){
			//当前日期为1-3月的计算方法
			String quarter = "";
			Integer year2 = year-1;
			quarter += year2.toString()+"年";
			quarter += months[3];
			return quarter;
		}else if(month>=4&&month<=6){
			//当前日期为4-6月的计算方法
			String quarter = year.toString()+"年";
			quarter += months[0];
			return quarter;
		}else if(month>=7&&month<=9){
			//当前日期为7-9月的计算方法
			String quarter = year.toString()+"年";
			quarter += months[1];
			return quarter;
		}else {
			//当前日期为1-9月之外的月份的计算方法
			String quarter = year.toString()+"年";
			quarter += months[2];
			return quarter;
		}
	}
	
	//格式化当前时间
	public static String getNowTime(){
		//获取当前时间格式化为【yyyy,MM】格式的日期字符
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String date = df.format(new Date());
		return date;
	}
	//格式化当前时间
	public static String getNowTime01(){
		//获取当前时间格式化为【yyyy,MM】格式的日期字符
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		String date = df.format(new Date());
		return date;
	}
	public static void main(String[] args) {
		System.out.println(getQuarter());
		System.out.println(getNowTime01());
	}
	
}
