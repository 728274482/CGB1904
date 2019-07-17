package com.company.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.company.sys.entity.SysUser;
import com.company.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {
	int getUserCountByDeptId (Integer DeptId);
	
	/**用户模块列表数据呈现*/
	List<SysUserDeptVo> findPageObjects(
			@Param("username") String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("username") String username);
	
	/**用户禁用启用操作实现*/
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);

	/**
	   * 负责将用户信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);
	
	/**根据用户id查询用户以及部门信息*/
	SysUserDeptVo findObjectById(Integer id);
	
	/**9.更新用户自身数据*/
	int updateObject(SysUser entity);
	
	/**11.
	   * 基于用户id修改用户的密码
	 * @param password 新的密码
	 * @param salt 密码加密时使用的盐值
	 * @param id 用户id
	 * @return
	 */
	 int updatePassword(
	 @Param("password")String password,
	 @Param("salt")String salt,
	 @Param("id")Integer id);
	 
	 
	 /**根据用户名获取用户对象(身份认证)*/
	 @Select("select * from sys_users where username=#{username}")
	 SysUser findUserByUserName(String username);
}
