package com.bureau.biz;

import com.bureau.pojo.PageData;
import com.bureau.pojo.Softflag;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-9 下午4:26:33 
 * 类说明 
 */
public interface SoftflagBiz {
	public PageData<Softflag> findAll(Softflag sf);
	public boolean add(Softflag s);
	public Softflag findById(Integer id);
	public boolean del(Softflag sf);
}
