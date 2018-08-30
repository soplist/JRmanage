package com.bureau.biz.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.CustomerBiz;
import com.bureau.biz.PubliccarBiz;
import com.bureau.dao.CustomerDao;
import com.bureau.dao.DictionaryDao;
import com.bureau.dao.PubliccarDao;
import com.bureau.pojo.Customer;
import com.bureau.pojo.Dictionary;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Publiccar;

public class CustomerBizImpl implements CustomerBiz {
	//注入Dao
	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	//注入 字典 dcyDao
	private DictionaryDao dicDao;
	public void setDicDao(DictionaryDao dicDao) {
		this.dicDao = dicDao;
	}
	/**
	 * 查询所有的部门信息
	 * @return
	 */
	public List<Dictionary> finddpartList() {
		return findDictionaryByName("客户类型");
	}
	public List<Dictionary> findDictionaryByName(String text){
		String hql="from Dictionary d1 where d1.parent.id=(select d2.id from Dictionary d2 where d2.text=:text) " +
				" and d1.isdisable=:isdisable ";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("text", text);
		params.put("isdisable", 1);
		return dicDao.list(hql, params);
	}
	//分页查询
	public PageData<Customer> findAll(Customer cu) {
		String hql="from Customer p";
		String counthql="select count(p.id) from Customer p";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件
		if(cu.getName()!=null){
			hql+=" where p.name like :name";
			counthql+=" where p.name like :name";
			params.put("name", "%"+cu.getName()+"%");
		}
		hql+=" order by p.id asc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Customer> pus = customerDao.list(hql, params,
				(cu.getPage()-1)*cu.getRows(),cu.getRows());
		PageData<Customer> prr=new PageData<Customer>();
		//查询用户的数量
		Integer total = customerDao.count(counthql, params);
		//放入pageDate
		prr.setRows(pus);
		prr.setTotal(total);
		return prr;
	}
	
	public PageData<Customer> findAllByStyle(Customer cu) {
		String hql="from Customer c";
		String counthql="select count(c.id) from Customer c";
		Map<String, Object> params=new HashMap<String, Object>();
		if(cu.getStyle()!=null){
		    hql+=" where c.style.id = :style";
		    counthql+=" where c.style.id = :style";
		    params.put("style", cu.getStyle().getId());
		}
		
		hql+=" order by c.id asc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Customer> pus = customerDao.list(hql, params,
				(cu.getPage()-1)*cu.getRows(),cu.getRows());
		PageData<Customer> prr=new PageData<Customer>();
		//查询用户的数量
		Integer total = customerDao.count(counthql, params);
		//放入pageDate
		prr.setRows(pus);
		prr.setTotal(total);
		return prr;
	}
	//查询所有运输企业8.17吕
	public List<Customer> findTomersByStyle(int style) {
		String hql="from Customer c where c.style.id=:style";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("style", style);
		return customerDao.list(hql, params);
	}
	//增加
	public boolean add(Customer cu) {
		return customerDao.add(cu);
	}
	//修改
	public boolean upd(Customer cu) {
		return customerDao.upd(cu);
	}
	//删除
	public boolean del(Customer cu) {
		return customerDao.del(cu);
	}
	//根据ID查询
	public Customer findById(int id) {
		return customerDao.get(id);
	}
		
}