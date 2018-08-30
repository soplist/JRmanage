package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.PageData;
import com.bureau.pojo.Personfile;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-6 上午8:30:32 
 * 类说明 
 */
public interface PersionFileBiz {
	public boolean add(Personfile p);
	public PageData<Personfile> findAllPersonfile(Personfile u);
	public boolean update(Personfile pf);
	public List<Personfile> findAll();
	//获得合同到期人员
	public List<Personfile> findEndDate();
}
