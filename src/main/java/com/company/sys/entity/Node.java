package com.company.sys.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Node implements Serializable{
	private static final long serialVersionUID = -6343646655881965104L;
	private Integer id;
	private String name;
	private Integer parentId;

}
