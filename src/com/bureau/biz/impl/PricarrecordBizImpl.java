package com.bureau.biz.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;

import com.bureau.biz.PricarrecordBiz;
import com.bureau.dao.PricarrecordDao;
import com.bureau.pojo.Carrecord;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Pricarrecord;

public class PricarrecordBizImpl implements PricarrecordBiz {
	//注入Dao
	private PricarrecordDao pricarrecordDao;
	
	public void setPricarrecordDao(PricarrecordDao pricarrecordDao) {
		this.pricarrecordDao = pricarrecordDao;
	}
	//分页查询
	public PageData<Pricarrecord> findAll(Pricarrecord ca, String idStart, String idEnd) {
		String hql="from Pricarrecord p";
		String counthql="select count(p.id) from Pricarrecord p";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件
		if(ca.getCarid()!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.carid like :carid";
			counthql+=" where p.carid like :carid";
			params.put("carid", "%"+ca.getCarid()+"%");
		}else if(ca.getCarid()==null && idStart !=null && idEnd != null){
			hql+=" where p.starttime between :a and :b";
			counthql+=" where p.starttime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(ca.getCarid()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.carid like :carid and p.starttime between :a and :b";
			counthql+=" where p.carid like :carid and p.starttime between :a and :b";
			params.put("carid", "%"+ca.getCarid()+"%");
			params.put("a", idStart);
			params.put("b", idEnd);
		}else{//查询获取所有(两个月内)总条数,从lastdate到nowdate
			DateFormat dformat = DateFormat.getDateInstance();
	  		String nowdate = dformat.format(new Date());//当月
	  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	 		 Calendar lastDate = Calendar.getInstance();  
	 		 lastDate.set(Calendar.DATE, 1);// 设为当前月的1号  
	 		 lastDate.add(Calendar.MONTH, -2);//两月前  
	 		String lastdate = sdf.format(lastDate.getTime()); 
			System.out.println(idEnd+idStart);
			hql+=" where p.starttime between :a and :b";
			counthql+=" where p.starttime between :a and :b";
			params.put("a", lastdate);
			params.put("b", nowdate);
		}
		
		hql+=" order by p.id desc";
		counthql+=" order by p.id desc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Pricarrecord> prs = pricarrecordDao.list(hql, params,
				(ca.getPage()-1)*ca.getRows(),ca.getRows());
		PageData<Pricarrecord> prr=new PageData<Pricarrecord>();
		//查询用户的数量
		Integer total = pricarrecordDao.count(counthql, params);
		//放入pageDate
		prr.setRows(prs);
		prr.setTotal(total);
		return prr;
	}
	//查询所有
	public List<Pricarrecord> findAll01(Pricarrecord ca, String idStart, String idEnd) {
		String hql="from Pricarrecord p";
		String counthql="select count(p.id) from Pricarrecord p";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件
		if(ca.getCarid()!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.carid like :carid";
			counthql+=" where p.carid like :carid";
			params.put("carid", "%"+ca.getCarid()+"%");
		}else if(ca.getCarid()==null && idStart !=null && idEnd != null){
			hql+=" where p.starttime between :a and :b";
			counthql+=" where p.starttime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(ca.getCarid()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.carid like :carid and p.starttime between :a and :b";
			counthql+=" where p.carid like :carid and p.starttime between :a and :b";
			params.put("carid", "%"+ca.getCarid()+"%");
			params.put("a", idStart);
			params.put("b", idEnd);
		}else{//查询获取所有(两个月内)总条数,从lastdate到nowdate
			DateFormat dformat = DateFormat.getDateInstance();
	  		String nowdate = dformat.format(new Date());//当月
	  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	 		 Calendar lastDate = Calendar.getInstance();  
	 		 lastDate.set(Calendar.DATE, 1);// 设为当前月的1号  
	 		 lastDate.add(Calendar.MONTH, -2);//两月前  
	 		String lastdate = sdf.format(lastDate.getTime()); 
			System.out.println(idEnd+idStart);
			hql+=" where p.starttime between :a and :b";
			counthql+=" where p.starttime between :a and :b";
			params.put("a", lastdate);
			params.put("b", nowdate);
		}
		
		hql+=" order by p.id desc";
		counthql+=" order by p.id desc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Pricarrecord> prs = pricarrecordDao.list(hql, params);
		
		return prs;
	}
	public List<Pricarrecord> findBytime(String carid, String idStart, String idEnd){
		String hql="from Pricarrecord p";
		Map<String, Object> params=new HashMap<String, Object>();
		if(carid!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.carid like :carid";
			params.put("carid", "%"+carid+"%");
		}else if(carid==null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.starttime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(carid!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.carid like :carid and p.starttime between :a and :b";
			params.put("carid", "%"+carid+"%");
			params.put("a", idStart);
			params.put("b", idEnd);
		}else{//查询获取所有(两个月内)总条数,从lastdate到nowdate
			DateFormat dformat = DateFormat.getDateInstance();
	  		String nowdate = dformat.format(new Date());//当月
	  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	 		 Calendar lastDate = Calendar.getInstance();  
	 		 lastDate.set(Calendar.DATE, 1);// 设为当前月的1号  
	 		 lastDate.add(Calendar.MONTH, -2);//两月前  
	 		String lastdate = sdf.format(lastDate.getTime()); 
			System.out.println(idEnd+idStart);
			hql+=" where p.starttime between :a and :b";
			params.put("a", lastdate);
			params.put("b", nowdate);
		}
		List<Pricarrecord> car = pricarrecordDao.list(hql, params);
		return car;
	}
	//增加
	public boolean add(Pricarrecord ca) {
		return pricarrecordDao.add(ca);
	}
	//修改
	public boolean upd(Pricarrecord ca) {
		return pricarrecordDao.upd(ca);
	}
	//删除
	public boolean del(Pricarrecord ca) {
		return pricarrecordDao.del(ca);
	}
	//根据ID查询
	public Pricarrecord findById(int id) {
		return pricarrecordDao.get(id);
	}
	public HSSFWorkbook export(List<Pricarrecord> list) {
		String[] excelHeader = {"车牌号码", "派车人 ","驾车人","出车时间","出车公里数", "返回时间", "返回公里数","行驶公里数 ","金额","用途","备注"};
		HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("私车公用记录统计表");
        
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
        for (int i = 0; i < list.size(); i++) {  
       	 row = sheet.createRow(i + 1); 	 
       	 row.createCell(0).setCellValue(list.get(i).getCarid());//车牌号码
   		 row.createCell(1).setCellValue(list.get(i).getPnameid());//派车人
   		 row.createCell(2).setCellValue(list.get(i).getJnameid());//驾车人
   		 row.createCell(3).setCellValue(list.get(i).getStarttime());//出车时间
   		 if(list.get(i).getStartkm() == null){
       		 row.createCell(4).setCellValue(0);//行车次数
       	 }else{
       		 row.createCell(4).setCellValue(list.get(i).getStartkm());//出车公里数
       	 }
   		 row.createCell(5).setCellValue(list.get(i).getEndtime());//返回时间
   		 if(list.get(i).getEndkm() == null){
       		 row.createCell(6).setCellValue(0);//行车次数
       	 }else{
       		 row.createCell(6).setCellValue(list.get(i).getEndkm());//返回公里数
       	 }
   		 if(list.get(i).getKm() == null){
       		 row.createCell(7).setCellValue(0);//行车次数
       	 }else{
       		 row.createCell(7).setCellValue(list.get(i).getKm());//行驶公里数
       	 }
   		 if(list.get(i).getMoney() == null){
       		 row.createCell(8).setCellValue(0);//行车次数
       	 }else{
       		 row.createCell(8).setCellValue(list.get(i).getMoney());//金额
       	 }
   		 row.createCell(9).setCellValue(list.get(i).getBrief());//用途
   		 row.createCell(10).setCellValue(list.get(i).getRemark());//备注
       }
		return wb;
		
	}
		
}