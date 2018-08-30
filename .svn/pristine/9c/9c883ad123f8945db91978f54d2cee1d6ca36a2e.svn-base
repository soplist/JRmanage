package com.bureau.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * QRole entity. @author MyEclipse Persistence Tools
 */

public class QRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set QUsers = new HashSet(0);
	private Set QMenus = new HashSet(0);
	//分页属性,不做映射
	private Integer page;//表示当前是第几页
	private Integer rows;//每页显示多少条记录
	//角色授权使用
	private String mids;
	public String getMids() {
		return mids;
	}
	public void setMids(String mids) {
		this.mids = mids;
	}
	// Constructors

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

	/** default constructor */
	public QRole() {
	}

	/** minimal constructor */
	public QRole(String name) {
		this.name = name;
	}

	/** full constructor */
	public QRole(String name, Set QUsers, Set QMenus) {
		this.name = name;
		this.QUsers = QUsers;
		this.QMenus = QMenus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getQUsers() {
		return this.QUsers;
	}

	public void setQUsers(Set QUsers) {
		this.QUsers = QUsers;
	}

	public Set getQMenus() {
		return this.QMenus;
	}

	public void setQMenus(Set QMenus) {
		this.QMenus = QMenus;
	}

}