package com.company.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.sys.entity.SysLog;
import com.company.sys.service.SysLogService;
import com.company.sys.vo.JsonResult;
import com.company.sys.vo.PageObject;

@Controller
@RequestMapping("/log/")
public class SysLogController {

	@Autowired
	private SysLogService sysLogService;
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent){
	 PageObject<SysLog> pageObject=
		sysLogService.findPageObjects(username,pageCurrent);
	return new JsonResult(pageObject);
	}//自动依赖(将结果转换为json字符串)
	
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult deleteLogById(Integer... ids) {
		int rows=sysLogService.deleteLogById(ids);
		return new JsonResult("成功删除:"+rows+"条数据!");
	}

}
































































