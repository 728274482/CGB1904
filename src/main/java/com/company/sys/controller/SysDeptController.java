package com.company.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.sys.entity.SysDept;
import com.company.sys.service.SysDeptService;
import com.company.sys.vo.JsonResult;

@RestController
@RequestMapping("/dept/")
public class SysDeptController {
	@Autowired
	private SysDeptService SysDeptService;
	
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects(){
	return new JsonResult(SysDeptService.findObjects());
	}
	
	@RequestMapping("doDeleteObject")
	 public JsonResult doDeleteObject(Integer id){
		SysDeptService.deleteObject(id);
		 return new JsonResult("删除OK");
	 }
						
	@RequestMapping("doFindZtreeDeptNodes")
	public JsonResult doFindZtreeDeptNodes(){
		 return new JsonResult(
		SysDeptService.findZtreeDeptNodes());
	 }
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysDept entity){
		SysDeptService.insertObject(entity);
		return new JsonResult("save ok");
	}

	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysDept entity){
		 SysDeptService.updateObject(entity);
	    return new JsonResult("update ok");
	}


}
