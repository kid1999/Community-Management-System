package io.kid19999.backstage.controller;

import io.kid19999.backstage.dto.Result;
import io.kid19999.backstage.model.Admin;
import io.kid19999.backstage.repository.AdminRepository;
import io.kid19999.backstage.utils.SomeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kid1999
 * @title: SuperAdminController
 * @date 2019/11/26 15:35
 */

@RestController
public class SuperAdminController {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private SomeUtils someUtils;


	@PostMapping("/admin")
	Result addAdmin(@RequestBody Admin admin){
		System.out.println(admin);
		String password = someUtils.md5(admin.getAdminPwd(),admin.getAdminId());		//加密密码
		admin.setAdminPwd(password);
		Result result = new Result();
		if(!admin.getAdminId().isEmpty() || !admin.getAdminPwd().isEmpty()){
			result.setInfo("账号|密码不能为空");
			result.setReslut(0);
		}else if(adminRepository.findByAdminId(admin.getAdminId()) == null){
			admin.setAdminRole("admin");
			adminRepository.save(admin);
			result.setInfo("添加成功");
			result.setReslut(1);
		}
		else{
			result.setInfo("账号已存在！");
			result.setReslut(0);
		}
		return result;
	}
}