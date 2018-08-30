package com.bureau.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.biz.ChangeequipmentBiz;
import com.bureau.biz.DictionaryBiz;
import com.bureau.biz.LogBiz;
import com.bureau.pojo.Changeequipment;
import com.bureau.pojo.ChangeequipmentVo;
import com.bureau.pojo.CheckepcarVo;
import com.bureau.pojo.Dictionary;
import com.bureau.pojo.Log;
import com.bureau.pojo.Notice;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-17 上午9:21:40 
 * 类说明 
 */
public class ChangeequipmentAction extends BaseAction implements ModelDriven<Changeequipment>  {
	
	private ChangeequipmentBiz ceBiz;
	private Changeequipment ce;
	private DictionaryBiz dicBiz;
	
    private static List<ChangeequipmentVo> cevlist = null;
	
	//接收前台参数
	private String idStart;
	private String idEnd;
	public void setIdStart(String idStart) {
		this.idStart = idStart;
	}
	public String getIdStart() {
		return idStart;
	}
	public void setIdEnd(String idEnd) {
		this.idEnd = idEnd;
	}
	public String getIdEnd() {
		return idEnd;
	}

	public void setDicBiz(DictionaryBiz dicBiz) {
		this.dicBiz = dicBiz;
	}
	public Changeequipment getCe() {
		return ce;
	}
    public void setCe(Changeequipment ce) {
		this.ce = ce;
	}

	public void setCeBiz(ChangeequipmentBiz ceBiz) {
		this.ceBiz = ceBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}

	public Changeequipment getModel() {
		if (ce == null) {
			ce = new Changeequipment();
		}
		return ce;
	}
	//分页查询文件列表
	public void findAll(){
		PageData<ChangeequipmentVo> pd = new PageData<ChangeequipmentVo>();
		pd = ceBiz.findAll(idStart,idEnd,ce);
		cevlist = pd.getRows();
		this.writeJSON(pd,new String[]{"persionfile","style","parent","qUsers","privatecar"});
	}
	
	public void findBytime(){
		try{
			cevlist = ceBiz.findBytime(idStart,idEnd,ce);
		    this.writeJSON(cevlist,new String[]{"persionfile","style","privatecar"});
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void findCartypeList(){
		List<Dictionary> cartypes=dicBiz.findDictionaryByName("车辆类型");
		this.writeJSON(cartypes);
	}
	
	public void add() throws UnsupportedEncodingException{
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		boolean res = false;	
		ce.setCreatetime(Quarter.getNowTime());
		res = ceBiz.add(ce);
		if(res){
			tj.setMessage(SysConst.ADDSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	
	public void del(){
		try{
		    TempJson tj=new TempJson();
		    tj.setMessage(SysConst.DELFAIL);
		    
		    ce = ceBiz.findById(ce.getId());
		    
		    boolean res = ceBiz.del(ce);
		
		    if(res){
			    tj.setMessage(SysConst.DELSUCCESS);
			    tj.setSuccess(true);
		    }
		  //写入日志
			if(res){
				//存入日志
				QUser us = (QUser) session.get("login_users");
				Log log = new Log();
				log.setTitle("删除换装记录信息一条！");
				log.setPeople(us.getLname());
				//时间
				Date day=new Date();    
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				log.setCreatetime(df.format(day));
				log.setIsfinish(1);
				logBiz.addLog(log);	
			}
		    this.writeJSON(tj);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void update(){
		TempJson tj=new TempJson();
		boolean result = ceBiz.update(ce);
		if(result){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}else{
			tj.setMessage(SysConst.UPDFAIL);
		}
		//写入日志
		if(result){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("修改换装记录信息！");
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
	
	//实现导出列表功能
		public void exportExcel() throws IOException{
			HSSFWorkbook wb=null;
			try{
				wb = ceBiz.export(cevlist);
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
