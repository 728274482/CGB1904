package com.company.sys.vo;

import java.io.Serializable;
import java.util.Date;

import com.company.sys.entity.SysDept;

import lombok.Data;
@Data
public class SysUserDeptVo implements Serializable{
	
	private static final long serialVersionUID = 4778703797624571133L;
	private Integer id;
	private String username;
	private String password;//md5
	private String salt;
	private String email;
	private String mobile;
	private Integer valid=1;
	private SysDept sysDept; //private Integer deptId;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;

}
