package com.bureau.pojo;

/**
 * Pricarrecord entity. @author MyEclipse Persistence Tools
 */

public class Pricarrecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String pnameid;
	private String jnameid;
	private String starttime;
	private Integer startkm;
	private String endtime;
	private Integer endkm;
	private Integer km;
	private Double money;
	private String brief;
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
	public Pricarrecord() {
	}

	/** full constructor */
	public Pricarrecord(String pnameid, String jnameid, String starttime,
			Integer startkm, String endtime, Integer endkm, Integer km,
			Double money, String brief, String carid, String remark,
			String createtime) {
		this.pnameid = pnameid;
		this.jnameid = jnameid;
		this.starttime = starttime;
		this.startkm = startkm;
		this.endtime = endtime;
		this.endkm = endkm;
		this.km = km;
		this.money = money;
		this.brief = brief;
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

	public String getPnameid() {
		return this.pnameid;
	}

	public void setPnameid(String pnameid) {
		this.pnameid = pnameid;
	}

	public String getJnameid() {
		return this.jnameid;
	}

	public void setJnameid(String jnameid) {
		this.jnameid = jnameid;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public Integer getStartkm() {
		return this.startkm;
	}

	public void setStartkm(Integer startkm) {
		this.startkm = startkm;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Integer getEndkm() {
		return this.endkm;
	}

	public void setEndkm(Integer endkm) {
		this.endkm = endkm;
	}

	public Integer getKm() {
		return this.km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
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