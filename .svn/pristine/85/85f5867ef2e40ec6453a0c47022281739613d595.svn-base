package com.bureau.pojo;

/**
 * Memo entity. @author MyEclipse Persistence Tools
 */

public class Memo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private String peopleid;
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
	public Memo() {
	}

	/** full constructor */
	public Memo(String title, String content, String peopleid, String createtime) {
		this.title = title;
		this.content = content;
		this.peopleid = peopleid;
		this.setCreatetime(createtime);
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPeopleid() {
		return this.peopleid;
	}

	public void setPeopleid(String peopleid) {
		this.peopleid = peopleid;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreatetime() {
		return createtime;
	}
	
}