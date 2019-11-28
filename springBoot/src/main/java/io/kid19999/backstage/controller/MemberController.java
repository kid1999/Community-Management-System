package io.kid19999.backstage.controller;

import io.kid19999.backstage.repository.MemberRepository;
import io.kid19999.backstage.repository.SignLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kid1999
 * @title: memberController
 * @date 2019/11/24 14:21
 */

@Controller
public class MemberController {
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private SignLogRepository signLogRepository;

	@GetMapping("/")
    String Home(){
		return "Index";
	}

	@GetMapping("/test")
    String Test(){
		return "test";
	}

}
