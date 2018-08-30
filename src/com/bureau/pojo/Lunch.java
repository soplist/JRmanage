package com.bureau.pojo;

/**
 * Lunch entity. @author MyEclipse Persistence Tools
 */

public class Lunch implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sum;
	private String remark;
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
	public Lunch() {
	}

	/** full constructor */
	public Lunch(Integer sum, String remark, String createtime) {
		this.sum = sum;
		this.remark = remark;
		this.setCreatetime(createtime);
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSum() {
		return this.sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreatetime() {
		return createtime;
	}

	
}