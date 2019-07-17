package com.company.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {
	
	int deleteObjectsByMenuId(Integer menuId);

	@Delete("delete from sys_role_menus where role_id=#{roleId}")
	int deleteObject2(Integer roleId);
	
	int insertObjects(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
	
	int deleteObjectsByRoleId(Integer roleId);

	/**基于用户id查找菜单id信息(shiro框架授权管理)*/
	List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds")Integer[] roleIds);

}
