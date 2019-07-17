package com.company.sys.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleDao {

	@Delete("delete from sys_user_roles where role_id=#{roleId}")
	int deleteObject3(Integer roleId);
	
	/**
	   * 负责将用户与角色的关系数据写入到数据库
	 * @param userId 用户id
	 * @param roleIds 多个角色id
	 * @return
	 */
	int insertObjects(
	@Param("userId")Integer userId,
	@Param("roleIds")Integer[] roleIds);

	/**根据用户id查询角色id(可能是多个)
	 * shiro框架的授权管理也用到此方法
	 * */
	List<Integer> findRoleIdsByUserId(Integer id);
	
	/**9.先删除关系数据再添加*/
	int deleteObjectsByUserId(Integer userId);
	
}
