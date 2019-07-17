package com.company.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.sys.entity.SysRole;
import com.company.sys.vo.CheckBox;
import com.company.sys.vo.PageObject;
import com.company.sys.vo.SysRoleMenuVo;

public interface SysRoleService {

	 /**
	      * 通过此方法实现分页查询操作
	  * @param useruserusername 基于条件查询时的参数名
      * @param pageCurrent 当前的页码值
      * @return 当前页记录+分页信息
      */
	PageObject<SysRole> findPageObjects(
			@Param("name")String  name,
		    @Param("pageCurrent")Integer pageCurrent);	
	
	int deleteObject1(Integer id);
	int deleteObject2(Integer roleId);
	int deleteObject3(Integer roleId);

	int saveObject(SysRole entity,Integer[] menuIds);
	
	SysRoleMenuVo findObjectById(Integer id);

	int updateObject(SysRole entity,Integer[] menuIds);
	
	/**通过此方法获取所有的角色id,角色名*/
	List<CheckBox> findObjects();
	
	
}
