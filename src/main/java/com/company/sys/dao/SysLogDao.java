package com.company.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.company.sys.entity.SysLog;

@Mapper
public interface SysLogDao {

	List<SysLog> findPageObjects(
			@Param("username")String  username,
		    @Param("startIndex")Integer startIndex,
		    @Param("pageSize")Integer pageSize);

	int getRowCount(@Param("username")String username);
	
	int deleteLogById(@Param("ids") Integer... ids);
}
