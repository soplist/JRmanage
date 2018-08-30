package com.bureau.action;

import java.util.List;

import com.bureau.biz.RoleBiz;
import com.bureau.pojo.MenuBean;
import com.bureau.pojo.QRole;
import com.bureau.pojo.TempJson;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

public class RoleAction extends BaseAction implements ModelDriven<QRole> {

	//获取页面传递参数
	private QRole role;
	public QRole getRole() {
		return role;
	}
	public void setRole(QRole role) {
		this.role = role;
	}
	//注入操作角色表的service
	private RoleBiz roleBiz;
	
	public void setroleBiz(RoleBiz roleBiz) {
		this.roleBiz = roleBiz;
	}
	//查询所有的角色
	public void findRoles(){
		this.writeJSON(roleBiz.findAllRoles(role),new String[]{"qMenus","qUsers"});
	}
	//添加角色
	public void add(){
		System.out.println("----come in roleAction----");
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		boolean res = roleBiz.addRole(role);
		if(res){
			tj.setMessage(SysConst.ADDSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	
	//修改角色
	
	public void upd(){
		
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.UPDFAIL);
		boolean res = roleBiz.updRole(role);
		if(res){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	
	//通过角色查找菜单
	public void findTreeByRole(){
		
		List<MenuBean> menuBeans=roleBiz.findTreeByRole(role);
		this.writeJSON(menuBeans);
	}
	
	//角色授权
	public void grantRole(){
		
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.GRANTFAIL);
		boolean res = roleBiz.addRoleMenu(role);
		if(res){
			tj.setMessage(SysConst.GRANTSUCCESS);
			tj.setSuccess(true);
		}
		
		this.writeJSON(tj);
	}
	
	
	
	public QRole getModel() {
		if(this.role==null){
			this.role=new QRole();
		}
		return this.role;
	}

}
