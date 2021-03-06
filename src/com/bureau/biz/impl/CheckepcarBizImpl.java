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

import com.bureau.biz.CheckepcarBiz;
import com.bureau.dao.CheckepcarDao;
import com.bureau.pojo.Checkepcar;
import com.bureau.pojo.CheckepcarVo;
import com.bureau.pojo.Customer;
import com.bureau.pojo.Newepcar;
import com.bureau.pojo.Notice;
import com.bureau.pojo.PageData;
import com.bureau.pojo.Personfile;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-13 上午10:04:07 
 * 类说明 
 */
public class CheckepcarBizImpl implements CheckepcarBiz {
	CheckepcarDao ccDao;

	public void setCcDao(CheckepcarDao ccDao) {
		this.ccDao = ccDao;
	}
	
	//findAll
	public PageData<CheckepcarVo> findAll(String idStart, String idEnd,Checkepcar cc) {
		String hql="from Checkepcar c ";
		String counthql="select count(c.id) from Checkepcar c";
		Map<String, Object> params=new HashMap<String, Object>();
		/*if(nc.getTitle()!=null){
			hql+=" where n.title like :title";
			counthql+=" where n.title like :title";
			params.put("title", "%"+nc.getTitle()+"%");
		}*/
		if(cc.getCarnumber()!=null ||!isEmptyOrNull(idStart) || !isEmptyOrNull(idEnd)){
			if(cc.getCarnumber()!=null && idStart.isEmpty() && idEnd.isEmpty()){
				hql += " where c.carnumber like :carnumber";
				counthql += " where c.carnumber like :carnumber";
				params.put("carnumber", "%"+cc.getCarnumber()+"%");
			}
			else if(cc.getCarnumber()==null && idStart !=null && idEnd != null){
				hql+=" where c.createtime between :a and :b";
				counthql += " where c.createtime between :a and :b";
				params.put("a", idStart);
				params.put("b", idEnd);
			}
			else if(cc.getCarnumber()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
				hql+=" where c.carnumber like :carnumber and c.createtime between :a and :b";
				counthql += " where c.carnumber like :carnumber and c.createtime between :a and :b";
				params.put("carnumber", "%"+cc.getCarnumber()+"%");
				params.put("a", idStart);
				params.put("b", idEnd);
			}
		}
		hql+="  order by c.id desc";
		List<Checkepcar> ccs = ccDao.list(hql, params,
		(cc.getPage()-1)*cc.getRows(),cc.getRows());
		PageData<CheckepcarVo> pd = new PageData<CheckepcarVo>();
		Integer total = ccDao.count(counthql, params);
		pd.setRows(addEmptyPeople(ccs));
		pd.setTotal(total);
		return pd;
	}
	//查询未修车辆
	public List<Checkepcar> findAllIsNull() {
		String hql="from Checkepcar c where checkdate = null order by c.id desc";
		List<Checkepcar> ccs = ccDao.list(hql, null);
		return ccs;
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
	
	public List<CheckepcarVo> findBytime(String idStart, String idEnd,Checkepcar cc) {
		String hql="from Checkepcar c";
		Map<String, Object> params=new HashMap<String, Object>();
		if(cc.getCarnumber()!=null && idStart.isEmpty() && idEnd.isEmpty()){
			hql += " where c.carnumber like :carnumber";
			params.put("carnumber", "%"+cc.getCarnumber()+"%");
		}
		else if(cc.getCarnumber()==null && idStart !=null && idEnd != null){
			hql+=" where c.createtime between :a and :b";
			params.put("a", idStart);
			params.put("b", idEnd);
		}
		else if(cc.getCarnumber()!=null && !idStart.isEmpty() && !idEnd.isEmpty()){
			hql+=" where c.carnumber like :carnumber and c.createtime between :a and :b";
			params.put("carnumber", "%"+cc.getCarnumber()+"%");
			params.put("a", idStart);
			params.put("b", idEnd);
		}
		
		List<Checkepcar> ccs = ccDao.list(hql, params);
		List<CheckepcarVo> ccvs = addEmptyPeople(ccs);
		return ccvs;
	}
	
	//按车牌号查询
	public PageData<CheckepcarVo> findByCarnumber(Checkepcar cc) {
		String hql="from Checkepcar c ";
		String counthql="select count(c.id) from Checkepcar c";
		Map<String, Object> params=new HashMap<String, Object>();
		if(cc.getCarnumber()!=null){
			hql+=" where c.carnumber like :carnumber";
			counthql+=" where c.carnumber like :carnumber";
			params.put("carnumber", "%"+cc.getCarnumber()+"%");
		}
		hql+="  order by c.id desc";
			
		List<Checkepcar> ccs = ccDao.list(hql, params,
		(cc.getPage()-1)*cc.getRows(),cc.getRows());
		PageData<CheckepcarVo> pd = new PageData<CheckepcarVo>();
		Integer total = ccDao.count(counthql, params);
		pd.setRows(addEmptyPeople(ccs));
		pd.setTotal(total);
		return pd;
	}
	
	public List<CheckepcarVo> addEmptyPeople(List<Checkepcar> ccs){
		List<CheckepcarVo> volist = new ArrayList<CheckepcarVo>();
		for(Checkepcar cc : ccs){
			CheckepcarVo vo = new CheckepcarVo();
			
			vo.setId(cc.getId());
			vo.setInformadate(cc.getInformadate());
			vo.setCustomer(cc.getCustomer());
			vo.setCarnumber(cc.getCarnumber());
			vo.setPhone(cc.getPhone());
			vo.setErrorbrief(cc.getErrorbrief());
			vo.setNumber(cc.getNumber());
			vo.setPeople(cc.getPeople());
			vo.setCheckdate(cc.getCheckdate());
			vo.setResult(cc.getResult());
			vo.setMan(cc.getMan());
			vo.setCalldate(cc.getCalldate());
			vo.setCallbrief(cc.getCallbrief());
			vo.setIsfinish(cc.getIsfinish());
			vo.setCreatetime(cc.getCreatetime());
			vo.setStatus(cc.getStatus());
			
			volist.add(vo);
		}
		for(CheckepcarVo ccv : volist){
			if(ccv.getMan()==null){
				ccv.setMan(new Personfile());
			}
			if(ccv.getPeople()==null){
				ccv.setPeople(new Personfile());
			}
			//ccv.setCustomer(new Customer());
		}
		return volist;
	}
	//add
	public boolean add(Checkepcar cc) {
		return ccDao.add(cc);
	}
	
	public Checkepcar findById(Integer id) {
		return ccDao.get(id);
	}
	
	//delete
	public boolean del(Checkepcar cc) {
		return ccDao.del(cc);
	}
	
	public boolean maintainUpdate(Checkepcar cc){
		String hql="update Checkepcar c set c.people.id=:peopleid,c.checkdate=:checkdate,c.result=:result,c.status=2 where c.id=:id";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("peopleid",cc.getPeople().getId());
		params.put("checkdate",cc.getCheckdate());
		params.put("result",cc.getResult());
		params.put("id",cc.getId());
		if(ccDao.execute(hql, params)>-1){
		    return true;
		}else{
		    return false;
		}
	}
	
	public boolean returnvisitUpdate(Checkepcar cc){
		String hql="update Checkepcar c set c.man.id=:manid,c.calldate=:calldate,c.callbrief=:callbrief,c.status=3 where c.id=:id";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("manid",cc.getMan().getId());
		params.put("calldate",cc.getCalldate());
		params.put("callbrief",cc.getCallbrief());
		params.put("id",cc.getId());
		if(ccDao.execute(hql, params)>-1){
		    return true;
		}else{
		    return false;
		}
	}
	
	public HSSFWorkbook export(List<CheckepcarVo> list) {
		String[] excelHeader = {"报修日期", "所属公司","车牌号码","联系电话","故障描述", "编号", "维修工程师","维修日期 ","处理状况结果","回访人员","回访日期","回访情况"};
		HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("车辆维修记录表");
        
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
	
	private void fillExcelValue(HSSFSheet sheet,HSSFRow row,List<CheckepcarVo> list){
		for (int i = 0; i <list.size(); i++) {  
       	 row = sheet.createRow(i + 1); 	 
       	 row.createCell(0).setCellValue(list.get(i).getInformadate());//报修日期
   		 row.createCell(1).setCellValue(list.get(i).getCustomer());//所属公司
   		 row.createCell(2).setCellValue(list.get(i).getCarnumber());//车牌号码
   		 row.createCell(3).setCellValue(list.get(i).getPhone());//联系电话
   		 row.createCell(4).setCellValue(list.get(i).getErrorbrief());//故障描述
   		 row.createCell(5).setCellValue(list.get(i).getNumber());//编号
   		 row.createCell(6).setCellValue(list.get(i).getPeople().getName());//维修工程师
   		 row.createCell(7).setCellValue(list.get(i).getCheckdate());//维修日期
   		 row.createCell(8).setCellValue(list.get(i).getResult());//处理状况结果
   		 row.createCell(9).setCellValue(list.get(i).getMan().getName());//回访人员
   		 row.createCell(10).setCellValue(list.get(i).getCalldate());//回访日期
   		 
   		 Short cb = list.get(i).getCallbrief();
   		 if(cb!=null){
   			 if(cb==1){
       			 row.createCell(11).setCellValue("满意");//回访情况
       		 }
       		 else if(cb==0){
       			 row.createCell(11).setCellValue("不满意");
       		 }
       		 else{
       			 row.createCell(11).setCellValue("");
       		 }
   		 }
   		 else{
   			 row.createCell(11).setCellValue("");
   		 }
   	   }
	}
}
