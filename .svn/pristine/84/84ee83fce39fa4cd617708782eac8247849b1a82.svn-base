package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.MenuBean;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QRole;


public interface RoleBiz {
	
	/**
	 * 查询所有的角色,并转换成页面实体
	 * @param role 
	 * @return	list
	 */
	public PageData<QRole> findAllRoles(QRole role);
	
	/**
	 * 保存角色
	 * @param role	待保存的角色
	 * @return
	 */
	public boolean addRole(QRole role);
	/**
	 * 修改角色
	 * @param role	待修改的角色
	 * @return
	 */
	public boolean updRole(QRole role);
	
	/**
	 * 通过角色查找对应的菜单树
	 * @param role
	 * @return
	 */
	public List<MenuBean> findTreeByRole(QRole role);
	
	/**
	 * 角色授权使用：添加角色对应的菜单
	 * @param role
	 * @return
	 */
	public boolean addRoleMenu(QRole role);

}
