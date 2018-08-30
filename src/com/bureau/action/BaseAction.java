package com.bureau.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware,RequestAware,ServletResponseAware{
	
	public Map<String, Object> session;
	public Map<String, Object> request;
	public HttpServletResponse response;
	
	//配置动态结果集
	private String path;
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}

	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
	/**
	 * 将对象转换成JSON输出到页面当中
	 * @param obj
	 */
	public void writeJSON(Object obj){
		
		String json = JSONObject.toJSONString(obj);
		try {
			System.out.println("json-"+json);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/***
	 * 响应json,并过滤不需要的属性
	 * @param object 待转换对象
	 * @param propertyNames 字符串数组结构：不想转换的属性，例：new String[]{"pro_1","pro_2"}
	 */
	public void writeJSON(Object object,String... propertyNames) {
		try {
			//过滤属性
			PropertyFilter pf= filterProperty(propertyNames);
			//套用过滤规则
			String json = JSON.toJSONString(object, pf, SerializerFeature.DisableCircularReferenceDetect);
			System.out.println("json by String of -"+json);
			this.response.setContentType("text/html;charset=utf-8");
			this.response.getWriter().write(json);
			this.response.getWriter().flush();
			this.response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 属性过滤器
	 * @param propertyNames 不需要转换的属性
	 * @return
	 */
	private PropertyFilter filterProperty(final String... propertyNames) {
		// 过滤不显示的属性及关联属性
		PropertyFilter propertyFilter = new PropertyFilter() {

			public boolean apply(Object arg0, String propertyName, Object arg2) {
				if (propertyNames != null && propertyNames.length > 0) {
					for (String pname : propertyNames) {
						if (propertyName.equals(pname)) {
							return false;
						}
					}
				}
				return true;
			}
		};

		return propertyFilter;
	}
	
	/**
	 * 拼接URL地址
	 * @param url	待拼接的URL地址
	 * @return
	 */
	public String contactPath(String url){
		return "WEB-INF/"+url;
	}
	
}
