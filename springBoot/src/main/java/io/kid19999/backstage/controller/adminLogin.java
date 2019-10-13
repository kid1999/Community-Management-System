package io.kid19999.backstage.controller;

import io.kid19999.backstage.dto.Login;
import io.kid19999.backstage.dto.Result;
import io.kid19999.backstage.mapper.adminMapper;
import io.kid19999.backstage.model.Admin;
import io.kid19999.backstage.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class adminLogin {

  @Value("${Invitation_code}")
  private String Invitation_code;

  @Autowired
  private adminMapper AdminMapper;

  @Autowired
  private LoginService loginService;

  @PostMapping("/login")
  Result Login(@RequestBody Login login){
    boolean f = loginService.toLogin(login);
    Result res = new Result();
    if(f) {
      res.setReslut(1);
      res.setInfo("登陆成功!");
    }else{
      res.setReslut(0);
      res.setInfo("登录失败!");
    }
    return res;
  }

  @PostMapping("/regsiter")
  Result regsiter(@RequestBody Admin admin){
    Result res = new Result();
    if(admin.getInvitationCode().equals(Invitation_code)){
      Date date1 = new Date();
      java.sql.Date d = new java.sql.Date(date1.getTime());
      admin.setAdminCreateTime(d);
      AdminMapper.insert(admin);
      res.setReslut(1);
      res.setInfo("注册成功!");
    }else{
      res.setReslut(0);
      res.setInfo("注册错误!");
    }
    return res;
  }
}
