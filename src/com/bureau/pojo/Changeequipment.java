package com.bureau.pojo;
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-16 下午5:22:29 
 * 类说明 
 */
public class Changeequipment {
	private Integer id;
	private String customer;
	private String carnumber;
	private String color;
	private String phone;
	private String sim;
	private String terminal;
	private Personfile people;
	private String changedate;
	private Dictionary cartype;
	private String number;
	private String registerdate;
	private String endpay;
	private String remark;
	private String createtime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public Personfile getPeople() {
		return people;
	}
	public void setPeople(Personfile people) {
		this.people = people;
	}
	public String getChangedate() {
		return changedate;
	}
	public void setChangedate(String changedate) {
		this.changedate = changedate;
	}
	public Dictionary getCartype() {
		return cartype;
	}
	public void setCartype(Dictionary cartype) {
		this.cartype = cartype;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
	public String getEndpay() {
		return endpay;
	}
	public void setEndpay(String endpay) {
		this.endpay = endpay;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

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
	
	public Changeequipment() {
		super();
		
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public Changeequipment(Integer id,String customer,String carnumber,String color,
			String phone,String sim,String terminal,Personfile people,String changedate,Dictionary cartype,
			String number,String registerdate,String endpay,String remark,String createtime) {
		super();
		this.id = id;
		this.customer = customer;
		this.carnumber = carnumber;
		this.color = color;
		this.phone = phone;
		this.sim = sim;
		this.terminal = terminal;
		this.people = people;
		this.changedate = changedate;
		this.cartype = cartype;
		this.number = number;
		this.registerdate = registerdate;
		this.endpay = endpay;
		this.remark = remark;
		this.createtime = createtime;
	}
}
