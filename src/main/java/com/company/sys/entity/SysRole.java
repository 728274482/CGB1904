package com.company.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SysRole implements Serializable{
	private static final long serialVersionUID = -7882543236388347196L;
	private Integer id;
	//用户名
	private String name;
	//用户操作
	private String note;
	//创建时间
	private Date createdTime;
	//修改时间
	private Date modifiedTime;
	//创建用户
	private String createdUser;
	//修改用户
	private String modifiedUser;
}
