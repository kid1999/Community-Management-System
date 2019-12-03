package io.kid19999.backstage.controller;

import io.kid19999.backstage.dto.Login;
import io.kid19999.backstage.dto.Result;
import io.kid19999.backstage.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kid1999
 * @title: memberController
 * @date 2019/11/24 14:21
 */

@RestController
public class MemberController {
	@Autowired
	private MemberRepository memberRepository;

	/**
	 * 基于对象的传输
	 */

	@PostMapping("/test")
	Result postObj(@RequestBody Login login){
		System.out.println(login);
		Result result = new Result();
		result.setStatusCode(1);
		result.setInfo("post obj success");
		return result;
	}


	@PutMapping("/test")
	Result putObj(@RequestBody Login login){
		System.out.println(login);
		Result result = new Result();
		result.setStatusCode(1);
		result.setInfo("put obj success");
		return result;
	}

	@GetMapping("/test")
	Result getObj(@RequestParam("adminId") String adminId,
				  @RequestParam("adminPwd") String adminPwd){
		System.out.println(adminId + " " + adminPwd);
		Result result = new Result();
		result.setStatusCode(1);
		result.setInfo("get obj success");
		return result;
	}


	@DeleteMapping("/test")
	Result deleteObj(@RequestBody Login login){
		System.out.println(login);
		Result result = new Result();
		result.setStatusCode(1);
		result.setInfo("delete obj success");
		return result;
	}




	/**
	 * 基于表单的传输
	 */
	@PostMapping("/test2")
	Result postForm(@RequestParam("adminId") String adminId,
					@RequestParam("adminPwd") String adminPwd){
		System.out.println(adminId + " " + adminPwd);
		Result result = new Result();
		result.setStatusCode(1);
		result.setInfo("post form success");
		return result;
	}

	@PutMapping("/test2")
	Result putForm(@RequestParam("adminId") String adminId,
				   @RequestParam("adminPwd") String adminPwd){
		System.out.println(adminId + " " + adminPwd);
		Result result = new Result();
		result.setStatusCode(1);
		result.setInfo("put form success");
		return result;
	}

	@DeleteMapping("/test2")
	Result deletForm(@RequestParam("adminId") String adminId,
					 @RequestParam("adminPwd") String adminPwd){
		System.out.println(adminId + " " + adminPwd);
		Result result = new Result();
		result.setStatusCode(1);
		result.setInfo("delete form success");
		return result;
	}


}
