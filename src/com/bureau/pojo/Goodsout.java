package com.bureau.pojo;

/**
 * Goodsout entity. @author MyEclipse Persistence Tools
 */

public class Goodsout implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nameid;
	private String peopleid;
	private Integer num;
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
	public Goodsout() {
	}

	/** full constructor */
	public Goodsout(String nameid, String peopleid, Integer num, String createtime) {
		this.nameid = nameid;
		this.peopleid = peopleid;
		this.num = num;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameid() {
		return this.nameid;
	}

	public void setNameid(String nameid) {
		this.nameid = nameid;
	}

	public String getPeopleid() {
		return this.peopleid;
	}

	public void setPeopleid(String peopleid) {
		this.peopleid = peopleid;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreatetime() {
		return createtime;
	}

}