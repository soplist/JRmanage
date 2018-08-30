package com.bureau.pojo;

/**
 * Gascar entity. @author MyEclipse Persistence Tools
 */

public class Gascar implements java.io.Serializable {

	// Fields

	private Integer id;
	private String ingas;
	private Integer ingaskm;
	private Double money;
	private Double balance;
	private Double gas;
	private String carid;
	private String remark;
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
	//分页属性,不做映射

	/** default constructor */
	public Gascar() {
	}

	/** full constructor */
	public Gascar(String ingas, Integer ingaskm, Double money, Double balance,
			Double gas, String carid, String remark, String createtime) {
		this.ingas = ingas;
		this.ingaskm = ingaskm;
		this.money = money;
		this.balance = balance;
		this.gas = gas;
		this.carid = carid;
		this.remark = remark;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIngas() {
		return this.ingas;
	}

	public void setIngas(String ingas) {
		this.ingas = ingas;
	}

	public Integer getIngaskm() {
		return this.ingaskm;
	}

	public void setIngaskm(Integer ingaskm) {
		this.ingaskm = ingaskm;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getGas() {
		return this.gas;
	}

	public void setGas(Double gas) {
		this.gas = gas;
	}

	public String getCarid() {
		return this.carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}