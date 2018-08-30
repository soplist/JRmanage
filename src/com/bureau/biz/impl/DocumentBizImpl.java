package com.bureau.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.DocumentBiz;
import com.bureau.dao.DocumentDao;
import com.bureau.pojo.Document;
import com.bureau.pojo.Notice;
import com.bureau.pojo.PageData;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-8 下午3:16:20 
 * 类说明 
 */
public class DocumentBizImpl implements DocumentBiz {
	private DocumentDao dDao;
	public void setdDao(DocumentDao dDao) {
		this.dDao = dDao;
	}
	public PageData<Document> findAll(Document d) {
		String hql="from Document d";
		String counthql="select count(d.id) from Document d";
		Map<String, Object> params=new HashMap<String, Object>();
		if(d.getTitle()!=null){
			hql+=" where d.title like :title";
			counthql+=" where d.title like :title";
			params.put("title", "%"+d.getTitle()+"%");
		}
		hql+="  order by d.id desc";
		List<Document> ds = dDao.list(hql, params,
				(d.getPage()-1)*d.getRows(),d.getRows());
		PageData<Document> pd = new PageData<Document>();
		Integer total = dDao.count(counthql, params);
		pd.setRows(ds);
		pd.setTotal(total);
		return pd;
	}
	
	public boolean add(Document d) {
		return dDao.add(d);
	}
	
	public Document findById(Integer id) {
		return dDao.get(id);
	}
	
	//delete
	public boolean del(Document d) {
		return dDao.del(d);
	}
}
