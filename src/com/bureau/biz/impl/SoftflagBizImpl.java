package com.bureau.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.SoftflagBiz;
import com.bureau.dao.SoftflagDao;
import com.bureau.pojo.Notice;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Softflag;


/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-9 下午4:27:26 
 * 类说明 
 */
public class SoftflagBizImpl implements SoftflagBiz {
    private SoftflagDao sfDao;

	
	public void setSfDao(SoftflagDao sfDao) {
		this.sfDao = sfDao;
	}
	
	//findAll
	public PageData<Softflag> findAll(Softflag sf) {
		String hql="from Softflag s ";
		String counthql="select count(s.id) from Softflag s";
		Map<String, Object> params=new HashMap<String, Object>();
		if(sf.getName()!=null){
			hql+=" where s.name like :name";
			counthql+=" where s.name like :name";
			params.put("name", "%"+sf.getName()+"%");
		}
		hql+="  order by s.id desc";
			
		List<Softflag> sfs = sfDao.list(hql, params,(sf.getPage()-1)*sf.getRows(),sf.getRows());
		PageData<Softflag> pd = new PageData<Softflag>();
		Integer total = sfDao.count(counthql, params);
		pd.setRows(sfs);
		pd.setTotal(total);
		return pd;
	}
	
	public boolean add(Softflag s) {
		return sfDao.add(s);
	}
	
	//findOne
	public Softflag findById(Integer id) {
		return sfDao.get(id);
	}
	
	public boolean del(Softflag sf) {
		return sfDao.del(sf);
	}
}
