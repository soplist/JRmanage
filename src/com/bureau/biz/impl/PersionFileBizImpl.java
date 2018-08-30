package com.bureau.biz.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bureau.biz.PersionFileBiz;
import com.bureau.dao.PersionFileDao;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Personfile;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-6 上午8:32:22 
 * 类说明 
 */
public class PersionFileBizImpl implements PersionFileBiz {
	
	private PersionFileDao persionfileDao;
	
	public void setPersionfileDao(PersionFileDao persionfileDao) {
		this.persionfileDao = persionfileDao;
	}

	public boolean add(Personfile p) {
		return persionfileDao.add(p);
	}
	
	//获得合同到期人员
	public List<Personfile> findEndDate(){
		String hql="from Personfile p";
		Map<String, Object> params=new HashMap<String, Object>();
		List<Personfile> personfiles = persionfileDao.list(hql, params);
		
		List<Personfile> pf = new ArrayList<Personfile>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowdate = sdf.format(new Date());
		for(int i=0; i<personfiles.size();i++){
			String enddate = personfiles.get(i).getEnddate();//得到合同到期日期
			//得到毫秒数的差，除以1000，得到秒数的差；再除以60，得到分钟数的差；再除以60，得到小时数的差；再除以24小时，得到天数的差
			try {
				if(enddate!=null&&!enddate.isEmpty()){
					long dvl = ( sdf.parse(enddate).getTime() - sdf.parse(nowdate).getTime())/1000/60/60/24;
					System.out.println("######### => " + dvl);
					if( dvl <= 40 && dvl > 0){
						pf.add(personfiles.get(i));
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return pf;
	}
	
	
	public PageData<Personfile> findAllPersonfile(Personfile pf) {
		String hql="from Personfile p where 1=1";
		String counthql="select count(p.id) from Personfile p where 1=1";
		Map<String, Object> params=new HashMap<String, Object>();
		if(pf.getName()!=null){
			hql+=" and p.name like :name";
			counthql+=" and p.name like :name";
			params.put("name", "%"+pf.getName()+"%");
		}
		if(pf.getInservice()!=null){
			hql+=" and p.inservice = :inservice";
			counthql+=" and p.inservice = :inservice";
			params.put("inservice", pf.getInservice());
		}
		hql+=" order by id desc";
		List<Personfile> personfiles = persionfileDao.list(hql, params, (pf.getPage()-1)*pf.getRows(),pf.getRows());
		PageData<Personfile> pd=new PageData<Personfile>();
		Integer total = persionfileDao.count(counthql, params);
		//放入pageDate
		pd.setRows(personfiles);
		pd.setTotal(total);
		return pd;
	}
	
	public boolean update(Personfile pf){
		String hql="update Personfile p set p.name=:name,p.sex=:sex,p.depart=:depart,p.job=:job,p.email=:email,p.startdate=:startdate,p.education=:education,p.workdate=:workdate,p.enddate=:enddate,p.phone=:phone,p.driver=:driver,p.inservice=:inservice,p.remark=:remark where p.id=:id";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("name",pf.getName());
		params.put("sex",pf.getSex());
		params.put("depart",pf.getDepart());
		params.put("job",pf.getJob());
		params.put("email",pf.getEmail());
		params.put("startdate",pf.getStartdate());
		params.put("education",pf.getEducation());
		params.put("workdate",pf.getWorkdate());
		params.put("enddate",pf.getEnddate());
		params.put("phone",pf.getPhone());
		params.put("driver",pf.getDriver());
		params.put("inservice",pf.getInservice());
		params.put("remark",pf.getRemark());
		params.put("id",pf.getId());
		if(persionfileDao.execute(hql, params)>-1){
		    return true;
		}else{
		    return false;
		}
	}

	public List<Personfile> findAll() {
		String hql="from Personfile p";
		hql+=" order by p.id asc";
		return persionfileDao.list(hql, null);
	}

	
}
