package com.company.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 在此controller中配置所有页面
 * */
@Controller
@RequestMapping("/")
public class PageController {

	/***********返回首页页面***********/
	@RequestMapping("doIndexUI")
	public String doHelloUI() {
		return "starter";
	}
	/***********分页div页面***********/
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	
	/**
	 * 当服务端拦截到用户请求以后,判定此请求是否已经被认证,
	 * 假如没有认证应该先跳转到登录页面,添加一个呈现登录页面的方法
	 * */
	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}
	
	/***********所有页面综合设计************/
	@RequestMapping("{module}/{page}")
	public String doModuleUI(
			@PathVariable String page) {
		return "sys/"+page;
	}
	
//	/***********日志列表页面************/
//	@RequestMapping("log/doLogUI")
//	public String doLogUI() {
//		return "sys/log_list";
//	}
//	/***********菜单列表页面***********/
//	@RequestMapping("menu/doMenuUI")
//	public String doMenuUI() {
//		return "sys/menu_list";
//	}
}













































































































































































