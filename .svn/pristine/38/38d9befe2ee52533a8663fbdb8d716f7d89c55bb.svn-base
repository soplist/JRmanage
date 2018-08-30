package com.bureau.pojo;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */

public class Log implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private Integer isfinish;
	private String people;
	private String createtime;

	// Constructors
	//分页属性,不做映射
	private Integer page;//表示当前是第几页
	private Integer rows;//每页显示多少条记录
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPage() {
		return page;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getRows() {
		return rows;
	}
	
	

	/** default constructor */
	public Log() {
	}

	/** full constructor */
	public Log(String title, Integer isfinish, String people, String createtime) {
		this.title = title;
		this.isfinish = isfinish;
		this.people = people;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIsfinish() {
		return this.isfinish;
	}

	public void setIsfinish(Integer isfinish) {
		this.isfinish = isfinish;
	}

	public String getPeople() {
		return this.people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}