package io.kid19999.backstage.controller;

import io.kid19999.backstage.dto.Result;
import io.kid19999.backstage.mapper.adminMapper;
import io.kid19999.backstage.mapper.userMapper;
import io.kid19999.backstage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

@RestController
public class userShow {

  @Autowired
  private adminMapper adminMapper;

  @Autowired
  private userMapper userMapper;

  @PostMapping("/editUser")
  Result editUser(@RequestBody User user){
    System.out.println(user);
    Date d = new Date();
    Timestamp date = new Timestamp(d.getTime());
    user.setUserCreateTime(date);
    User u = userMapper.selectById(user.getUserId());
    Result res = new Result();
    res.setReslut(1);
    if(u == null){
      // add
      userMapper.insert(user);
      res.setInfo("添加成功!");
    }else {
      userMapper.updateById(user);
      res.setInfo("更新成功!");
    }
    return res;
  }

}
