package com.bureau.biz;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.pojo.Changeequipment;
import com.bureau.pojo.ChangeequipmentVo;
import com.bureau.pojo.PageData;


/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-17 上午9:13:34 
 * 类说明 
 */
public interface ChangeequipmentBiz {
	public PageData<ChangeequipmentVo> findAll(String idStart, String idEnd,Changeequipment ce);
	public boolean add(Changeequipment ce);
	public boolean del(Changeequipment ce);
	public Changeequipment findById(Integer id);
	public boolean update(Changeequipment ce);
	public List<ChangeequipmentVo> findBytime(String idStart, String idEnd,Changeequipment ce);
	public HSSFWorkbook export(List<ChangeequipmentVo> list);
	//按车辆类型统计(换装)车辆数量      by-李金2018-8-20
	public Integer findAllSum(Integer carstyle,String idStart, String idEnd);
}

