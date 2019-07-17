package com.company.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @Configuration 注解描述的类为一个配置对象,
 *    此对象也会交给spring管理
 */
@Configuration
public class SpringShiroConfig {
	/**
	 * @Bean 描述的方法,其返回值会交给spring管理
	 * @Bean 一般应用在整合第三bean资源时
	 * 
	   *   配置SecurityManager对象(Shiro框架的核心管理器对象)
	 * */
	@Bean
	public SecurityManager newSecurityManager(
			@Autowired Realm realm,
			@Autowired CacheManager cacheManager) {

		DefaultWebSecurityManager sManager=
				new DefaultWebSecurityManager();

		sManager.setRealm(realm);
		sManager.setCacheManager(cacheManager);
		sManager.setRememberMeManager(newRememberMeManager());
		//对安全管理器(securityManager)增加  sessionManager值的注入
		sManager.setSessionManager(newSessionManager());
		return sManager;
	}

	/**配置缓存Bean对象(Shiro框架提供)*/
	@Bean
	public CacheManager newCacheManager(){
		return new MemoryConstrainedCacheManager();
	}




	/** 创建一个Bean对象,通过此对象创建一个过滤器工厂,
	 * 	通过此工厂创建ShiroFilter对象,最后通过过滤器对象
	   *      对请求数据进行过滤*/
	@Bean("shiroFilterFactory")
	public ShiroFilterFactoryBean newShiroFilterFactoryBean(
			@Autowired	SecurityManager securityManager	) {

		ShiroFilterFactoryBean sfBean=
				new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);

		//假如没有认证请求先访问此认证的url
		sfBean.setLoginUrl("/doLoginUI");

		//定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		LinkedHashMap<String,String> map=
				new LinkedHashMap<>();

		//静态资源允许匿名访问:"anon"
		map.put("/bower_components/**","anon");
		map.put("/build/**","anon");
		map.put("/dist/**","anon");
		map.put("/plugins/**","anon");

		map.put("/user/doLogin","anon");//对/user/doLogin.do这个路径进行匿名访问的配置
		map.put("/doLogout","logout");//退出操作配置实现

		//设置必须认证才可以访问的资源
		map.put("/**","user");
		sfBean.setFilterChainDefinitionMap(map);
		return sfBean;

	}

	/**配置shiro框架中一些bean对象的生命周期管理器*/
	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor 
	newLifecycleBeanPostProcessor() {

		return new LifecycleBeanPostProcessor();
	}

	/**配置代理对象创建器,通过此对象为目标业务对象创建代理对象(spring框架)
	 * 为目标对象(方法上有@RequiresPermissions注解)创建代理对象，
	 * 通过代理对象调用通知(Advice)实现授权检测
	 * */
	@DependsOn("lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator 
	newDefaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	/**配置advisor对象,shiro框架底层会通过
	 * 此对象的matchs方法返回值决定是否创建代理对象,进行权限控制
	 * 
	 * 次对象负责定义切入点以及通知
	 * */
	public AuthorizationAttributeSourceAdvisor 
	newAuthorizationAttributeSourceAdvisor(
			@Autowired SecurityManager securityManager) {

		AuthorizationAttributeSourceAdvisor advisor=
				new AuthorizationAttributeSourceAdvisor();
		return advisor;
	}

	/**添加cookie设置*/
	public SimpleCookie newCookie() {
		SimpleCookie c=new SimpleCookie("rememberMe");
		c.setMaxAge(10*60);
		return c;
	}

	public CookieRememberMeManager newRememberMeManager() {
		CookieRememberMeManager cManager=
		new CookieRememberMeManager();
		cManager.setCookie(newCookie());
		return cManager;
	}

	/**添加会话管理器配置*/
	public DefaultWebSessionManager newSessionManager() {
		DefaultWebSessionManager sManager=
		new DefaultWebSessionManager();
		sManager.setGlobalSessionTimeout(60*60*1000);
		return sManager;
	}



}
