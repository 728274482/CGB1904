package com.company.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.company.sys.entity.SysRole;
import com.company.sys.vo.CheckBox;
import com.company.sys.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {
	/**
	   *   分页查询角色信息
	 * @param startIndex 上一页的结束位置
	 * @param pageSize 每页要查询的记录数
	 * @return
	 */

	List<SysRole> findPageObjects(
			@Param("name")String  name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/**
	   * 查询记录总数
	 * @return
	 */
	int getRowCount(@Param("name")String name);

	@Delete("delete from sys_roles where id=#{id}")
	int deleteObject1(Integer id);

	int insertObject(SysRole entity);
	
	SysRoleMenuVo findObjectById(Integer id);
	
	int updateObject(SysRole entity);
	
	/**添加一个查询角色ID,角色名的方法*/
	List<CheckBox> findObjects();

}
