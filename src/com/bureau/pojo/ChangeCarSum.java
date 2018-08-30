package com.bureau.pojo;
/*
 * 说明：此实体类并非数据库中表映射，用于统计报表使用-（新装、换装、维修）
 * */
public class ChangeCarSum implements java.io.Serializable{
	
	private String carstyle;//车辆类型
	private Integer newsum;//新装车辆数
	private Integer updsum;//换装车辆数
	private Integer oldsum;//报废车辆数
	private String starttime;//起始日期
	private String endtime;//结束日期
	
	public ChangeCarSum(){}
	
	public ChangeCarSum(String carstyle,Integer newsum,Integer updsum,Integer oldsum,String starttime,String endtime){
		this.carstyle = carstyle;
		this.newsum = newsum;
		this.updsum = updsum;
		this.oldsum = oldsum;
		this.starttime = starttime;
		this.endtime = endtime;
	}
	
	public String getCarstyle() {
		return carstyle;
	}
	public void setCarstyle(String carstyle) {
		this.carstyle = carstyle;
	}

	public Integer getNewsum() {
		return newsum;
	}
	public void setNewsum(Integer newsum) {
		this.newsum = newsum;
	}

	public Integer getUpdsum() {
		return updsum;
	}
	public void setUpdsum(Integer updsum) {
		this.updsum = updsum;
	}

	public Integer getOldsum() {
		return oldsum;
	}
	public void setOldsum(Integer oldsum) {
		this.oldsum = oldsum;
	}
	
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
}
