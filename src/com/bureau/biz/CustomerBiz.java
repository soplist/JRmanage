package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.Customer;
import com.bureau.pojo.Dictionary;
import com.bureau.pojo.PageData;


public interface CustomerBiz {
	//分页查询所有
	public PageData<Customer> findAll(Customer cu);
	//增加
	public boolean add(Customer cu);
	//修改
	public boolean upd(Customer cu);
	//删除
	public boolean del(Customer cu);
	//根据id查询
	public Customer findById(int id);
	
	public PageData<Customer> findAllByStyle(Customer cu);
	/**
	 * 查询所有的部门信息
	 * @return
	 */
	public List<Dictionary> finddpartList();
	//查询所有运输企业8.17吕
	public List<Customer> findTomersByStyle(int style);
}
