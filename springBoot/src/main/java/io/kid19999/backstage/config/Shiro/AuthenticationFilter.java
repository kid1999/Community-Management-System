package io.kid19999.backstage.config.Shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @desc:   自定义 登录验证 的操作
 * @auther: kid1999
 * @date: 2019/11/28 20:52
 **/

public class AuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //获取已登录的用户信息
        String username = (String) subject.getPrincipal();
        //获取session
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        //把用户信息保存到session
        session.setAttribute("activeUser", username);

        System.out.println("login success");
        System.out.println(token.getPrincipal());
        String name = token.getPrincipal().toString();
        System.out.println(name);

        return super.onLoginSuccess(token, subject, request, response);
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("拒绝访问");
        return super.onAccessDenied(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        System.out.println("登录失败");
        return super.onLoginFailure(token, e, request, response);
    }

}
