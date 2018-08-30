package com.bureau.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.NoticeBiz;
import com.bureau.dao.NoticeDao;
import com.bureau.pojo.Notice;
import com.bureau.pojo.PageData;

public class NoticeBizImpl implements NoticeBiz {
	//注入ncDao
	private NoticeDao ncDao;
	public void setNcDao(NoticeDao ncDao) {
		this.ncDao = ncDao;
	}
	//add
	public boolean add(Notice nc) {
		return ncDao.add(nc);
	}
	//update
	public boolean upd(Notice nc) {
		return ncDao.upd(nc);
	}
	//delete
	public boolean del(Notice nc) {
		return ncDao.del(nc);
	}
	//findAll
	public PageData<Notice> findAll(Notice nc) {
		String hql="from Notice n ";
		String counthql="select count(n.id) from Notice n";
		Map<String, Object> params=new HashMap<String, Object>();
		if(nc.getTitle()!=null){
			hql+=" where n.title like :title";
			counthql+=" where n.title like :title";
			params.put("title", "%"+nc.getTitle()+"%");
		}
		hql+="  order by n.id desc";
		
		List<Notice> ncs = ncDao.list(hql, params,
				(nc.getPage()-1)*nc.getRows(),nc.getRows());
		PageData<Notice> pd = new PageData<Notice>();
		Integer total = ncDao.count(counthql, params);
		pd.setRows(ncs);
		pd.setTotal(total);
		return pd;
	}
	//查询前15条公告12-20吕文
	public List<Notice> findAll() {
		String hql="from Notice n order by n.id desc";
		List<Notice> list = ncDao.list(hql,null);
		if(list.size()>15){
			return list.subList(0, 15);
		}
		return list;
	}
	//findOne
	public Notice findById(Integer id) {
		return ncDao.get(id);
	}
	
}
