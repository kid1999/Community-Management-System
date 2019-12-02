package io.kid19999.backstage.config.Shiro;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @desc:
 * @auther: kid1999
 * @date: 2019/11/29 16:53
 **/
public class MyLogoutFilter extends LogoutFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        //在这里执行退出系统前需要清空的数据
        Subject subject=getSubject(request,response);
        ServletContext context= request.getServletContext();
        try {
            subject.logout();
            System.out.println("logout");
        }catch (SessionException e){
            e.printStackTrace();
        }
        return false;
    }

}
