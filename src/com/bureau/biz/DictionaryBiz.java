package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.Dictionary;
import com.bureau.pojo.PageData;


public interface DictionaryBiz {

	/**
	 * 添加字典表数据
	 * @param dic	待添加的字典表实体
	 * @return
	 */
	public boolean addDictionary(Dictionary dic);
	
	/**
	 * 通过父类获取字典表数据
	 * @param dic
	 * @return
	 */
	public List<Dictionary> findDicByParent(Dictionary dic);

	//通过父类ID获取对应的表格数据
	
	public PageData<Dictionary> findDicPageDataByPid(Dictionary dic);

	//通过级别查找字典表数据
	public List<Dictionary> findDicByLev(Integer lev);
	

	//通过ID查找字典表数据
	public Dictionary findById(Integer id);

	//更新字典表数据
	public boolean updDictionary(Dictionary dic);
	
	public List<Dictionary> findDictionaryByName(String text);
	
	public List<Dictionary> findDicByParentID(Integer pid);
	
	public Dictionary findByText(String text);
	
	public Integer findCarState(String text);
	
}
