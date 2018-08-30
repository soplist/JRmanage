package com.bureau.pojo;

/**
 * Oldepcar entity. @author MyEclipse Persistence Tools
 */

public class Oldepcar implements java.io.Serializable {

	// Fields

	private Integer id;
	private Dictionary style;
	private String customer;
	private String carnumber;
	private String cnumber;
	private String snumber;
	private String epdate;
	private String transnumber;
	private String reason;
	private String source;
	private String remark1;
	private String remark2;
	private String system;
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

	// Constructors

	/** default constructor */
	public Oldepcar() {
	}

	/** full constructor */
	public Oldepcar(Dictionary style, String customer, String carnumber,
			String cnumber, String snumber, String epdate, String transnumber,
			String reason, String source, String remark1, String remark2,
			String system, String remark, String createtime) {
		this.style = style;
		this.customer = customer;
		this.carnumber = carnumber;
		this.cnumber = cnumber;
		this.snumber = snumber;
		this.epdate = epdate;
		this.transnumber = transnumber;
		this.reason = reason;
		this.source = source;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.system = system;
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
	public Dictionary getStyle() {
		return style;
	}
    public void setStyle(Dictionary style) {
		this.style = style;
	}
	
	public String getCustomer() {
		return customer;
	}
    public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCarnumber() {
		return this.carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getCnumber() {
		return this.cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	public String getSnumber() {
		return this.snumber;
	}

	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	public String getEpdate() {
		return this.epdate;
	}

	public void setEpdate(String epdate) {
		this.epdate = epdate;
	}

	public String getTransnumber() {
		return this.transnumber;
	}

	public void setTransnumber(String transnumber) {
		this.transnumber = transnumber;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRemark1() {
		return this.remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getSystem() {
		return this.system;
	}

	public void setSystem(String system) {
		this.system = system;
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