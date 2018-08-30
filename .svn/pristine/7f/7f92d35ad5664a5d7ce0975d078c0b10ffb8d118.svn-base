package com.bureau.unit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.aspectj.apache.bcel.generic.Type.TypeHolder;

/** 
 * @author 作者 heyuanzhi
 * @version 创建时间：2017-12-22 上午11:35:20 
 * 类说明 
 * 检测时间差
 */
public class CheckTime {
	public static String checktime(String before_time){
		long minutes=0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());	//当前时间
		System.out.println(date);
		try{
		  Date d1 = df.parse(before_time);
		  Date d2 = df.parse(date);
		  long diff = d1.getTime() - d2.getTime();//这样得到的差值是毫秒级别
		  minutes =diff/1000/60;
		}
		catch (Exception e){
		}finally{
			if(minutes<0){
				minutes=-minutes;
			}
		}
		String minu = minutes+"";
		System.out.println(minu);
		return minu;
	}
	
/*	public static void main(String[] args) {
		String ss = "2017-12-22 14:00:28";
		checktime(ss);
	}*/
	
}
