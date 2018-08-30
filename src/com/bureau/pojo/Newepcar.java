package com.bureau.pojo;

/**
 * Newepcar entity. @author MyEclipse Persistence Tools
 */

public class Newepcar implements java.io.Serializable {

	// Fields

	private Integer id;
	private Dictionary style;
	private String customerid;
	private String carnumber;
	private String color;
	private String brandmodel;
	private String enumber;
	private String cnumber;
	private String djtime;
	private String lnumber;
	private String opernumber;
	private String driver;
	private String driverphone;
	private String ecard;
	private String snumber;
	private String management;
	private String dnumber;
	private String zhnumber;
	private String headman;
	private String hphone;
	private String people;
	private String newtime;
	private String travelcard;
	private String transcard;
	private String qcard;
	private String asccenter;
	private String transport;
	private String newnumber;
	private String platform;
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
	public Newepcar() {
	}

	/** full constructor */
	public Newepcar(Dictionary style, String customerid, String carnumber,
			String color, String brandmodel, String enumber, String cnumber,
			String djtime, String lnumber, String opernumber, String driver,
			String driverphone, String ecard, String snumber, String management,
			String dnumber, String zhnumber, String headman, String hphone,
			String people, String newtime, String travelcard, String transcard,
			String qcard, String asccenter, String transport, String newnumber,
			String platform, String remark, String createtime) {
		this.style = style;
		this.customerid = customerid;
		this.carnumber = carnumber;
		this.color = color;
		this.brandmodel = brandmodel;
		this.enumber = enumber;
		this.cnumber = cnumber;
		this.djtime = djtime;
		this.lnumber = lnumber;
		this.opernumber = opernumber;
		this.driver = driver;
		this.driverphone = driverphone;
		this.ecard = ecard;
		this.snumber = snumber;
		this.management = management;
		this.dnumber = dnumber;
		this.zhnumber = zhnumber;
		this.headman = headman;
		this.hphone = hphone;
		this.people = people;
		this.newtime = newtime;
		this.travelcard = travelcard;
		this.transcard = transcard;
		this.qcard = qcard;
		this.asccenter = asccenter;
		this.transport = transport;
		this.newnumber = newnumber;
		this.platform = platform;
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
		return this.style;
	}

	public void setStyle(Dictionary style) {
		this.style = style;
	}

	public String getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCarnumber() {
		return this.carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrandmodel() {
		return this.brandmodel;
	}

	public void setBrandmodel(String brandmodel) {
		this.brandmodel = brandmodel;
	}

	public String getEnumber() {
		return this.enumber;
	}

	public void setEnumber(String enumber) {
		this.enumber = enumber;
	}

	public String getCnumber() {
		return this.cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	public String getDjtime() {
		return this.djtime;
	}

	public void setDjtime(String djtime) {
		this.djtime = djtime;
	}

	public String getLnumber() {
		return this.lnumber;
	}

	public void setLnumber(String lnumber) {
		this.lnumber = lnumber;
	}

	public String getOpernumber() {
		return this.opernumber;
	}

	public void setOpernumber(String opernumber) {
		this.opernumber = opernumber;
	}

	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriverphone() {
		return this.driverphone;
	}

	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}

	public String getEcard() {
		return this.ecard;
	}

	public void setEcard(String ecard) {
		this.ecard = ecard;
	}

	public String getSnumber() {
		return this.snumber;
	}

	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	public String getManagement() {
		return this.management;
	}

	public void setManagement(String management) {
		this.management = management;
	}

	public String getDnumber() {
		return this.dnumber;
	}

	public void setDnumber(String dnumber) {
		this.dnumber = dnumber;
	}

	public String getZhnumber() {
		return this.zhnumber;
	}

	public void setZhnumber(String zhnumber) {
		this.zhnumber = zhnumber;
	}

	public String getHeadman() {
		return this.headman;
	}

	public void setHeadman(String headman) {
		this.headman = headman;
	}

	public String getHphone() {
		return this.hphone;
	}

	public void setHphone(String hphone) {
		this.hphone = hphone;
	}

	public String getPeople() {
		return this.people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getNewtime() {
		return this.newtime;
	}

	public void setNewtime(String newtime) {
		this.newtime = newtime;
	}

	public String getTravelcard() {
		return this.travelcard;
	}

	public void setTravelcard(String travelcard) {
		this.travelcard = travelcard;
	}

	public String getTranscard() {
		return this.transcard;
	}

	public void setTranscard(String transcard) {
		this.transcard = transcard;
	}

	public String getQcard() {
		return this.qcard;
	}

	public void setQcard(String qcard) {
		this.qcard = qcard;
	}

	public String getAsccenter() {
		return this.asccenter;
	}

	public void setAsccenter(String asccenter) {
		this.asccenter = asccenter;
	}

	public String getTransport() {
		return this.transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getNewnumber() {
		return this.newnumber;
	}

	public void setNewnumber(String newnumber) {
		this.newnumber = newnumber;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
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