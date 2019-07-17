package com.company.sys.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.sys.entity.SysUser;
import com.company.sys.service.SysUserService;
import com.company.sys.vo.JsonResult;
import com.company.sys.vo.PageObject;
import com.company.sys.vo.SysUserDeptVo;

@RestController
@RequestMapping("/user/")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
			String username,Integer pageCurrent){
		PageObject<SysUserDeptVo> pageObject=
				sysUserService.findPageObjects(username, pageCurrent);
		return new JsonResult(pageObject);
	}

	@RequestMapping("doValidById")
	public JsonResult doValidById(Integer id,Integer valid) {
		sysUserService.validById(id,valid,"admin");//"admin"用户将来是登陆用户
		return new JsonResult("update ok");

	}	

	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysUser entity,Integer[] roleIds){
		sysUserService.saveObject(entity,roleIds);
		return new JsonResult("save ok");
	}
	/**8.用户修改页面数据呈现
	 * 
	 * 1).接收客户端请求数据 (userId)
	 * 2).调用业务方法查询用户,部门以及对应角色信息
	 * 3).封装查询数据,并返回
	 * */
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(
			Integer id){
		Map<String,Object> map=
		sysUserService.findObjectById(id);
		return new JsonResult(map);
	}
	
	/**9.
	 * 1)接收客户端请求数据
	 * 2)调用业务层方法,将请求数据更新到数据库
	 * 3)封装数据并返回.
	 */
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(
	SysUser entity,Integer[] roleIds) {
		
		sysUserService.updateObject(entity, roleIds);
		return new JsonResult("update Ok");
	}

	 
	 /**
	  * 11.用户密码修改实现
	  */
	@RequestMapping("doUpdatePassword")
	public JsonResult doUpdatePassword(
			 String pwd,
			 String newPwd,
			 String cfgPwd) {
		 sysUserService.updatePassword(pwd, newPwd, cfgPwd);
		 return new JsonResult("update ok");
	 }

	
	/**
	 * 1)    在SysUserController添加登录方法doLogin
     * 2) 接收用户名及密码参数，并对其进行有效验证
     * 3) 执行登录认证
     * */
	@RequestMapping("doLogin")
	public JsonResult doLogin(
		boolean isRememberMe,
		String username,
		String password) {
		//1.获取Subject对象
		Subject subject = SecurityUtils.getSubject();
		//2.通过Subject提交用户信息,交给shiro框架进行认证操作
		//2.1对用户进行封装
		UsernamePasswordToken token =
		new UsernamePasswordToken(
		username, //身份信息
		password);//凭证信息
		
		 if(isRememberMe) {
				token.setRememberMe(true); 
			 }

		//2.2对用户信息进行身份认证
		subject.login(token);
		return new JsonResult("login OK");
	}
	
	
	
}
