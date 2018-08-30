package com.bureau.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Publiccar entity. @author MyEclipse Persistence Tools
 */

public class Publiccar implements java.io.Serializable {

	// Fields

	private Integer id;
	private String carnumber;
	private String yearcheck;
	private String bdate;
	private Integer bsum;
	private Double output;
	private String blanknum;
	private String buytime;
	private String brand;
	private String insurance;
	private Double balance;
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
	// Constructors

	/** default constructor */
	public Publiccar() {
	}

	/** full constructor */
	public Publiccar(String carnumber, String yearcheck, String bdate,
			Integer bsum, Double output, String blanknum, String buytime,
			String brand, String insurance, Double balance, String createtime) {
		this.carnumber = carnumber;
		this.yearcheck = yearcheck;
		this.bdate = bdate;
		this.bsum = bsum;
		this.output = output;
		this.blanknum = blanknum;
		this.buytime = buytime;
		this.brand = brand;
		this.insurance = insurance;
		this.balance = balance;
		this.createtime = createtime;
		
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarnumber() {
		return this.carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getYearcheck() {
		return this.yearcheck;
	}

	public void setYearcheck(String yearcheck) {
		this.yearcheck = yearcheck;
	}

	public String getBdate() {
		return this.bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public Integer getBsum() {
		return this.bsum;
	}

	public void setBsum(Integer bsum) {
		this.bsum = bsum;
	}

	public Double getOutput() {
		return this.output;
	}

	public void setOutput(Double output) {
		this.output = output;
	}

	public String getBlanknum() {
		return this.blanknum;
	}

	public void setBlanknum(String blanknum) {
		this.blanknum = blanknum;
	}

	public String getBuytime() {
		return this.buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getInsurance() {
		return this.insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}