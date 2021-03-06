package com.bureau.action;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.biz.CarrecordBiz;
import com.bureau.biz.GascarBiz;
import com.bureau.biz.PaycarBiz;
import com.bureau.biz.PubliccarBiz;
import com.bureau.biz.UseCarSumBiz;
import com.bureau.pojo.Carrecord;
import com.bureau.pojo.Publiccar;
import com.bureau.pojo.UseCarSum;
import com.opensymphony.xwork2.ModelDriven;

public class UseCarSumAction extends BaseAction implements ModelDriven<UseCarSum>{
	//注入ucsBiz
	private UseCarSumBiz ucsBiz;	
	public void setUcsBiz(UseCarSumBiz ucsBiz) {
		this.ucsBiz = ucsBiz;
	}
	//注入carrecordBiz
	private CarrecordBiz carrecordBiz;
	public void setCarrecordBiz(CarrecordBiz carrecordBiz) {
		this.carrecordBiz = carrecordBiz;
	}
	//注入publiccarBiz
	private PubliccarBiz publiccarBiz;
	public void setPubliccarBiz(PubliccarBiz publiccarBiz) {
		this.publiccarBiz = publiccarBiz;
	}
	//注入gascarBiz
	private GascarBiz gascarBiz;
	public void setGascarBiz(GascarBiz gascarBiz) {
		this.gascarBiz = gascarBiz;
	}
	//注入paycarBiz
	private PaycarBiz paycarBiz;
	public void setPaycarBiz(PaycarBiz paycarBiz) {
		this.paycarBiz = paycarBiz;
	}
	//实体类
	private UseCarSum ucs;
	public UseCarSum getUcs() {
		return ucs;
	}
	public void setUcs(UseCarSum ucs) {
		this.ucs = ucs;
	}
	
	//接收前台参数
	private String idStart;
	public void setIdStart(String idStart) {
		this.idStart = idStart;
	}
	public String getIdStart() {
		return idStart;
	}
	private String idEnd;
	public void setIdEnd(String idEnd) {
		this.idEnd = idEnd;
	}
	public String getIdEnd() {
		return idEnd;
	}
	
	private static List<UseCarSum>  pd = null;
	private static List<UseCarSum>  pd01 = null;
	
	//统计展示列表,根据时间段查询统计，初始默认统计当月信息
	public void findAllSum(){
		pd = new ArrayList<UseCarSum>();
		if(idStart == null && idEnd == null){
			DateFormat dformat = DateFormat.getDateInstance();
			idEnd = dformat.format(new Date());//当月现在
	  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	  		Calendar lastDate = Calendar.getInstance();  
	 		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号  
	 		idStart = sdf.format(lastDate.getTime());
		}
		System.out.println("$$$idStart2="+idStart);
 		System.out.println("$$$idEnd2="+idEnd);
		
		List<Publiccar> pblc_list = publiccarBiz.findAll();//获得全部车辆信息
		//获得公司车辆列表，几台车
		for(int i=0; i<pblc_list.size();i++){
			String carnumber = pblc_list.get(i).getCarnumber();//得到车牌号码
			Double balance =  pblc_list.get(i).getBalance();//获得油卡余额
			System.out.println(carnumber);
			//调用carrecord查询车牌号为carnumber车行车统计
			List re_list = carrecordBiz.findAllSum(carnumber,idStart,idEnd);
			//调用gascar循环查询车牌号为carnumber加油统计
			List gas_list = gascarBiz.findAllSum(carnumber,idStart,idEnd);
			//调用paycar循环查询车牌号为carnumber充值统计
			List pay_list = paycarBiz.findAllSum(carnumber,idStart,idEnd);
			UseCarSum uu = new UseCarSum();
			uu = ucsBiz.findSumbyCar(carnumber,balance,re_list,gas_list,pay_list,idStart,idEnd);
			pd.add(uu);
		}
		pd01 = pd;//给echart图表数据赋值
		this.writeJSON(pd);
	}
	
	//统计展示列表,根据时间段查询统计，初始默认统计当月信息
	public void findAllSum01(){
		this.writeJSON(pd01);
	}
	
	//实现导出列表功能
	public void exportExcel() throws IOException{
		HSSFWorkbook wb=null;
		try{
			wb = ucsBiz.export(pd01);
			response.reset();//清除缓冲中的数据
			response.setContentType("application/vnd.ms-excel;charset=utf-8");  
			response.setHeader("Content-disposition", "attachment;filename=export.xls");
			response.flushBuffer();//将缓冲区中的所有数据发送到客户端
			OutputStream ouputStream = response.getOutputStream();  
			wb.write(ouputStream);  
			ouputStream.flush();  
			ouputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public UseCarSum getModel(){
		if(ucs == null)
			ucs = new UseCarSum();
		return ucs;
	}
}
