package com.company;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.sys.dao.SysLogDao;
import com.company.sys.entity.SysLog;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSysLog {
	@Autowired
	private SysLogDao sysLogDao;
	@Test
	public void testGetRowCount() {
		int rowCount=sysLogDao.getRowCount("admin");
		System.out.println(rowCount);
	}
	@Test
	public void testFindPageObject() {
		List<SysLog> list = 
				sysLogDao.findPageObjects("admin",0,8);
		System.out.println(list.size());
	}
}
