package com.bureau.pojo;

/**
 * Paycar entity. @author MyEclipse Persistence Tools
 */

public class Paycar implements java.io.Serializable {

	// Fields

	private Integer id;
	private Double money;
	private String paydate;
	private Double balance;
	private String carid;
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
	public Paycar() {
	}

	/** full constructor */
	public Paycar(Double money, String paydate, Double balance, String carid,
			String createtime) {
		this.money = money;
		this.paydate = paydate;
		this.balance = balance;
		this.carid = carid;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getPaydate() {
		return this.paydate;
	}

	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getCarid() {
		return this.carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}