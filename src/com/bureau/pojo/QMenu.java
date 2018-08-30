package com.bureau.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * QMenu entity. @author MyEclipse Persistence Tools
 */

public class QMenu implements java.io.Serializable {

	// Fields

	private String id;
	private String pid;
	private String text;
	private String url;
	private Set QRoles = new HashSet(0);
	//分页属性,不做映射
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
	public QMenu() {
	}

	/** full constructor */
	public QMenu(String pid, String text, String url, Set QRoles) {
		this.pid = pid;
		this.text = text;
		this.url = url;
		this.QRoles = QRoles;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set getQRoles() {
		return this.QRoles;
	}

	public void setQRoles(Set QRoles) {
		this.QRoles = QRoles;
	}

}