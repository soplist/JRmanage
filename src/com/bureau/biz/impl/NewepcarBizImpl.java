package com.bureau.biz.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.bureau.biz.NewepcarBiz;
import com.bureau.dao.DictionaryDao;
import com.bureau.dao.NewepcarDao;
import com.bureau.pojo.Dictionary;
import com.bureau.pojo.Newepcar;
import com.bureau.pojo.PageData;

public class NewepcarBizImpl implements NewepcarBiz {
	//注入Dao
	private NewepcarDao newepcarDao;
	public void setNewepcarDao(NewepcarDao newepcarDao) {
		this.newepcarDao = newepcarDao;
	}

	private static Integer total=0;
	//分页查询
	public PageData<Newepcar> findAll(Newepcar ca, String idStart, String idEnd) {
		String hql="from Newepcar p";
		String counthql="select count(p.id) from Newepcar p";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件
		if(ca.getCarnumber()!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.carnumber like :carnumber";
			counthql+=" where p.carnumber like :carnumber";
			params.put("carnumber", "%"+ca.getCarnumber()+"%");
		}else if(ca.getCarnumber()==null && idStart !=null && idEnd != null){
			hql+=" where p.newtime between :a and :b";
			counthql+=" where p.newtime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(ca.getCarnumber()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.carnumber like :carnumber and p.newtime between :a and :b";
			counthql+=" where p.carnumber like :carnumber and p.newtime between :a and :b";
			params.put("carnumber", "%"+ca.getCarnumber()+"%");
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
			System.out.println(lastdate+"555"+lastdate);
			hql+=" where p.newtime between :a and :b";
			counthql+=" where p.newtime between :a and :b";
			params.put("a", lastdate);
			params.put("b", nowdate);
		}
		
		hql+=" order by p.id desc";
		counthql+=" order by p.id desc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Newepcar> prs = newepcarDao.list(hql, params,
				(ca.getPage()-1)*ca.getRows(),ca.getRows());
		PageData<Newepcar> prr=new PageData<Newepcar>();
		//查询用户的数量
		total = newepcarDao.count(counthql, params);
		//放入pageDate
		prr.setRows(prs);
		prr.setTotal(total);
		return prr;
	}
	//查询所有
	public List<Newepcar> findAll01(Newepcar ca, String idStart, String idEnd) {
		String hql="from Newepcar p";
		String counthql="select count(p.id) from Newepcar p";
		Map<String, Object> params=new HashMap<String, Object>();
		//模糊查询追加条件
		if(ca.getCarnumber()!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.carnumber like :carnumber";
			counthql+=" where p.carnumber like :carnumber";
			params.put("carnumber", "%"+ca.getCarnumber()+"%");
		}else if(ca.getCarnumber()==null && idStart !=null && idEnd != null){
			hql+=" where p.newtime between :a and :b";
			counthql+=" where p.newtime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(ca.getCarnumber()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.carnumber like :carnumber and p.newtime between :a and :b";
			counthql+=" where p.carnumber like :carnumber and p.newtime between :a and :b";
			params.put("carnumber", "%"+ca.getCarnumber()+"%");
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
			System.out.println(lastdate+"555"+lastdate);
			hql+=" where p.newtime between :a and :b";
			counthql+=" where p.newtime between :a and :b";
			params.put("a", lastdate);
			params.put("b", nowdate);
		}
		
		hql+=" order by p.id desc";
		counthql+=" order by p.id desc";
		System.out.println("hql--"+hql);
		//查询所有用户
		List<Newepcar> prs = newepcarDao.list(hql, params);
		return prs;
	}
	public List<Newepcar> findBytime(String carnumber, String idStart, String idEnd){
		String hql="from Newepcar p";
		Map<String, Object> params=new HashMap<String, Object>();
		if(carnumber!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql+=" where p.carnumber like :carnumber";
			params.put("carnumber", "%"+carnumber+"%");
		}else if(carnumber==null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.newtime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}else if(carnumber!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where p.carnumber like :carnumber and p.newtime between :a and :b";
			params.put("carnumber", "%"+carnumber+"%");
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
			System.out.println(lastdate+nowdate);
			hql+=" where p.newtime between :a and :b";
			params.put("a", lastdate);
			params.put("b", nowdate);
		}
		List<Newepcar> car = newepcarDao.list(hql, params);
		return car;
	}
	//增加
	public boolean add(Newepcar ca) {
		return newepcarDao.add(ca);
	}
	//修改
	public boolean upd(Newepcar ca) {
		return newepcarDao.upd(ca);
	}
	//删除
	public boolean del(Newepcar ca) {
		return newepcarDao.del(ca);
	}
	//根据ID查询
	public Newepcar findById(int id) {
		return newepcarDao.get(id);
	}

	public HSSFWorkbook export(List<Newepcar> list) {
		String[] excelHeader = {"车辆类型", "所属公司","车牌号码","车牌颜色","品牌型号", "发动机号", "车架号","车辆登记日期 ","荷载人数","营运证号","驾驶员","驾驶员电话", "从业资格证","经营范围","SIM卡号","顶灯编号", "负责人", "负责人电话","安装人 ","安装日期","终端编号","行驶证资料","道路运输证资料","资格证资料", "归属企业资料", "运管所资料","安装单编号 ","所属平台","备注","记录日期"};
		HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("出车记录统计表");
        
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
        for (int i = 0; i <list.size(); i++) {  
        	 row = sheet.createRow(i + 1); 	 
        	 row.createCell(0).setCellValue(list.get(i).getStyle().getText());//车辆类型
    		 row.createCell(1).setCellValue(list.get(i).getCustomerid());//所属公司
    		 row.createCell(2).setCellValue(list.get(i).getCarnumber());//车牌号码
    		 row.createCell(3).setCellValue(list.get(i).getColor());//车牌颜色
    		 row.createCell(4).setCellValue(list.get(i).getBrandmodel());//品牌型号
    		 row.createCell(5).setCellValue(list.get(i).getEnumber());//发动机号
    		 row.createCell(6).setCellValue(list.get(i).getCnumber());//车架号
    		 row.createCell(7).setCellValue(list.get(i).getDjtime());//车辆登记日期
    		 row.createCell(8).setCellValue(list.get(i).getLnumber());//荷载人数
    		 row.createCell(9).setCellValue(list.get(i).getOpernumber());//营运证号
    		 row.createCell(10).setCellValue(list.get(i).getDriver());//驾驶员
    		 row.createCell(11).setCellValue(list.get(i).getDriverphone());//驾驶员电话
    		 row.createCell(12).setCellValue(list.get(i).getEcard());//从业资格证
    		 row.createCell(13).setCellValue(list.get(i).getManagement());//经营范围
    		 row.createCell(14).setCellValue(list.get(i).getSnumber());//SIM卡号
    		 row.createCell(15).setCellValue(list.get(i).getDnumber());//顶灯编号
    		 row.createCell(16).setCellValue(list.get(i).getHeadman());//负责人
    		 row.createCell(17).setCellValue(list.get(i).getHphone());//负责人电话
    		 row.createCell(18).setCellValue(list.get(i).getPeople());//安装人
    		 row.createCell(19).setCellValue(list.get(i).getNewtime());//安装日期
    		 row.createCell(20).setCellValue(list.get(i).getZhnumber());//终端编号
    		 row.createCell(21).setCellValue(list.get(i).getTravelcard());//行驶证
    		 row.createCell(22).setCellValue(list.get(i).getTranscard());//道路运输证
    		 row.createCell(23).setCellValue(list.get(i).getQcard());//资格证
    		 row.createCell(24).setCellValue(list.get(i).getAsccenter());//归属企业
    		 row.createCell(25).setCellValue(list.get(i).getTransport());//运管所
    		 row.createCell(26).setCellValue(list.get(i).getNewnumber());//安装单编号
    		 row.createCell(27).setCellValue(list.get(i).getPlatform());//所属平台
    		 row.createCell(28).setCellValue(list.get(i).getRemark());//备注
    		 row.createCell(29).setCellValue(list.get(i).getCreatetime());//记录时间
        }
		return wb;
		
	}

	//注入 字典 dcyDao
	private DictionaryDao dicDao;
	public void setDicDao(DictionaryDao dicDao) {
		this.dicDao = dicDao;
	}
	/**
	 * 查询所有的车辆类型
	 * @return
	 */
	public List<Dictionary> findCarstyleList() {
		return findDictionaryByName("车辆类型");
	}
	public List<Dictionary> findDictionaryByName(String text){
		String hql="from Dictionary d1 where d1.parent.id=(select d2.id from Dictionary d2 where d2.text=:text) " +
				" and d1.isdisable=:isdisable ";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("text", text);
		params.put("isdisable", 1);
		return dicDao.list(hql, params);
	}
	
	
	//按车辆类型统计(新装)车辆数量      by-李金2018-8-21
	public Integer findAllSum(Integer carstyle,String idStart, String idEnd){
		String counthql="select count(n.id) from Newepcar n where n.style.id=:carstyle and n.newtime between :a and :b order by n.id desc";		
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("carstyle", carstyle);
		params.put("a", idStart);
		params.put("b", idEnd);
		//查询记录条数
		Integer total = newepcarDao.count(counthql, params);
		return total;
	}
}