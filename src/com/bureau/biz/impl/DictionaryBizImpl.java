package com.bureau.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.DictionaryBiz;
import com.bureau.dao.DictionaryDao;
import com.bureau.pojo.Dictionary;
import com.bureau.pojo.PageData;


public class DictionaryBizImpl implements DictionaryBiz {

	//注入dcyDao
	private DictionaryDao dicDao;
	public void setDicDao(DictionaryDao dicDao) {
		this.dicDao = dicDao;
	}
	//添加字典表数据
	public boolean addDictionary(Dictionary dic){
		if(dic.getParent().getId()==null||dic.getParent().getId()==-1){
			dic.setParent(null);
		}
		return dicDao.add(dic);
	}

	
	//通过父类获取字典表数据
	public List<Dictionary> findDicByParent(Dictionary dic) {
		String hql="from Dictionary d";
		Map<String, Object> params=new HashMap<String, Object>();
		if(dic!=null&&dic.getParent()!=null){
			hql+=" where d.parent.id=:pid";
			params.put("pid", dic.getParent().getId());
		}else{
			hql+=" where d.parent is null";
		}
		
		hql+=" order by d.id";
		return dicDao.list(hql, params);
	}
	

	public List<Dictionary> findDicByParentID(Integer pid) {
		String hql="from Dictionary d where d.parent.id=:pid";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("pid", pid);
		hql+=" order by d.id";
		return dicDao.list(hql, params);
	}
	
	//通过父类ID获取对应的表格数据
	public PageData<Dictionary> findDicPageDataByPid(Dictionary dic) {
		
		String hql="from Dictionary d where 1=1";
		String counthql="select count(d.id) from Dictionary d where 1=1";
		Map<String, Object> params=new HashMap<String, Object>();
		
		if(dic!=null&&dic.getParent()!=null){
			hql+=" and d.parent.id=:pid";
			counthql+=" and d.parent.id=:pid";
			params.put("pid", dic.getParent().getId());
		}
		hql+=" order by d.id";
		List<Dictionary> dics = dicDao.list(hql, params);
		
		Integer total = dicDao.count(counthql, params);
		PageData<Dictionary> pd=new PageData<Dictionary>();
		pd.setRows(dics);
		pd.setTotal(total);
		return pd;
	}

	//通过级别查找字典表数据
	public List<Dictionary> findDicByLev(Integer lev) {
		String hql="from Dictionary d where d.lev=:lev";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("lev", lev);
		return dicDao.list(hql, params);
	}

	//通过ID查找字典表数据
	public Dictionary findById(Integer id) {
		return dicDao.get(id);
	}

	public boolean updDictionary(Dictionary dic) {
		if(dic.getParent().getId()==null||dic.getParent().getId()==-1){
			dic.setParent(null);
		}
		if(dicDao.upd(dic)){
			//修改子节点
			String hql="update Dictionary d set d.isdisable=:isdisable ,d.lev=:lev where d.parent.id=:pid ";
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("isdisable", dic.getIsdisable());
			params.put("lev", dic.getLev());
			params.put("pid", dic.getId());
			dicDao.execute(hql, params);
			
			return true;
		}else{
			return false;
		}
	}

	public List<Dictionary> findDictionaryByName(String text) {
		String hql="from Dictionary d1 where d1.parent.id=(select d2.id from Dictionary d2 where d2.text=:text) " +
				"and d1.isdisable=:isdisable ";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("text", text);
		params.put("isdisable", 1);
		return dicDao.list(hql, params);
	}

	public Dictionary findByText(String text) {
		
		String hql="from Dictionary d where d.isdisable=:isdisable and d.text=:text";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("text", text);
		params.put("isdisable", 1);
		return dicDao.get(hql, params);
	}

	public Integer findCarState(String text) {
		Integer stateId=0;
		List<Dictionary> carStates = findDictionaryByName("车辆状态");
		for (Dictionary dictionary : carStates) {
			if(dictionary.getText().equals(text)){
				stateId=dictionary.getId();
				break;
			}
		}
		return stateId;
	}

}
