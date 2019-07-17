package com.company.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.sys.entity.SysMenu;
import com.company.sys.service.SysMenuService;
import com.company.sys.vo.JsonResult;


@RestController
@RequestMapping("/menu/")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping("doFindObjects")
	public JsonResult findAllMenu() {
		List<Map<String, Object>> list = 
				sysMenuService.findAllMenu();
		return new JsonResult(list);
	}

	@RequestMapping("doFindZtreeMenuNodes")
	public JsonResult doFindZtreeMenuNodes(){
		return new JsonResult(
				sysMenuService.findZtreeMenuNodes());
	}

	@RequestMapping("doSaveObject")
	public JsonResult insertObject(SysMenu entity) {
		sysMenuService.insertObject(entity);
		return new JsonResult("保存成功!");
	}

	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id){
		sysMenuService.deleteObject(id);
		return new JsonResult("删除OK");
	}


	@RequestMapping("doUpdateObject")
	public JsonResult updateObject(SysMenu entity) {
		sysMenuService.updateObject(entity);
		return new JsonResult("数据更新成功!");
	}

}
































































