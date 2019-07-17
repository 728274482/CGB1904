package com.company.sys.service;

import java.util.List;
import java.util.Map;

import com.company.sys.entity.SysDept;
import com.company.sys.vo.Node;

public interface SysDeptService {
	List<Map<String,Object>> findObjects();
	
	 int deleteObject(Integer id);
	 
	 List<Node> findZtreeDeptNodes();
	 
	 int insertObject(SysDept entity);
	 
	 int updateObject(SysDept entity);
}
