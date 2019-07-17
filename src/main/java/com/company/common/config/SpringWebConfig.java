package com.company.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.company.sys.web.TimeInterceptor;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer{
	/**配置spring mvc拦截器*/
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("拦截器已运行");
		registry.addInterceptor(new TimeInterceptor())
		.addPathPatterns("/user/doLogin");
		
		
	}
	
	//注册filter对象
	@SuppressWarnings({"rawtypes","unchecked"})
	@Bean
	public FilterRegistrationBean newFilterRegistrationBean() {
		//1.构建过滤器的注册器对象
		FilterRegistrationBean fBean = 
		new FilterRegistrationBean();
		
		//2.注册过滤器对象
		DelegatingFilterProxy filter =
		new DelegatingFilterProxy("shiroFilterFactory");
		fBean.setFilter(filter);
		
		//3.进行过滤器配置
		fBean.addUrlPatterns("/*");
		
		return fBean;
	}
}
