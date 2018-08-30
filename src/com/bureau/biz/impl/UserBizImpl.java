package com.bureau.biz.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bureau.biz.UserBiz;
import com.bureau.dao.DictionaryDao;
import com.bureau.dao.RoleDao;
import com.bureau.dao.UserDao;
import com.bureau.pojo.Dictionary;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QRole;
import com.bureau.pojo.QUser;
import com.bureau.unit.MD5Util;
import com.bureau.unit.SysConst;

public class UserBizImpl implements UserBiz {
		//注入操作用户表的Dao
		private UserDao userDao;
		public void setUserDao(UserDao userDao) {
			this.userDao = userDao;
		}
		//注入 字典 dcyDao
		private DictionaryDao dicDao;
		public void setDicDao(DictionaryDao dicDao) {
			this.dicDao = dicDao;
		}
		//注入 角色 Dao
		private RoleDao roleDao;
		public void setRoleDao(RoleDao roleDao) {
			this.roleDao = roleDao;
		}
		/**
		 * 通过用户名和密码查询数据6.7lv
		 * @param lname	用户名
		 * @param lpass	密码
		 * @param session 
		 * @return
		 */
		public boolean findUserByLogin(String lname, String lpass,Map<String, Object> session) {
			//创建查询语句
			System.out.println(lname);
			String hql="from QUser u where u.lname=:lname and u.lpass=:lpass";
			//创建参数对象
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("lname", lname);
			params.put("lpass", lpass);
			System.out.println(lpass);
			QUser u = userDao.get(hql, params);
			if(u!=null){
				System.out.println("biz-true-账号密码匹配");
				//登录成功将用户信息存放到session当中
				session.put("login_users", u);
				return true;
			}else{
				System.out.println("biz-false-账号密码不匹配");
				return false;
			}
		}
		
		/**
		 * 通过Lname查询用户
		 * @return
		 */
		public QUser findUserByLname(String lname){
			System.out.println(lname);
			
			String hql="from QUser u where u.lname=:lname";
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("lname", lname);
			return userDao.get(hql, params);
		}
		
		//根据用户id查找用户--by李金
		public QUser findUserById(int id){
			if(id == 0){
				return null;
			}
			String hql="from QUser u where u.id=:id";
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("id", id);
			return userDao.get(hql, params);
		}
		
		/**
		 * 查询所有的用户-findAllUser启用的-findAllUserDel禁用的6.7lv
		 * @return
		 */
		public PageData<QUser> findAllUser(QUser u) {
			String hql="from QUser u";
			String counthql="select count(u.id) from QUser u";
			Map<String, Object> params=new HashMap<String, Object>();
			//查找不包含超级管理员的所用用户(超管此行属性为null)，按自身所属行业区分查找
			//QUser uss = this.findUserByLname(u.getCompany());
			//模糊查询追加条件
			if(u.getRname()!=null){
				hql+=" where u.rname like :rname";
				counthql+=" where u.rname like :rname";
				params.put("rname", "%"+u.getRname()+"%");
			}
			hql+=" order by id asc";
			System.out.println("hql--"+hql);
			System.out.println(u.getRname());
			//查询所有用户
			List<QUser> users = userDao.list(hql, params,
					(u.getPage()-1)*u.getRows(),u.getRows());
			
			PageData<QUser> pd=new PageData<QUser>();
			
			//查询用户的数量
			Integer total = userDao.count(counthql, params);
			//放入pageDate
			pd.setRows(users);
			pd.setTotal(total);
			return pd;
		}
		
		public PageData<QUser> findAllUserDel(QUser u) {
			String hql="from QUser u where u.isdelete=:isdelete and u.account!=:account";
			String counthql="select count(u.id) from QUser u where u.isdelete=:isdelete and u.account!=:account";
			Map<String, Object> params=new HashMap<String, Object>();
			//禁用的用户isdelete状态为SysConst.DELETE
			params.put("isdelete", SysConst.DELETE);
			//设置不包含超级管理员SysConst.ADMIN
			params.put("account", SysConst.ADMIN);
			
			//查找不包含超级管理员的所用用户(超管此行属性为null)，按自身所属行业区分查找
			QUser uss = this.findUserByLname(u.getLname());
			
			
			//模糊查询追加条件
			if(u.getRname()!=null){
				hql+=" where u.rname like :rname";
				counthql+=" where u.rname like :rname";
				params.put("rname", "%"+u.getRname()+"%");
			}
			hql+=" order by id asc";
			//查询所有用户
			List<QUser> users = userDao.list(hql, params,
					(u.getPage()-1)*u.getRows(),u.getRows());
			
			for (QUser q: users) {
				System.out.println("userBiz - foreach :"+q.getLname());
			}
			PageData<QUser> pd=new PageData<QUser>();
			//查询用户的数量
			Integer total = userDao.count(counthql, params);
			
			pd.setRows(users);
			pd.setTotal(total);
			return pd;
		}
		

		//更新用户
		public boolean updUser(QUser u) {
			String hql="update QUser u set u.rname=:rname,"+
			"u.phone=:phone," +
			"u.dpart.id=:dpart," +
			"u.isdisable=:isdisable " +
			"where u.id=:id";
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("rname",u.getRname());
			params.put("phone",u.getPhone());
			params.put("dpart",u.getDpart().getId());
			params.put("phone",u.getPhone());
			params.put("isdisable",u.getIsdisable());
			params.put("id",u.getId());
			if(userDao.execute(hql, params)>-1){
				return true;
			}else{
				return false;
			}
		}
		/**
		 * 查询所有的部门信息
		 * @return
		 */
		public List<Dictionary> finddpartList() {
			return findDictionaryByName("部门名称");
		}
		
		public List<Dictionary> findDictionaryByName(String text){
			String hql="from Dictionary d1 where d1.parent.id=(select d2.id from Dictionary d2 where d2.text=:text) " +
					" and d1.isdisable=:isdisable ";
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("text", text);
			params.put("isdisable", 1);
			return dicDao.list(hql, params);
		}
		
		/**
		 * 添加用户
		 * @param u
		 * @return
		 */
		public boolean addUser(QUser u) {
			return userDao.add(u);
		}
		/**
		 * 修改密码
		 * @param id
		 * @return
		 */
		public boolean updLpass(Integer id, String password) {
			//获取当前用户
			QUser u = userDao.get(id);
			u.setLpass(MD5Util.md5Encode(password));
			return userDao.upd(u);
		}
		

		/**
		 * 删除用户
		 * @param u
		 * @return
		 */
		public boolean delUser(QUser u) {
			return userDao.del(u);
		}

		//授权
		public boolean updgrantRole(QUser u) {
			//获取对应的用户
			QUser users = userDao.get(u.getId());
			//角色授权专用
			String rids= u.getRids();
			String[] ridsarr = rids.split(",");
			Set<QRole> roles=new HashSet<QRole>();
			for (String rid : ridsarr) {
				QRole role = roleDao.get(Integer.parseInt(rid));
				roles.add(role);
			}
			users.getQRoles().clear();
			users.setQRoles(roles);
			boolean key = userDao.upd(users);
			//更改授权状态
			/*if(key){
				users.setIsGrant(1);
			}*/
			return key;
		}
		/**
		 * 还原删除的用户
		 * @param u
		 * @return
		 */
		public boolean delUserBack(QUser u) {
			String hql = "update QUser u set u.isdelete=:isdelete"+" where u.id=:id";
			Map<String, Object> maps = new HashMap<String, Object>();
			
			maps.put("isdelete", SysConst.DELETEFLAG);
			maps.put("id", u.getId());
			
			if(userDao.execute(hql, maps)>-1){
				return true;
			}
			return false;
		}
		/**
		 * 彻底删除用户
		 * @param u
		 * @return
		 */
		public boolean delUserFinal(QUser u) {
			return userDao.del(u);
		}
		
		/*
		 * 查询登录错误次数
		 * */
		public List<QUser> finduserbyname_psd(String name) {
			//创建查询语句
			String hql="from QUser u where u.lname=:lname";
			//创建参数对象
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("lname", name);
			List<QUser> u = userDao.list(hql, params);
			return u;
		}
		
		public boolean updateMissTimes(String lname,int times) {
			// TODO Auto-generated method stub
			Map<String, Object> params=new HashMap<String, Object>();
			String hql = "";
			if(lname.isEmpty()){
				params.put("isDeclare", 0);
				hql = "update QUser u set u.isDeclare=:isDeclare";
			}else{
				params.put("isDeclare", times);
				params.put("lname", lname);
				hql = "update QUser u set u.isDeclare=:isDeclare"+" where u.lname=:lname";
			}
			
			if(userDao.execute(hql, params)>-1){
				return true;
			}else{
				return false;
			}
		}
		public void updateMissTime(String lname, String date) {
			String hql = "update QUser u set u.missTime=:missTime"+" where u.lname=:lname";
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("missTime", date);
			params.put("lname", lname);
			userDao.execute(hql, params);
		}
		//更改lname
		public boolean updLname(String old_lname, String new_lname) {
			String hql = "update QUser u set u.lname=:new where u.lname=:old";
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("old", old_lname);
			maps.put("new", new_lname);
			
			if(userDao.execute(hql, maps)>-1){
				return true;
			}else{
				//System.out.println("更改失败");
			}
			return false;
		}
}