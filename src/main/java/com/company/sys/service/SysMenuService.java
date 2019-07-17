package com.company.sys.service;

import java.util.List;
import java.util.Map;

import com.company.sys.entity.Node;
import com.company.sys.entity.SysMenu;

public interface SysMenuService {

	List<Map<String,Object>> findAllMenu();
	
	/**查询父级菜单的信息*/
	List<Node> findZtreeMenuNodes();
	
	/**添加菜单信息*/
	int insertObject(SysMenu entity);
	
	/**删除菜单信息*/
	int deleteObject(Integer id);
	
	/**修改菜单信息*/
	int updateObject(SysMenu entity);
}
