package io.kid19999.backstage.config.Shiro;

import io.kid19999.backstage.model.Admin;
import io.kid19999.backstage.repository.AdminRepository;
import io.kid19999.backstage.utils.SomeUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kid1999
 * @title: UserRealm 自定义 realm 处理 授权和认证
 * @date 2019/11/26 21:29
 */



public class UserRealm extends AuthorizingRealm {
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private SomeUtils someUtils;

	// 授权执行逻辑
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("授权执行逻辑");
		// 给资源进行授权
		String studentId = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Admin user = adminRepository.findByAdminId(studentId);
		// 添加资源使用角色
		info.addRole(user.getAdminRole());
		return info;
	}

	// 认证执行逻辑
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("认证执行逻辑");

		// 1. 获取传来验证的token
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String name = token.getUsername();
		String password = String.valueOf(token.getPassword());
		// 2. 通过用户名 取出数据库的数据
		Admin user = adminRepository.findByAdminId(name);

		// 3. 判断用户名
		if (user == null){
			return null;        // 找不到用户名 报 UnknownAccountException
		}

		// 4. 判断密码
		/**
		 * arg0: 用户名
		 * arg1: 数据库的密码
		 * arg2: realm 的name
		 */
		return new SimpleAuthenticationInfo(token.getUsername(),user.getAdminPwd(),"");
	}
}
