package io.kid19999.backstage.service;

import io.kid19999.backstage.dto.Login;
import io.kid19999.backstage.mapper.adminMapper;
import io.kid19999.backstage.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

	@Autowired
	private adminMapper adminMapper;

	public boolean toLogin(Login user){
		Admin admins = adminMapper.toLogin(user.getAdminId(),user.getAdminPwd());
		System.out.println(admins);
		return admins != null;
	}

}
