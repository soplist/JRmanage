package com.bureau.action;

/**
 * 页面跳转action
 */
public class ForwardAction extends BaseAction{
	
	//接收从页面传递过来的action
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String url(){
		this.setPath(contactPath(url));
		return "ok";
	}
}
