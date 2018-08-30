package com.bureau.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;

import com.bureau.biz.OldepcarBiz;
import com.bureau.dao.OldepcarDao;
import com.bureau.pojo.Changeequipment;
import com.bureau.pojo.ChangeequipmentVo;
import com.bureau.pojo.Customer;
import com.bureau.pojo.Dictionary;
import com.bureau.pojo.Oldepcar;
import com.bureau.pojo.OldepcarVo;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Personfile;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-18 上午9:24:31 
 * 类说明 
 */
public class OldepcarBizImpl implements OldepcarBiz {
private OldepcarDao oDao;


public void setoDao(OldepcarDao oDao) {
	this.oDao = oDao;
}

public PageData<OldepcarVo> findAll(String idStart, String idEnd,Oldepcar o) {
	String hql="from Oldepcar o ";
	String counthql="select count(o.id) from Oldepcar o";
	Map<String, Object> params=new HashMap<String, Object>();
	
	if(o.getCarnumber()!=null ||!isEmptyOrNull(idStart) || !isEmptyOrNull(idEnd)){
		if(o.getCarnumber()!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql += " where o.carnumber like :carnumber";
			counthql += " where o.carnumber like :carnumber";
			params.put("carnumber", "%"+o.getCarnumber()+"%");
		}
		else if(o.getCarnumber()==null && idStart !=null && idEnd != null){
			hql+=" where o.createtime between :a and :b";
			counthql += " where o.createtime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}
		else if(o.getCarnumber()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where o.carnumber like :carnumber and o.createtime between :a and :b";
			counthql += " where o.carnumber like :carnumber and o.createtime between :a and :b";
			params.put("carnumber", "%"+o.getCarnumber()+"%");
			params.put("a", idStart);
			params.put("b", idEnd);
		}
	}
	hql+="  order by o.id desc";
	
	List<Oldepcar> os = oDao.list(hql, params,
			(o.getPage()-1)*o.getRows(),o.getRows());
	PageData<OldepcarVo> pd = new PageData<OldepcarVo>();
	Integer total = oDao.count(counthql, params);
	pd.setRows(addEmptyPeople(os));
	pd.setTotal(total);
	return pd;
}

public List<OldepcarVo> findBytime(String idStart, String idEnd,Oldepcar o) {
	String hql="from Oldepcar o";
	Map<String, Object> params=new HashMap<String, Object>();
	if(o.getCarnumber()!=null && idStart.isEmpty() && idEnd.isEmpty()){
		hql += " where o.carnumber like :carnumber";
		params.put("carnumber", "%"+o.getCarnumber()+"%");
	}
	else if(o.getCarnumber()==null && idStart !=null && idEnd != null){
		hql+=" where o.createtime between :a and :b";
		params.put("a", idStart);
		params.put("b", idEnd);
	}
	else if(o.getCarnumber()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
		hql+=" where o.carnumber like :carnumber and o.createtime between :a and :b";
		params.put("carnumber", "%"+o.getCarnumber()+"%");
		params.put("a", idStart);
		params.put("b", idEnd);
	}
	
	List<Oldepcar> ocs = oDao.list(hql, params);
	List<OldepcarVo> ocvs = addEmptyPeople(ocs);
	return ocvs;
}

private boolean isEmptyOrNull(String value){
	if(value==null){
		return true;
	}
	else{
		if(value.isEmpty()){
			return true;
		}
		else{
		    return false;
		}
	}
}

public List<OldepcarVo> addEmptyPeople(List<Oldepcar> os){
	List<OldepcarVo> volist = new ArrayList<OldepcarVo>();
	for(Oldepcar o : os){
		OldepcarVo vo = new OldepcarVo();
		
		vo.setId(o.getId());
		vo.setStyle(o.getStyle());
		vo.setCustomer(o.getCustomer());
		vo.setCarnumber(o.getCarnumber());
		vo.setCnumber(o.getCnumber());
		vo.setSnumber(o.getSnumber());
		vo.setEpdate(o.getEpdate());
		vo.setTransnumber(o.getTransnumber());
		vo.setReason(o.getReason());
		vo.setSource(o.getSource());
		vo.setRemark1(o.getRemark1());
		vo.setRemark2(o.getRemark2());
		vo.setSystem(o.getSystem());
		vo.setRemark(o.getRemark());
		vo.setCreatetime(o.getCreatetime());
		volist.add(vo);
	}
	for(OldepcarVo ov : volist){
		if(ov.getStyle()==null){
			ov.setStyle(new Dictionary());
		}
		//ccv.setCustomer(new Customer());
	}
	return volist;
}

     //delete
	public boolean del(Oldepcar o) {
		return oDao.del(o);
	}
	
	public Oldepcar findById(Integer id) {
		return oDao.get(id);
	}
	
	public boolean add(Oldepcar o) {
		return oDao.add(o);
	}
	
	//update
	public boolean update(Oldepcar o) {
		String hql="update Oldepcar o set o.style.id=:styleid,o.customer=:customer,o.carnumber=:carnumber,o.cnumber=:cnumber,o.snumber=:snumber,o.epdate=:epdate,o.transnumber=:transnumber,o.reason=:reason,o.source=:source,o.remark1=:remark1,o.remark2=:remark2,o.system=:system,o.remark=:remark where o.id=:id";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("id",o.getId());
		params.put("styleid",o.getStyle().getId());
		params.put("customer",o.getCustomer());
		params.put("carnumber",o.getCarnumber());
		params.put("cnumber",o.getCnumber());
		params.put("snumber",o.getSnumber());
		params.put("epdate",o.getEpdate());
		params.put("transnumber",o.getTransnumber());
		params.put("reason",o.getReason());
		params.put("source",o.getSource());
		params.put("remark1",o.getRemark1());
		params.put("remark2",o.getRemark2());
		params.put("system",o.getSystem());
		params.put("remark",o.getRemark());
		if(oDao.execute(hql, params)>-1){
			return true;
		}else{
			return false;
		}
	}
	
	public HSSFWorkbook export(List<OldepcarVo> list) {
		String[] excelHeader = {"编号", "企业类型","所属公司","车牌号码","3G卡号", "SIM卡号", "企业盖章日期","运管盖章日期","报废/停运原因","来源","备注1","备注2","所属平台","备注"};
		HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("报废停运车辆记录表");
        
        HSSFRow row = sheet.createRow((int) 0);  
        row.setHeightInPoints(12F);
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        //style.setFillPattern(HSSFCellStyle.FINE_DOTS);// 设置列的样式
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        
        // 设置字体样式  
        Font titleFont = wb.createFont();  
        titleFont.setFontHeightInPoints((short)12);// 字体高度  
        titleFont.setFontName("黑体");// 字体样式  
        style.setFont(titleFont);
        //表头
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);  
            cell.setCellValue(excelHeader[i]);  
            cell.setCellStyle(style);  
            sheet.autoSizeColumn(i);// 设置宽度 
        }
        //表体内容
        fillExcelValue(sheet,row,list);
		return wb;
		
	}
	
	private void fillExcelValue(HSSFSheet sheet,HSSFRow row,List<OldepcarVo> list){
		for (int i = 0; i <list.size(); i++) {  
       	 row = sheet.createRow(i + 1); 	 
       	 row.createCell(0).setCellValue(list.get(i).getId());//编号
   		 row.createCell(1).setCellValue(list.get(i).getStyle().getText());//企业类型
   		 row.createCell(2).setCellValue(list.get(i).getCustomer());//所属公司
   		 row.createCell(3).setCellValue(list.get(i).getCarnumber());//车牌号码
   		 row.createCell(4).setCellValue(list.get(i).getCnumber());//3G卡号
   		 row.createCell(5).setCellValue(list.get(i).getSnumber());//SIM卡号
   		 row.createCell(6).setCellValue(list.get(i).getEpdate());//企业盖章日期
   		 row.createCell(7).setCellValue(list.get(i).getTransnumber());//运管盖章日期
   		 row.createCell(8).setCellValue(list.get(i).getReason());//报废/停运原因
   		 row.createCell(9).setCellValue(list.get(i).getSource());//来源
   		 row.createCell(10).setCellValue(list.get(i).getRemark1());//备注1
   		 row.createCell(11).setCellValue(list.get(i).getRemark2());//备注2
   		 row.createCell(12).setCellValue(list.get(i).getSystem());//所属平台
   		 row.createCell(13).setCellValue(list.get(i).getRemark());//备注
   	   }
	}
	
	//按车辆类型统计(报废)车辆数量      by-李金2018-8-21
	public Integer findAllSum(Integer carstyle,String idStart, String idEnd){
		String counthql="select count(o.id) from Oldepcar o where o.style.id=:carstyle and o.epdate between :a and :b order by o.id desc";		
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("carstyle", carstyle);
		params.put("a", idStart);
		params.put("b", idEnd);
		//查询记录条数
		Integer total = oDao.count(counthql, params);
		return total;
	}
}
