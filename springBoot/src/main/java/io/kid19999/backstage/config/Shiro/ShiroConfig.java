package io.kid19999.backstage.config.Shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author kid1999
 * @title: Shiro 的 配置
 * @date 2019/11/26 21:27
 */

@Configuration
public class ShiroConfig {

	// 3. 创建 ShiroFilterFactoryBean
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		/**  添加Shiro的内置过滤器
		 * 常用：
		 * anon : 无需认证（登录） 可以访问
		 * authc ：必须认证才可以访问
		 * user : 如果使用remenberMe功能可以直接访问
		 * perms: 该资源必须获得相关权限才可访问
		 * role: 该资源必须获得角色权限才可以访问  roles[admin] || hasAnyRoles[admin,partner]
		 *
		 *  注意： 这个授权拦截链 是按顺序执行的！！！
		 */
		Map<String, String> filterMap = new LinkedHashMap<>();
//		filterMap.put("/**","anon");
//		filterMap.put("/login", "authc");
//		filterMap.put("/error","anon");
//		filterMap.put("/logout","logout");
//
//		// 授权过滤器
		filterMap.put("/addAdmin","roles[admin]");
//		filterMap.put("/**", "authc");

		// 注入 拦截器和认证
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		Map<String,Filter> filter = new HashMap<>();
	    filter.put("authc",new AuthenticationFilter());
		filter.put("logout",new MyLogoutFilter());
		shiroFilterFactoryBean.setFilters(filter);
		return shiroFilterFactoryBean;
	}

	// 2. 创建DefaultWebSecurityManager  管理器
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") UserRealm userRealm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 关联realm
		securityManager.setRealm(getRealm());
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(cacheManager());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	// 1. 创建Realm
	@Bean
	public UserRealm getRealm(){
		return new UserRealm();
	}


	// 整合 redis
	/**
	 * cacheManager 缓存 redis实现
	 * 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * 配置shiro redisManager
	 * 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost("10.16.11.211:6379");
		redisManager.setTimeout(1800);
		return redisManager;
	}

	/**
	 * Session Manager
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}


}
