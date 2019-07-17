package com.company.sys.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.sys.dao.SysMenuDao;
import com.company.sys.dao.SysRoleMenuDao;
import com.company.sys.entity.Node;
import com.company.sys.entity.SysMenu;
import com.company.sys.exception.ServiceException;
import com.company.sys.service.SysMenuService;

import io.micrometer.core.instrument.util.StringUtils;

@Service
public class SysMenuServiceImpl implements SysMenuService{

	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	public List<Map<String, Object>> findAllMenu() {
		List<Map<String, Object>> list = 
				sysMenuDao.findAllMenu();
		if(list==null||list.size()==0) 
			throw new ServiceException("系统没有找到菜单记录!");
		return list;
	}

	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}

	@Override
	public int insertObject(SysMenu entity) {
		//1.合法验证
		if(entity==null)
			throw new ServiceException("保存对象不能为空!");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("菜单名不能为空!");
		int rows;
		//保存数据
		try {
			rows=sysMenuDao.insertObject(entity);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存失败!");
		}
		return rows;
	}

	@Override
	public int updateObject(SysMenu entity) {
		//1.验证
		if(entity==null)
			throw new ServiceException("保存对象不能为空!");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("菜单名不能为空!");
		//更新数据
		int rows=sysMenuDao.updateObject(entity);
		if(rows==0) {
			throw new ServiceException("记录可能已经不存在!");
		}
		//返回数据
		return rows;
	}

	@Override
	public int deleteObject(Integer id) {
		//1.验证数据的合法性
		if(id==null||id<=0)
			throw new IllegalArgumentException("请先选择");
		//2.基于id进行子元素查询
		int count=sysMenuDao.getChildCount(id);
		if(count>0)
			throw new ServiceException("请先删除子菜单");
		//3.删除菜单元素
		int rows=sysMenuDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("此菜单可能已经不存在");
		//4.删除角色,菜单关系数据
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		//5.返回结果	
		return rows;
	}

}
