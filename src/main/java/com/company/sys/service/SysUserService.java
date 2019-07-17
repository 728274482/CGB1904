package com.company.sys.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.company.sys.entity.SysUser;
import com.company.sys.vo.PageObject;
import com.company.sys.vo.SysUserDeptVo;

public interface SysUserService {
	PageObject<SysUserDeptVo> findPageObjects(
			@Param("username")String username,
			@Param("pageCurrent")Integer pageCurrent);
	
	int validById(Integer id,Integer valid,String modifiedUser);
 
	int saveObject(SysUser entity, Integer[] roleIds);
	
	Map<String,Object> findObjectById(Integer userId);
	
	/**9.*/
	int updateObject(SysUser entity,Integer[] roleIds);
	
	/**11.
	   * 修改密码
	 * @param password 原密码
	 * @param newPassword 新密码(还没加密)
	 * @param cfgPassword 确认密码
	 * @return	
	 */
	int updatePassword(
			@Param("password")String password,
			@Param("newPassword")String newPassword,
			@Param("cfgPassword")String cfgPassword);

}
