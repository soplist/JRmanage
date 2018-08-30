package com.bureau.dao;

import java.util.List;
import java.util.Map;


/**
 * 基础接口，实现增删改查等功能
 */
public interface BaseDao<T> {
	/**
	 * @param T 增加的实体
	 */
	public boolean add(T t);
	
	/**
	 * @param T 删除的实体
	 */
	public boolean del(T t);
	/**
	 * @param T 更新的实体
	 */
	public boolean upd(T t);
	
	/**
	 * @param id 查找条件
	 * @param T 查找并返回通过ID查找一个实体
	 */
	public T get(Integer id);
	public T get(String id);
	/**
	 * 通过hql语句查找，返回为一个实体
	 * @param 追加条件存放在map集合中
	 */
	public T get(String hql,Map<String,Object> params);
	
	/**
	 * 通过hql语句查找，返回为list集合
	 * @param hql		待查询的hql
	 * @param params	查询参数
	 */
	public List<T> list(String hql,Map<String,Object> params);
	
	
	/**
	 * 分页查询
	 * @param hql		待查询的hql
	 * @param params	查询参数
	 * @param start		数据的开始
	 * @param ls		一页显示多少条记录
	 */
	public List<T> list(String hql,Map<String, Object> params,Integer start,Integer ls);
	
	/**
	 * 查询数据库当中一共有多少条记录
	 * @param hql	待查询的hql
	 * @param params	查询参数
	 * @return count 总记录数
	 */
	public Integer count(String hql,Map<String, Object> params);
	
	/**
	 * 执行通用sql语句
	 * @param hql
	 * @return
	 */
	public Integer execute(String hql, Map<String, Object> params);
	
	
}
