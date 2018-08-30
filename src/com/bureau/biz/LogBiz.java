package com.bureau.biz;

import java.util.List;

import com.bureau.pojo.Log;
import com.bureau.pojo.PageData;

public interface LogBiz {
	/**
	 * 查询所有的log
	 * @param u 实体
	 * @return 数量
	 */
	public PageData<Log> findAllLog(Log log,String idStart, String idEnd);
	
	public List<Log> findBytime(String idStart, String idEnd);
	
	/**
	 * 添加log
	 *  @param u 实体
	 * @return 成功与否
	 */
	public boolean addLog(Log log);
	
	/**
	 * 删除用户
	 * @param u
	 * @return
	 */
	public boolean delLog(Log log);
}
