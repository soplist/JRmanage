package com.bureau.biz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bureau.biz.TreeBiz;
import com.bureau.dao.MenuDao;
import com.bureau.dao.UserDao;
import com.bureau.pojo.MenuBean;
import com.bureau.pojo.QMenu;
import com.bureau.pojo.QRole;
import com.bureau.pojo.QUser;
import com.bureau.unit.SysConst;
import com.bureau.unit.SortList;


public class TreeBizImpl implements TreeBiz {

	//注入操作用户表的Dao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//注入操作菜单表的Dao
	private MenuDao menuDao;
	
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}


	/**
	 * 根据用户获取页面页面需要显示的菜单
	 * @param u	用户
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MenuBean> findMenusByUser(QUser u) {
		List<MenuBean> menubeans=new ArrayList<MenuBean>();
		if(u!=null){
			if(u.getLname().equals(SysConst.ADMIN)){
				//查询所有的菜单
				List<QMenu> menus = menuDao.list("from QMenu", null);
				//将查询到的菜单转换成页面实体
				convertMenu(menubeans, menus);
			}else{
				Set<QMenu> menus =new HashSet();
				QUser user = userDao.get(u.getId());
				Set<QRole> roles = user.getQRoles();
				for (QRole role : roles) {
					menus.addAll(role.getQMenus());
				}
				convertMenu(menubeans, menus);
			}
		}
		
		//对list菜单进行重新排序并存储于原list中   by-李金
		SortList<MenuBean> menube = new SortList<MenuBean>();
		menube.Sort(menubeans, "getId", "asc");
		return menubeans;
	}
	
	//将数据库实体转换成页面实体
	private void convertMenu(List<MenuBean> menubeans, Collection<QMenu> menus) {
		for (QMenu menu : menus) {
			MenuBean meb=new MenuBean();
			meb.setId(menu.getId());
			meb.setPid(menu.getPid());
			meb.setText(menu.getText());
			Map<String, Object> attrs=new HashMap<String, Object>();
			attrs.put("url", menu.getUrl());
			meb.setAttributes(attrs);
			menubeans.add(meb);
		}
	}
}
