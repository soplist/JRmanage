package com.bureau.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bureau.biz.OldepcarBiz;
import com.bureau.pojo.Changeequipment;
import com.bureau.pojo.ChangeequipmentVo;
import com.bureau.pojo.Oldepcar;
import com.bureau.pojo.OldepcarVo;
import com.bureau.pojo.PageData;
import com.bureau.pojo.TempJson;
import com.bureau.unit.Quarter;
import com.bureau.unit.SysConst;
import com.opensymphony.xwork2.ModelDriven;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018-8-18 上午9:46:23 
 * 类说明 
 */
public class OldepcarAction  extends BaseAction implements ModelDriven<Oldepcar> {
	
	private Oldepcar o;
	private OldepcarBiz oBiz;
	
	private static List<OldepcarVo> ocvlist = null;
	
	//接收前台参数
	private String idStart;
	private String idEnd;
	public void setIdStart(String idStart) {
		this.idStart = idStart;
	}
	public String getIdStart() {
		return idStart;
	}
	public void setIdEnd(String idEnd) {
		this.idEnd = idEnd;
	}
	public String getIdEnd() {
		return idEnd;
	}
	
    public void setoBiz(OldepcarBiz oBiz) {
		this.oBiz = oBiz;
	}
	public Oldepcar getO() {
		return o;
	}
    public void setO(Oldepcar o) {
		this.o = o;
	}


	public Oldepcar getModel() {
		if (o == null) {
			o = new Oldepcar();
		}
		return o;
	}

	public void findAll(){
		PageData<OldepcarVo> pd = new PageData<OldepcarVo>();
		pd = oBiz.findAll(idStart,idEnd,o);
		ocvlist = pd.getRows();
		this.writeJSON(pd);
	}
	
	public void findBytime(){
		try{
			ocvlist = oBiz.findBytime(idStart,idEnd,o);
		    this.writeJSON(ocvlist);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void del(){
		try{
		    TempJson tj=new TempJson();
		    tj.setMessage(SysConst.DELFAIL);
		    
		    o = oBiz.findById(o.getId());
		    
		    boolean res = oBiz.del(o);
		
		    if(res){
			    tj.setMessage(SysConst.DELSUCCESS);
			    tj.setSuccess(true);
		    }
		    this.writeJSON(tj);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void add() throws UnsupportedEncodingException{
		TempJson tj=new TempJson();
		tj.setMessage(SysConst.ADDFAIL);
		boolean res = false;	
		o.setCreatetime(Quarter.getNowTime());
		res = oBiz.add(o);
		if(res){
			tj.setMessage(SysConst.ADDSUCCESS);
			tj.setSuccess(true);
		}
		this.writeJSON(tj);
	}
	
	public void update(){
		TempJson tj=new TempJson();
		boolean result = oBiz.update(o); 
		if(result){
			tj.setMessage(SysConst.UPDSUCCESS);
			tj.setSuccess(true);
		}else{
			tj.setMessage(SysConst.UPDFAIL);
		}
		this.writeJSON(tj);
	}
	
	//实现导出列表功能
	public void exportExcel() throws IOException{
		HSSFWorkbook wb=null;
		try{
			wb = oBiz.export(ocvlist);
			response.reset();//清除缓冲中的数据
			response.setContentType("application/vnd.ms-excel;charset=utf-8");  
			response.setHeader("Content-disposition", "attachment;filename=export.xls");
			response.flushBuffer();//将缓冲区中的所有数据发送到客户端
			OutputStream ouputStream = response.getOutputStream();  
			wb.write(ouputStream);  
			ouputStream.flush();  
			ouputStream.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
}
