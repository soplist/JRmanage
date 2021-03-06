package com.bureau.action;


import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.biz.CarrecordBiz;
import com.bureau.biz.LogBiz;
import com.bureau.pojo.Carrecord;
import com.bureau.pojo.Log;
import com.bureau.pojo.Lunch;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.pojo.UseCarSum;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class CarrecordAction extends BaseAction implements ModelDriven<Carrecord>{
	private Carrecord ca;
	public Carrecord getCa() {
		return ca;
	}

	public void setCa(Carrecord ca) {
		this.ca = ca;
	}

	public Carrecord getModel() {
		if(ca==null){
			ca=new Carrecord();
		}
		return ca;
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
	//注入BIZ
	private CarrecordBiz carrecordBiz;
	public void setCarrecordBiz(CarrecordBiz carrecordBiz) {
		this.carrecordBiz = carrecordBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}
	
	private static List<Carrecord>  cas = null;
	//列表展示
	public void list(){
		PageData<Carrecord>  carrecord = carrecordBiz.findAll(ca,idStart,idEnd);	
		this.writeJSON(carrecord);
	}
	public void list01(){
		cas = carrecordBiz.findAll01(ca,idStart,idEnd);	
		this.writeJSON(ca);
	}
	public void findBytime(){
		List<Carrecord> list = carrecordBiz.findBytime(ca.getCarid(),idStart, idEnd);
		System.out.println(idEnd+idStart);
		this.writeJSON(list);
	}
	//增加
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		String nowTime = Quarter.getNowTime();
		ca.setCreatetime(nowTime);
  		boolean res = carrecordBiz.add(ca);
		if(res){
			tj.setMessage(SysConst.ADDSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	//修改
	public void updata(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.UPDFAIL);
		String nowTime = Quarter.getNowTime();
		ca.setCreatetime(nowTime);
		boolean res=carrecordBiz.upd(ca);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	//删除
	public void del(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.DELFAIL);
		boolean res = carrecordBiz.del(ca);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("删除公司车辆出车登记信息");
			log.setPeople(us.getLname());
			//时间
			Date day=new Date();    
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			log.setCreatetime(df.format(day));
			log.setIsfinish(1);
			logBiz.addLog(log);	
		}
		this.writeJSON(tj);
	}
	//根据id查询
	public void findById(){
		ca=carrecordBiz.findById(ca.getId());
		this.writeJSON(ca);
	}
	//实现导出列表功能
	public void exportExcel() throws IOException{
		HSSFWorkbook wb=null;
		try{
			wb = carrecordBiz.export(cas);
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
}
