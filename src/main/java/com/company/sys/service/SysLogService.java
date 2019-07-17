package com.company.sys.service;

import org.apache.ibatis.annotations.Param;

import com.company.sys.entity.SysLog;
import com.company.sys.vo.PageObject;

public interface SysLogService {

	 /**
     * 通过此方法实现分页查询操作
     * @param useruserusername 基于条件查询时的参数名
     * @param pageCurrent 当前的页码值
     * @return 当前页记录+分页信息
     */
	PageObject<SysLog> findPageObjects(
			@Param("username")String  username,
		    @Param("pageCurrent")Integer pageCurrent);	
	
	int deleteLogById(@Param("ids") Integer... ids);

}
