package io.kid19999.backstage.controller;

import io.kid19999.backstage.dto.Login;
import io.kid19999.backstage.dto.Result;
import io.kid19999.backstage.model.Admin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class adminLogin {

  @Value("${Invitation_code}")
  private String Invitation_code;

  @PostMapping("/login")
  Result Login(@RequestBody Login login){
    System.out.println(login);
    Result res = new Result();
    res.setReslut(1);
    res.setInfo("hello wrold");
    return res;
  }

  @PostMapping("/regsiter")
  Result regsiter(@RequestBody Admin admin){
    System.out.println(admin);
    Result res = new Result();
    if(admin.getInvitation_code().equals(Invitation_code)){
      res.setReslut(1);
      res.setInfo(admin.getAdmin_name());
    }else{
      res.setReslut(0);
      res.setInfo("bad code");
    }
    return res;
  }
}
