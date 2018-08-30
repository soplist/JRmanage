package com.bureau.pojo;

/**
 * Goodsin entity. @author MyEclipse Persistence Tools
 */

public class Goodsin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer innum;
	private Integer renum;
	private String createtime;
	
	
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

	// Constructors

	/** default constructor */
	public Goodsin() {
	}

	/** full constructor */
	public Goodsin(String name, Integer innum, Integer renum, String createtime) {
		this.name = name;
		this.innum = innum;
		this.renum = renum;
		this.setCreatetime(createtime);
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

	public Integer getInnum() {
		return this.innum;
	}

	public void setInnum(Integer innum) {
		this.innum = innum;
	}

	public Integer getRenum() {
		return this.renum;
	}

	public void setRenum(Integer renum) {
		this.renum = renum;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCreatetime() {
		return createtime;
	}

}