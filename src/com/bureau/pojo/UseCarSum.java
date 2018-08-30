package com.bureau.pojo;
/*
 * 说明：此实体类并非数据库中表映射，用于统计报表使用
 * */
public class UseCarSum implements java.io.Serializable{
	
	private String carnumber;//车牌号码
	private Integer usenum;//行车次数
	private Integer km;//行驶里程
	private Integer lastkm;//最后公里数
	private Double moneykm;//行驶里程--对应金额
	private Double moneypay;//充值金额
	private Double moneygas;//加油金额
	private Double moneylast;//油卡余额
	private String starttime;//起始时间
	private String endtime;//起始时间
	
	/** default constructor */
	public UseCarSum(){
	}

	public UseCarSum(String carnumber, Integer usenum, Integer km,
			Integer lastkm, Double moneykm, Double moneypay, Double moneygas,
			Double moneylast,String starttime,String endtime) {
		super();
		this.carnumber = carnumber;
		this.usenum = usenum;
		this.km = km;
		this.lastkm = lastkm;
		this.moneykm = moneykm;
		this.moneypay = moneypay;
		this.moneygas = moneygas;
		this.moneylast = moneylast;
		this.starttime = starttime;
		this.endtime = endtime;
	}
	
	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getUsenum() {
		return usenum;
	}

	public void setUsenum(Integer usenum) {
		this.usenum = usenum;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public Integer getLastkm() {
		return lastkm;
	}

	public void setLastkm(Integer lastkm) {
		this.lastkm = lastkm;
	}

	public Double getMoneykm() {
		return moneykm;
	}

	public void setMoneykm(Double moneykm) {
		this.moneykm = moneykm;
	}

	public Double getMoneypay() {
		return moneypay;
	}

	public void setMoneypay(Double moneypay) {
		this.moneypay = moneypay;
	}

	public Double getMoneygas() {
		return moneygas;
	}

	public void setMoneygas(Double moneygas) {
		this.moneygas = moneygas;
	}

	public Double getMoneylast() {
		return moneylast;
	}

	public void setMoneylast(Double moneylast) {
		this.moneylast = moneylast;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getEndtime() {
		return endtime;
	}
}
