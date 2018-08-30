package com.bureau.pojo;

/**
 * Softflag entity. @author MyEclipse Persistence Tools
 */

public class Softflag implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String starttime;
	private String endtime;
	private String people;
	private String brief;
	private String filename;
	private String filecode;
	private String peopleid;
	private String createtime;
	
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
	public Softflag() {
	}

	/** full constructor */
	public Softflag(String name, String starttime, String endtime,
			String people, String brief, String filename, String filecode,
			String peopleid, String createtime) {
		this.name = name;
		this.starttime = starttime;
		this.endtime = endtime;
		this.people = people;
		this.brief = brief;
		this.filename = filename;
		this.filecode = filecode;
		this.peopleid = peopleid;
		this.createtime = createtime;
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

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getPeople() {
		return this.people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilecode() {
		return this.filecode;
	}

	public void setFilecode(String filecode) {
		this.filecode = filecode;
	}

	public String getPeopleid() {
		return this.peopleid;
	}

	public void setPeopleid(String peopleid) {
		this.peopleid = peopleid;
	}

	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}