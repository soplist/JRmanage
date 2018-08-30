package com.bureau.unit;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.lang.reflect.Method;
import java.util.List;

public class SortList<E>{  
	 //排序方法，三个参数，1需要排序的List，存放对象E，2、E对象需要排序的方法，3，升序还是降序
	@SuppressWarnings("unchecked")
	public void Sort(List<E> list, final String method, final String sort){    
		Collections.sort(list, new Comparator() {  //调用Collections的排序方法     
			public int compare(Object a, Object b) {        
				int ret = 0;        
				try{          
					Method m1 = ((E)a).getClass().getMethod(method, null);//反射获取到List内E对象需要排序的方法名，反射获取方法对象         
					Method m2 = ((E)b).getClass().getMethod(method, null);          
					if(sort != null && "desc".equals(sort))//倒序      	
						ret = m2.invoke(((E)b), null).toString().compareTo(m1.invoke(((E)a), null).toString());//倒序排序List内的对象          
					else//正序 ("asc")          
						ret = m1.invoke(((E)a), null).toString().compareTo(m2.invoke(((E)b), null).toString());        
					}catch(NoSuchMethodException ne){         
						System.out.println(ne);        
					}catch(IllegalAccessException ie){          
						System.out.println(ie);        
					}catch(InvocationTargetException it){          
						System.out.println(it);        
					}       
					return ret;      
				}     
			});  
	}
}
