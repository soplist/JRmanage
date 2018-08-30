package com.bureau.biz;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.pojo.Changeequipment;
import com.bureau.pojo.Oldepcar;
import com.bureau.pojo.OldepcarVo;
import com.bureau.pojo.PageData;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-18 上午9:24:07 
 * 类说明 
 */
public interface OldepcarBiz {
	public boolean del(Oldepcar o);
	public Oldepcar findById(Integer id);
	public boolean add(Oldepcar o);
	public boolean update(Oldepcar o);
	public PageData<OldepcarVo> findAll(String idStart, String idEnd,Oldepcar o);
	public List<OldepcarVo> findBytime(String idStart, String idEnd,Oldepcar o);
	public HSSFWorkbook export(List<OldepcarVo> list);
	//按车辆类型统计(报废)车辆数量      by-李金2018-8-21
	public Integer findAllSum(Integer carstyle,String idStart, String idEnd);
}
