//初始化下拉列表
function initCombox(comboboxObj,surl,oKV,ckId){
		if(oKV==null){
			oKV={k:"id",v:"text"};
		}
		comboboxObj.combobox({    
		    url:surl,    
		    valueField:oKV.k,    
		    textField:oKV.v,
		    //在加载远程数据成功的时候触发。
		    onLoadSuccess:function(){
		    	var data =$(this).combobox('getData');//返回加载数据。
				if(data.length>0){
					//如果传入默认选中的ID不为-1则选中已选中的，否则选中第一个
					if(ckId ==-1){
						$(this).combobox('select',data[0][oKV.k]);//选择指定项。
					}else{
						$(this).combobox('select',ckId);
					}
				}
		    }   
		});
}
function initCombox01(comboboxObj,surl,oKV,ckId){
	if(oKV==null){
		oKV={k:"id",v:"name"};
	}
	comboboxObj.combobox({    
	    url:surl,    
	    valueField:oKV.k,    
	    textField:oKV.v,
	    //在加载远程数据成功的时候触发。
	    onLoadSuccess:function(){
	    	var data =$(this).combobox('getData');//返回加载数据。
			if(data.length>0){
				//如果传入默认选中的ID不为-1则选中已选中的，否则选中第一个
				if(ckId ==-1){
					$(this).combobox('select',data[0][oKV.k]);//选择指定项。
				}else{
					$(this).combobox('select',ckId);
				}
			}
	    }   
	});
}
function initCombox02(comboboxObj,surl,oKV,ckId){
	if(oKV==null){
		oKV={k:"id",v:"carnumber"};
	}
	comboboxObj.combobox({    
	    url:surl,    
	    valueField:oKV.v,    
	    textField:oKV.v,
	    //在加载远程数据成功的时候触发。
	    onLoadSuccess:function(){
	    	var data =$(this).combobox('getData');//返回加载数据。
			if(data.length>0){
				//如果传入默认选中的ID不为-1则选中已选中的，否则选中第一个
				if(ckId ==-1){
					$(this).combobox('select',data[0][oKV.v]);//选择指定项。
				}else{
					$(this).combobox('select',ckId);
				}
			}
	    }   
	});
}
function initCombox03(comboboxObj,surl,oKV,ckId){
	if(oKV==null){
		oKV={k:"id",v:"name"};
	}
	comboboxObj.combobox({    
	    url:surl,    
	    valueField:oKV.v,    
	    textField:oKV.v,
	    //在加载远程数据成功的时候触发。
	    onLoadSuccess:function(){
	    	var data =$(this).combobox('getData');//返回加载数据。
			if(data.length>0){
				//如果传入默认选中的ID不为-1则选中已选中的，否则选中第一个
				if(ckId ==-1){
					$(this).combobox('select',data[0][oKV.v]);//选择指定项。
				}else{
					$(this).combobox('select',ckId);
				}
			}
	    }   
	});
}
function initCombox04(comboboxObj,surl,oKV,ckId){
	if(oKV==null){
		oKV={k:"id",v:"output"};
	}
	comboboxObj.combobox({    
	    url:surl,    
	    valueField:oKV.v,    
	    textField:oKV.v,
	    //在加载远程数据成功的时候触发。
	    onLoadSuccess:function(){
	    	var data =$(this).combobox('getData');//返回加载数据。
			if(data.length>0){
				//如果传入默认选中的ID不为-1则选中已选中的，否则选中第一个
				if(ckId ==-1){
						$(this).combobox('select',data[0][oKV.v]);
				}else{
					$(this).combobox('select',ckId);
				}
			}
	    }   
	});
}
//初始化下拉列表,不选择指定的列
function initComboxData(comboboxObj,surl,oKV){
	if(oKV==null){
		oKV={k:"id",v:"text" +
				""};
	}
	comboboxObj.combobox({    
	    url:surl,    
	    valueField:oKV.k,    
	    textField:oKV.v
	});
}


//格式化时间为:yyyy-MM-dd
////getFullYear() 方法可返回一个表示年份的 4 位数字。
function formatDate(sDate){
	var oDate=new Date(sDate);
	var str=oDate.getFullYear()+'-'+addZero(oDate.getMonth()+1)+'-'+addZero(oDate.getDate());
	return str;
}


function addZero(num){
	if(num<10){
		return '0'+num;
	}else{
		return num;
	}
}

//获取当前时间 yyyy-MM-dd
function getNowDate(){
	var oDate=new Date();
	var str=oDate.getFullYear()+'-'+addZero(oDate.getMonth()+1)+'-'+addZero(oDate.getDate());
	return str;
}

//获取详细当前时间
function getNowDateDetail(){
	var oDate=new Date();
	var str=oDate.getFullYear()+'-'+addZero(oDate.getMonth()+1)+'-'+addZero(oDate.getDate())+" "+addZero(oDate.getHours())+":"+addZero(oDate.getMinutes())+":"+addZero(oDate.getSeconds());
	return str;
}
function formatDateDetail(sDate){
	var oDate=new Date(sDate);
	var str=oDate.getFullYear()+'-'+addZero(oDate.getMonth()+1)+'-'+addZero(oDate.getDate())+" "+addZero(oDate.getHours())+":"+addZero(oDate.getMinutes())+":"+addZero(oDate.getSeconds());
	return str;
}
