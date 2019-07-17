package com.company.sys.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.sys.dao.SysLogDao;
import com.company.sys.entity.SysLog;
import com.company.sys.exception.ServiceException;
import com.company.sys.service.SysLogService;
import com.company.sys.vo.PageObject;

@Service
public class SysLogServiceImpl implements SysLogService{

	@Autowired
	private SysLogDao sysLogDao;
	@Override
	public PageObject<SysLog> findPageObjects(
			String username, 
			Integer pageCurrent) {
		//1.验证参数合法性
		//1.1验证pageCurrent的合法性
		if(pageCurrent==null||pageCurrent<1) 
			throw new IllegalArgumentException("当前页码不正确");
		//不合法抛出Ille0galArgumentException异常
		//2.基于条件查询总记录数
		//2.1执行查询
		int rowCount= sysLogDao.getRowCount(username);
		//2.2验证查询结果,假如结果为0则不再执行如下操作
		  if(rowCount==0)
	          throw new ServiceException("系统没有查到对应记录");
			  //3.基于条件查询当前页记录(pageSize定义为2)
			  //3.1)定义pageSize
			  int pageSize=5;
			  //3.2)计算startIndex
			  int startIndex=(pageCurrent-1)*pageSize;
			  //3.3)执行当前数据的查询操作
			  List<SysLog> records=
			  sysLogDao.findPageObjects(username, startIndex, pageSize);
			  //4.对分页信息以及当前页记录进行封装
			  //4.1)构建PageObject对象
			  PageObject<SysLog> pageObject=new PageObject<>();
			  //4.2)封装数据
			  pageObject.setPageCurrent(pageCurrent);
			  pageObject.setPageSize(pageSize);
			  pageObject.setRowCount(rowCount);
			  pageObject.setRecords(records);
	          pageObject.setPageCount((rowCount-1)/pageSize+1);
			  //5.返回封装结果。
			  return pageObject;
		  }
	@Override
	public int deleteLogById(Integer... ids) {
		// 1.验证id是否存在
		if(ids==null||ids.length==0) 
			throw new IllegalArgumentException("请选择一条记录进行删除!");
		//2.执行删除操作
		int rows;
		try {
		rows= sysLogDao.deleteLogById(ids);
		}catch (Throwable  e) {
			e.printStackTrace();
		//3.维护信息展示
			throw new ServiceException("系统正在维护中,小帅哥且慢!");
		}
		//4.对结果进行校验
		if(rows==0) 
			throw new ServiceException("禀告小帅删除失败");	
		return rows;
	}

	
         
}
