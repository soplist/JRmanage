package com.bureau.action;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bureau.biz.CustomerBiz;
import com.bureau.biz.DictionaryBiz;
import com.bureau.biz.LogBiz;
import com.bureau.pojo.Customer;
import com.bureau.pojo.Dictionary;
import com.bureau.pojo.Log;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends BaseAction implements ModelDriven<Customer>{
	private Customer cu;
	private DictionaryBiz dicBiz;
	
	public void setDicBiz(DictionaryBiz dicBiz) {
		this.dicBiz = dicBiz;
	}

	public Customer getCu() {
		return cu;
	}

	public void setCu(Customer cu) {
		this.cu = cu;
	}

	public Customer getModel() {
		if(cu==null){
			cu=new Customer();
		}
		return cu;
	}
	
	//注入BIZ
	private CustomerBiz customerBiz;
	public void setCustomerBiz(CustomerBiz customerBiz) {
		this.customerBiz = customerBiz;
	}
	//注入logBiz
	private LogBiz logBiz;
	public void setLogBiz(LogBiz logBiz) {
		this.logBiz = logBiz;
	}
	
	
	//查询字典表中所有存在的客户类型
	public void finddpartList(){
		List<Dictionary> dparts=customerBiz.finddpartList();
		this.writeJSON(dparts,new String[]{"qUsers","parent","customer"});
	}
	//列表展示
	public void list(){
		PageData<Customer> tos = customerBiz.findAll(cu);	
		this.writeJSON(tos,new String[]{"customer"});
	}
	//增加
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		String nowTime = Quarter.getNowTime();
		cu.setCreatetime(nowTime);
  		boolean res = customerBiz.add(cu);
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
		cu.setCreatetime(nowTime);
		boolean res=customerBiz.upd(cu);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("修改客户信息");
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
	//删除
	public void del(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.DELFAIL);
		boolean res = customerBiz.del(cu);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		//写入日志
		if(res){
			//存入日志
			QUser us = (QUser) session.get("login_users");
			Log log = new Log();
			log.setTitle("修改客户信息");
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
		cu=customerBiz.findById(cu.getId());
		this.writeJSON(cu);
	}
	
	public void findTransportCompanys(){
		Dictionary d = dicBiz.findByText("运输企业");
		cu.setStyle(d);
		PageData<Customer> tos =customerBiz.findAllByStyle(cu);
		this.writeJSON(tos,new String[]{"style"});
	}
	//查询所有运输企业8.17吕
	public void findTomers(){
		Dictionary d = dicBiz.findByText("运输企业");
		System.out.println(d.getId());
		List<Customer> tom =customerBiz.findTomersByStyle(d.getId());
		this.writeJSON(tom,new String[]{"style"});
	}
	
}
