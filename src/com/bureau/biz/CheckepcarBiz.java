package com.bureau.biz;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.pojo.Checkepcar;
import com.bureau.pojo.CheckepcarVo;
import com.bureau.pojo.PageData;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-13 上午10:03:39 
 * 类说明 
 */
public interface CheckepcarBiz {
	public PageData<CheckepcarVo> findAll(String idStart, String idEnd,Checkepcar cc);
	public boolean add(Checkepcar cc);
	public Checkepcar findById(Integer id);
	public boolean del(Checkepcar cc);
	public boolean maintainUpdate(Checkepcar cc);
	public boolean returnvisitUpdate(Checkepcar cc);
	public PageData<CheckepcarVo> findByCarnumber(Checkepcar cc);
	public List<CheckepcarVo> findBytime(String idStart, String idEnd,Checkepcar cc);
	public HSSFWorkbook export(List<CheckepcarVo> list);
	public List<Checkepcar> findAllIsNull();//查询所有未修车辆
}
