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

import com.bureau.biz.ChangeCarSumBiz;
import com.bureau.biz.ChangeequipmentBiz;
import com.bureau.biz.DictionaryBiz;
import com.bureau.biz.NewepcarBiz;
import com.bureau.biz.OldepcarBiz;
import com.bureau.pojo.ChangeCarSum;
import com.bureau.pojo.Dictionary;
import com.opensymphony.xwork2.ModelDriven;

public class ChangeCarSumAction extends BaseAction implements ModelDriven<ChangeCarSum>{
	//注入Biz
	private ChangeCarSumBiz ccsBiz;
	public void setCcsBiz(ChangeCarSumBiz ccsBiz) {
		this.ccsBiz = ccsBiz;
	}
	private DictionaryBiz dicBiz;
	public void setDicBiz(DictionaryBiz dicBiz) {
		this.dicBiz = dicBiz;
	}
	private ChangeequipmentBiz ceBiz;
	public void setCeBiz(ChangeequipmentBiz ceBiz) {
		this.ceBiz = ceBiz;
	}
	private NewepcarBiz newepcarBiz;
	public void setNewepcarBiz(NewepcarBiz newepcarBiz) {
		this.newepcarBiz = newepcarBiz;
	}
	private OldepcarBiz oBiz;	
    public void setoBiz(OldepcarBiz oBiz) {
		this.oBiz = oBiz;
	}

	//实体类
	private ChangeCarSum ccs;
	public void setCcs(ChangeCarSum ccs) {
		this.ccs = ccs;
	}
	public ChangeCarSum getCcs() {
		return ccs;
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
	
	
	private static List<ChangeCarSum> pd = null;
	private static List<ChangeCarSum> pd01 = null;
	
	
	//统计展示列表,根据时间段查询统计，初始默认统计当月信息
	public void findAllSum(){
		pd = new ArrayList<ChangeCarSum>();
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
		
 		List<Dictionary> style=dicBiz.findDictionaryByName("车辆类型");
 		//从字典表获得几个车辆类型
		for(int i=0; i<style.size();i++){
			int carstyle = style.get(i).getId();
			String carstylename = style.get(i).getText();
			ChangeCarSum ccsum = new ChangeCarSum();
			int newsum = newepcarBiz.findAllSum(carstyle, idStart, idEnd);//1获得新装车辆数量
			int updsum = ceBiz.findAllSum(carstyle, idStart, idEnd);//2获得换装车辆数量
			int oldsum = oBiz.findAllSum(carstyle, idStart, idEnd);//3获得报废车辆数量
			ccsum = ccsBiz.findSumbyCar(carstylename, newsum, updsum, oldsum, idStart, idEnd);
			pd.add(ccsum);
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
			wb = ccsBiz.export(pd);
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
	
	
	public ChangeCarSum getModel(){
		if(ccs == null)
			ccs = new ChangeCarSum();
		return ccs;
	}
}
