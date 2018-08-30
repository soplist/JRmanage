package com.bureau.biz;

import java.util.List;
import java.util.Map;

import com.bureau.pojo.Dictionary;
import com.bureau.pojo.PageData;
import com.bureau.pojo.QUser;


public interface UserBiz {
	/**
	 * 通过用户名和密码查询数据
	 * @param lname	用户名
	 * @param lpass	密码
	 * @param session 
	 * @return
	 */
	public boolean findUserByLogin(String lname,String lpass, Map<String, Object> session);
	
	/**
	 * 查询所有的用户-findAllUser启用的-findAllUserDel禁用的
	 * @param u 实体
	 * @return 数量
	 */
	public PageData<QUser> findAllUser(QUser u);
	public PageData<QUser> findAllUserDel(QUser u);
	/**
	 * 根据Lname查找用户
	 * @param lname 用户账号
	 */
	public QUser findUserByLname(String lname);

	//根据用户id查找用户
	public QUser findUserById(int id);//by李金
	/**
	 * 查询所有的部门信息
	 * @return
	 */
	public List<Dictionary> finddpartList();
	
	/**
	 * 添加用户
	 *  @param u 实体
	 * @return 成功与否
	 */
	public boolean addUser(QUser u);
	
	/**
	 * 修改密码
	 * @param id
	 * @return 成功与否
	 */
	public boolean updLpass(Integer id, String password);
	
	/**
	 * 更新用户-updUserFirst首次-updUser正常更新
	 * @param u 实体
	 * @return 成功与否
	 */
	
	/**
	 * 删除用户
	 * @param u
	 * @return
	 */
	public boolean delUser(QUser u);

	/**
	 * 彻底删除用户
	 * @param u
	 * @return
	 */
	public boolean delUserFinal(QUser u);
	//授权
	public boolean updgrantRole(QUser u);
	public boolean updUser(QUser u);
}
