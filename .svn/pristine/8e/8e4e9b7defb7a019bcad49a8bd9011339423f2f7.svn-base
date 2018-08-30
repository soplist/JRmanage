package com.bureau.action;

import java.util.List;

import com.bureau.biz.TreeBiz;
import com.bureau.pojo.MenuBean;
import com.bureau.pojo.QUser;


public class TreeAction extends BaseAction{
	
	//注入操作页面左侧菜单的biz
	private TreeBiz treeBiz;
	
	public void setTreeBiz(TreeBiz treeBiz) {
		this.treeBiz = treeBiz;
	}


	/**
	 * 加载树的方法
	 */
	public void loadTree(){
		QUser u = (QUser) this.session.get("login_users");
		List<MenuBean> menubeans = treeBiz.findMenusByUser(u);
		this.writeJSON(menubeans);
	}
}
