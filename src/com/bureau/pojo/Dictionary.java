package com.bureau.pojo;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.bureau.pojo.Dictionary;

/**
 * Dictionary entity. @author MyEclipse Persistence Tools
 */

public class Dictionary implements java.io.Serializable {

	// Fields

	private Integer id;
	private Dictionary parent;
	private String text;
	private Integer lev;
	private Integer isdisable;
	private Set QUsers = new HashSet(0);
	private Set Customer = new HashSet(0);
	//分页属性不做映射
	private Integer page;//表示当前是第几页
	private Integer rows;//每页显示多少条记录
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	// Constructors

	/** default constructor */
	public Dictionary() {
	}

	/** full constructor */
	

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Dictionary(Integer id, Dictionary parent, String text, Integer lev,
			Integer isdisable,Set qUsers, Set customer) {
		super();
		this.id = id;
		this.parent = parent;
		this.text = text;
		this.lev = lev;
		this.isdisable = isdisable;
		QUsers = qUsers;
		Customer = customer;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Dictionary getParent() {
		return parent;
	}
	public void setParent(Dictionary parent) {
		this.parent = parent;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getLev() {
		return this.lev;
	}

	public void setLev(Integer lev) {
		this.lev = lev;
	}

	public Integer getIsdisable() {
		return this.isdisable;
	}

	public void setIsdisable(Integer isdisable) {
		this.isdisable = isdisable;
	}
	public Set getQUsers() {
		return this.QUsers;
	}

	public void setQUsers(Set QUsers) {
		this.QUsers = QUsers;
	}
	public Set getCustomer() {
		return Customer;
	}
	public void setCustomer(Set customer) {
		Customer = customer;
	}

}