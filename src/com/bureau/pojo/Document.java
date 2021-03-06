package com.bureau.pojo;

/**
 * Document entity. @author MyEclipse Persistence Tools
 */

public class Document implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer style;
	private String title;
	private Integer type;
	private String epname;
	private Integer textsize;
	private String docode;
	private String acceptdate;
	private String result;
	private String filename;
	private String filecode;
	private String peopleid;
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
	public Document() {
	}

	/** full constructor */
	public Document(Integer style, String title, Integer type, String epname,
			Integer textsize, String docode, String acceptdate, String result,
			String filename, String filecode, String peopleid, String createtime) {
		this.style = style;
		this.title = title;
		this.type = type;
		this.epname = epname;
		this.textsize = textsize;
		this.docode = docode;
		this.acceptdate = acceptdate;
		this.result = result;
		this.filename = filename;
		this.filecode = filecode;
		this.peopleid = peopleid;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStyle() {
		return this.style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getEpname() {
		return this.epname;
	}

	public void setEpname(String epname) {
		this.epname = epname;
	}

	public Integer getTextsize() {
		return this.textsize;
	}

	public void setTextsize(Integer textsize) {
		this.textsize = textsize;
	}

	public String getDocode() {
		return this.docode;
	}

	public void setDocode(String docode) {
		this.docode = docode;
	}

	public String getAcceptdate() {
		return this.acceptdate;
	}

	public void setAcceptdate(String acceptdate) {
		this.acceptdate = acceptdate;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilecode() {
		return this.filecode;
	}

	public void setFilecode(String filecode) {
		this.filecode = filecode;
	}

	public String getPeopleid() {
		return this.peopleid;
	}

	public void setPeopleid(String peopleid) {
		this.peopleid = peopleid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	

}