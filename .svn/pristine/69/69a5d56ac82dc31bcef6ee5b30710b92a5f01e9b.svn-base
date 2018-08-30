package com.bureau.action;

import java.util.List;

import com.bureau.biz.RepairBiz;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Repair;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class RepairAction extends BaseAction implements ModelDriven<Repair>{
	//注入操作Biz
	private RepairBiz repairBiz;
	public void setRepairBiz(RepairBiz repairBiz) {
		this.repairBiz = repairBiz;
	}

	//实体类
	private Repair repair;
	public Repair getRepair() {
		return repair;
	}
	public void setRepair(Repair repair) {
		this.repair = repair;
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
	
	//查询所有
	public void findAll(){
		PageData<Repair> pd = repairBiz.findAll(repair,idStart,idEnd);
		this.writeJSON(pd);
	}
	//根据时间段查询
	public void findBytime(){
		List<Repair> g = repairBiz.findBytime(repair.getCarnumber(),idStart,idEnd);
		this.writeJSON(g);
	}
	
	//添加
	public void add(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		boolean res = repairBiz.add(repair);
		if(res){
			tj.setMessage(SysConst.ADDSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	
	//修改
	public void upd(){
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.UPDFAIL);
		boolean res = repairBiz.upd(repair);
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
		boolean res = repairBiz.del(repair);
		if(res){
			tj.setMessage(SysConst.DELSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	
	//ModelDriven
	public Repair getModel(){
		if(repair == null)
			repair = new Repair();
		return repair;
	}
}
