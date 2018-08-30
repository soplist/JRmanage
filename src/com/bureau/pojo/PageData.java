package com.bureau.pojo;

import java.util.List;

/**
 * 页面数据实体
 */
public class PageData<T> {
	private Integer total;
	private List<T> rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
