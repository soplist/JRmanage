package com.bureau.pojo;

/**
 * Checkepcar entity. @author MyEclipse Persistence Tools
 */

public class Checkepcar implements java.io.Serializable {

	// Fields

	private Integer id;
	private String informadate;
	private String customer;
	private String carnumber;
	private String phone;
	private String errorbrief;
	private String number;
	private Personfile people;
	private String checkdate;
	private String result;
	private Personfile man;
	private String calldate;
	private Short callbrief;
	private Short isfinish;
	private String createtime;
	private Integer status;
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
	public Checkepcar() {
	}

	/** full constructor */
	public Checkepcar(String informadate, String customer, String carnumber,
			String phone, String errorbrief, String number, Personfile people,
			String checkdate, String result, Personfile man, String calldate,
			Short callbrief, Short isfinish, String createtime,Integer status) {
		this.informadate = informadate;
		this.customer = customer;
		this.carnumber = carnumber;
		this.phone = phone;
		this.errorbrief = errorbrief;
		this.number = number;
		this.people = people;
		this.checkdate = checkdate;
		this.result = result;
		this.man = man;
		this.calldate = calldate;
		this.callbrief = callbrief;
		this.isfinish = isfinish;
		this.createtime = createtime;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInformadate() {
		return this.informadate;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setInformadate(String informadate) {
		this.informadate = informadate;
	}
	public String getCarnumber() {
		return this.carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getErrorbrief() {
		return this.errorbrief;
	}

	public void setErrorbrief(String errorbrief) {
		this.errorbrief = errorbrief;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	public Personfile getPeople() {
		return people;
	}
	public void setPeople(Personfile people) {
		this.people = people;
	}
	public String getCheckdate() {
		return this.checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Personfile getMan() {
		return man;
	}
	public void setMan(Personfile man) {
		this.man = man;
	}
	

	public String getCalldate() {
		return this.calldate;
	}

	public void setCalldate(String calldate) {
		this.calldate = calldate;
	}

	public Short getCallbrief() {
		return this.callbrief;
	}

	public void setCallbrief(Short callbrief) {
		this.callbrief = callbrief;
	}

	public Short getIsfinish() {
		return this.isfinish;
	}

	public void setIsfinish(Short isfinish) {
		this.isfinish = isfinish;
	}

	public String getCreatetime() {
		return createtime;
	}
    public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
    public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}