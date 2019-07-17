package com.company.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.company.sys.entity.SysDept;
import com.company.sys.vo.Node;

@Mapper
public interface SysDeptDao {
	List<Map<String,Object>> findObjects();
	
	/**根据部门id统计子部门的个数*/
	int getChildCount(Integer id);
	
	/**根据id 删除部门*/
	int deleteObject(Integer id);
	
	List<Node> findZtreeDeptNodes();
	
	int insertObject(SysDept entity);
	
	int updateObject(SysDept entity);
}
