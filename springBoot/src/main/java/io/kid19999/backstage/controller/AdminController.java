package io.kid19999.backstage.controller;

import io.kid19999.backstage.dto.Result;
import io.kid19999.backstage.repository.AdminRepository;
import io.kid19999.backstage.utils.SomeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kid1999
 * @title: managerController
 * @date 2019/11/24 14:21
 */


@RestController
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private SomeUtils someUtils;


	/**
	 * 使用Shiro实现登录
	 */
	@PostMapping("/login")
    Result toLogin(@RequestParam("adminId") String username,
				   @RequestParam("adminPwd") String password){
		Result result = new Result();
		// 1.获取Subject
		Subject subject = SecurityUtils.getSubject();
		// 2.封装登录方法
		String passwd = someUtils.md5(username,password);
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try {
			// 3.执行登录方法
			subject.login(token);       // 调用login方法 -> realm 做验证
			result.setReslut(1);
			result.setInfo("111111");
			return result;
		}catch (UnknownAccountException e){
			result.setReslut(0);
			result.setInfo("用户名不存在");
			return result;
		}catch (IncorrectCredentialsException e){
			result.setReslut(0);
			result.setInfo("密码错误");
			return result;
		}
	}




	/**
	 * 管理员主页面
	 * @return
	 */
	@GetMapping("/admin")
    String manager(){
		return "admin";
	}

	/**
	 * 添加会员信息页面
	 */
	@GetMapping("/addMenber")
    String addMenber(){
		return "addMenber";
	}

	/**
	 * 添加会员信息验证
	 * @return
	 */
	@PostMapping("/addMenber")
    String toAddMember(){
		return "";
	}



}
