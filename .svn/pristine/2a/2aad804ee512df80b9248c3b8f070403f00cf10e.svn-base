package com.bureau.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.bureau.dao.BaseDao;


public class BaseDaoImpl<T> implements BaseDao<T> {

	//定义泛型T 数据类型
	private Class<T> entity;
	/**
	 * @param 通过反射获取泛型T的类型
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		@SuppressWarnings("rawtypes")
		Class c = this.getClass();
		Type t = c.getGenericSuperclass();
		if(t instanceof ParameterizedType){
			Type[] types = ((ParameterizedType)t).getActualTypeArguments();
			this.entity=(Class<T>) types[0];
		}
	}
	
	//注入	sessionFactory
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * @param T 增加的实体
	 */
	public boolean add(T t) {
		boolean flag = false;
		try {
			sessionFactory.getCurrentSession().save(t);
			flag = true;
		} catch (Exception e) {
			System.err.println("add(t)-添加数据出现异常！");
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * @param T 删除的实体
	 */
	public boolean del(T t) {
		boolean flag = false;
		try {
			sessionFactory.getCurrentSession().delete(t);
			flag = true;
		} catch (Exception e) {
			System.err.println("del(t)-删除数据出现异常！");
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * @param T 更新的实体
	 */
	public boolean upd(T t) {
		boolean flag = false;
		try {
			sessionFactory.getCurrentSession().update(t);
			flag = true;
		} catch (Exception e) {
			System.err.println("upd(t)-修改数据出现异常！");
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * @param id 查找条件
	 * @param T 查找并返回通过ID查找一个实体
	 */
	@SuppressWarnings("unchecked")
	public T get(Integer id) {
		return (T) sessionFactory.getCurrentSession().get(entity, id);
	}
	@SuppressWarnings("unchecked")
	public T get(String id) {
		return (T) sessionFactory.getCurrentSession().get(entity, id);
	}
	
	/**
	 * 通过hql语句查找，返回为一个实体
	 * @param 追加条件存放在map集合中
	 */
	@SuppressWarnings("unchecked")
	public T get(String hql, Map<String, Object> params) {
		try {
			//获取查询的hql语句
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			//追加条件
			if(params!=null&&params.size()>0){
				//遍历map，set查询条件
				for(String key : params.keySet()){
					query.setParameter(key, params.get(key));
					//System.out.println("baseDaoImpl-params:"+params.get(key));
				}
			}
			T ob = (T) query.uniqueResult();
			return ob;
		} catch (Exception e) {
			System.err.println("get t by hql+params - 查询出现异常！");
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 通过hql语句查找，返回为list集合
	 * @param hql		待查询的hql
	 * @param params	查询参数
	 */
	@SuppressWarnings("unchecked")
	public List<T> list(String hql, Map<String, Object> params) {
		//System.out.println("login list ------");
		try {
			//获取查询的hql语句
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			//追加条件
			if(params!=null&&params.size()>0){
				//遍历map，set查询条件
				for(String key : params.keySet()){
					query.setParameter(key, params.get(key));
				}
			}
			return query.list();
		} catch (Exception e) {
			System.err.println("list list<t> by hql&params - 查询出现异常！");
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	/**
	 * 分页查询
	 * @param hql		待查询的hql
	 * @param params	查询参数
	 * @param cp		数据的开始
	 * @param ls		一页显示多少条记录
	 */
	@SuppressWarnings("unchecked")
	public List<T> list(String hql, Map<String, Object> params, Integer start,
			Integer ls) {
		try {
			//获取查询的hql语句
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			//追加条件
			if(params!=null&&params.size()>0){
				//遍历map，set查询条件
				for(String key : params.keySet()){
					query.setParameter(key, params.get(key));
				}
			}
			query.setFirstResult(start);
			query.setMaxResults(ls);
			
			return query.list();
		} catch (Exception e) {
			System.err.println("list list<t> by hql&params&start&ls - 查询出现异常！");
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	/**
	 * 查询数据库当中一共有多少条记录
	 * @param hql	待查询的hql
	 * @param params	查询参数
	 * @return count 总记录数
	 */
	public Integer count(String hql, Map<String, Object> params) {
		try {
			//获取查询对象
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			//设置参数
			if(params!=null&&params.size()>0){
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			return Integer.parseInt(query.uniqueResult().toString());
		} catch (Exception e) {
			System.err.println("查询记录数count - 出现了异常！");
			e.printStackTrace();
		}
		return 0;
	}
	//通用查询方法
	public Integer execute(String hql,Map<String, Object> params) {
		int res=-1;
		try {
			System.out.println("dao - user execute hql - "+hql);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			//设置参数
			if(params!=null&&params.size()>0){
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			res=query.executeUpdate();
		} catch (Exception e) {
			System.out.println("执行通用execute - 异常");
			e.printStackTrace();
		}
		return res;
	}

	
	

}
