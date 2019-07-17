package com.company.sys.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * VO：通过此对象封装角色以及角色对应的菜单id
 * */
@Data
@ToString
public class SysRoleMenuVo {
	/**角色id*/
	private Integer id;
	/**角色名称*/
	private String name;
	/**角色备注*/
	private String note;
	/**角色对应的菜单id*/
	private List<Integer> menuIds;

}
