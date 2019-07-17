package com.company.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Service
@Slf4j
public class SysLogAspect {
	/**
	 * @Pointcut注解用于定义切入点
	 * bean表达式为切入点表达式,
	 * bean表达式内部指定的bean对象中
	 *   所有方法为切入点(进行功能扩展的点)
	 */
	@Pointcut("bean(sysUserServiceImpl)")
	public void logPointCut() {}

	/**
	 * @Around   描述的方法为环绕通知,用于功能增强
	 *   	                环绕通知(目标方法执行之前和之后都可以执行)
	 * @param jp 连接点 (封装了要执行的目标方法信息)
	 * @return   目标方法的执行结果
	 * @throws   抛出的异常最好是Throwable
	 */
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint jp)
			throws Throwable{
		try {
			log.info("start:"+System.currentTimeMillis());
			//调用下一个切面方法或目标方法
			Object result=jp.proceed();
			log.info("after:"+System.currentTimeMillis());
			return result;
		}catch(Throwable e) {
			log.error(e.getMessage());
			throw e;
		}
	}

















}
