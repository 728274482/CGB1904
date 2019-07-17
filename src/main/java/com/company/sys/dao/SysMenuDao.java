package com.company.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.company.sys.entity.Node;
import com.company.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {

	/***
	 * 查询所有菜单以及对应的上级菜单名称
	 * @return
	 */
	List<Map<String,Object>> findAllMenu();
	
	/**执行添加时查询父级菜单的信息*/
	List<Node> findZtreeMenuNodes();
	
	/**添加菜单信息*/
	int insertObject(SysMenu entity);
	
	/**修改菜单信息*/
	int updateObject(SysMenu entity);
	
	/**根据菜单id统计子菜单的个数*/
	int getChildCount(Integer id);
	
	/**根据id 删除菜单*/
	int deleteObject(Integer id);
	
	/**基于多个菜单id查找权限标识信息(shiro框架授权管理)*/
	List<String> findPermissions(
			@Param("menuIds")
			Integer[] menuIds);

	
	
	
	
}
