package com.company.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.sys.entity.SysRole;
import com.company.sys.service.SysRoleService;
import com.company.sys.vo.JsonResult;
import com.company.sys.vo.PageObject;

@RestController
@RequestMapping("/role/")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
			String name,Integer pageCurrent){
		PageObject<SysRole> pageObject=
				sysRoleService.findPageObjects(name,pageCurrent);
		return new JsonResult(pageObject);
	}

	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {		 
		sysRoleService.deleteObject1(id);
		return new JsonResult("删除成功!");
	}

	/**角色添加*/
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysRole entity,Integer[] menuIds){
		sysRoleService.saveObject(entity,menuIds);
		return new JsonResult("save ok"); 
	}

	/**角色修改页面呈现*/
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id){
		return new JsonResult(sysRoleService.findObjectById(id));
	}

	/**角色修改操作呈现*/
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysRole entity,
			Integer[] menuIds){
		sysRoleService.updateObject(entity, menuIds);
		return new JsonResult("update ok");
	}
	
	/**依据请求获取角色id和角色名,封装数据,并返回*/
	@RequestMapping("doFindRoles")
	public JsonResult doFindObjects() {
		return new JsonResult(sysRoleService.findObjects());
	}
	
}






























































