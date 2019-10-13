package io.kid19999.backstage.controller;

import io.kid19999.backstage.mapper.adminMapper;
import io.kid19999.backstage.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userShow {

  @Autowired
  private adminMapper adminMapper;

  @Autowired
  private userMapper userMapper;



}
