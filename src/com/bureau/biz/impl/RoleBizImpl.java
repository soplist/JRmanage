package com.bureau.biz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bureau.biz.RoleBiz;
import com.bureau.dao.MenuDao;
import com.bureau.dao.RoleDao;
import com.bureau.pojo.MenuBean;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QMenu;
import com.bureau.pojo.QRole;
import com.bureau.unit.SysConst;


public class RoleBizImpl implements RoleBiz {

	//注入操作菜单表的Dao
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	//注入操作角色表的Dao
	private RoleDao roleDao;
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/**
	 * 查询所有的角色,并转换成页面实体
	 * @param role 
	 * @return	list
	 */

	public PageData<QRole> findAllRoles(QRole role) {
		
		
		String hql="from QRole r where 1=1 order by r.id";
		Map<String, Object> params=null;
		List<QRole> roles=null;
		if(role.getPage()!=null&&role.getRows()!=null){
			//查询所有的角色
			roles = roleDao.list(hql, params,
						(role.getPage()-1)*role.getRows(),role.getRows());
		}else{
			roles=roleDao.list(hql, params);
		}
		
		//查询角色的数量
		Integer total = roleDao.count("select count(r.id) from QRole r", null);
		PageData<QRole> pd=new PageData<QRole>();
		pd.setRows(roles);
		pd.setTotal(total);
		return pd;
	}

	/**
	 * 保存角色
	 * @param role	待保存的角色
	 * @return
	 */

	public boolean addRole(QRole role) {
		return roleDao.add(role);
	}

	
	/**
	 * 修改角色
	 * @param role	待修改的角色
	 * @return
	 */

	public boolean updRole(QRole role) {
	
		
		if(role.getId()!=null&&role.getName()!=null){
			QRole r = roleDao.get(role.getId());
			r.setName(role.getName());
			return roleDao.upd(r);
		}else{
			return false;
		}
		
		
	}

	/**
	 * 通过角色查找对应的菜单树
	 * @param role
	 * @return
	 */

	public List<MenuBean> findTreeByRole(QRole role) {
		List<MenuBean> menubeans=new ArrayList<MenuBean>();
		if(role.getId()!=null){
			//通过ID查找角色
			QRole r = roleDao.get(role.getId());
			//获取角色的所有菜单
			@SuppressWarnings("unchecked")
			Set<QMenu> usersMenus = r.getQMenus();
			convertMenu(menubeans,usersMenus);
		}
		return menubeans;
		
	}

	//将数据库实体转换成页面实体
	private void convertMenu(List<MenuBean> menubeans, Collection<QMenu> usersMenus) {
		//查询所有的菜单
		List<QMenu> menus = menuDao.list("from QMenu", null);
		
		for (QMenu menu : menus) {
			MenuBean meb=new MenuBean();
			meb.setId(menu.getId());
//				meb.setId2(menu.getId());
			meb.setPid(menu.getPid());
			meb.setText(menu.getText());
			for (QMenu um : usersMenus) {
				if(um.getId().length()==SysConst.MAXLEN&& menu.getId().equals(um.getId())){
					meb.setChecked(true);
				}
			}
			menubeans.add(meb);
		}
	}

	/**
	 * 角色授权使用：添加角色对应的菜单
	 * @param role
	 * @return
	 */

	public boolean addRoleMenu(QRole role) {
		
		try {
			//获取权限对应的字符串
			String mids = role.getMids();
			System.out.println("++++++++++++"+mids );
			String[] midsarr = mids.split(",");
			
			//取得对应的角色的实体
			QRole r=roleDao.get(role.getId());

			//删除原来的菜单
			r.getQMenus().clear();
			
			//设置新的菜单
			Set<QMenu> menus=new HashSet<QMenu>();
			for (String mid : midsarr) {
				QMenu menu = menuDao.get(mid);
				menus.add(menu);
			}
			r.setQMenus(menus);
			roleDao.upd(r);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}



}
