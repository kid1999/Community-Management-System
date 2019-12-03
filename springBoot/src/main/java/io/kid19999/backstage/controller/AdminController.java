package io.kid19999.backstage.controller;

import cn.hutool.core.lang.UUID;
import io.kid19999.backstage.dto.Result;
import io.kid19999.backstage.model.Member;
import io.kid19999.backstage.repository.AdminRepository;
import io.kid19999.backstage.repository.MemberRepository;
import io.kid19999.backstage.utils.SomeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

/**
 * @author kid1999
 * @title: managerController
 * @date 2019/11/24 14:21
 */


@RestController
public class AdminController {

	@Autowired
	private SomeUtils someUtils;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private MemberRepository memberRepository;


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
		password = someUtils.md5(username,password);
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try {
			// 3.执行登录方法
			subject.login(token);       // 调用login方法 -> realm 做验证
			String uuid = UUID.fastUUID().toString() + " " +adminRepository.findByAdminId(username).getAdminRole();
			redisTemplate.opsForValue().set(username, uuid);
			System.out.println(uuid);
			result.setStatusCode(1);
			result.setInfo(uuid);
			return result;
		}catch (UnknownAccountException e){
			result.setStatusCode(0);
			result.setInfo("用户名不存在");
			return result;
		}catch (IncorrectCredentialsException e){
			result.setStatusCode(0);
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
	@GetMapping("/member")
    List<Member> getmembers(){
        return memberRepository.findAll();
    }

    @GetMapping("/searchMember")
    List<Member> searchmembers(String search){
		List<Member> res = memberRepository.findMembersByMemNameLike("%" + search + "%");
		res.addAll(memberRepository.findByMemIdLike(search));
        return res;
    }

	/**
	 * 添加会员信息验证
	 * @return
	 */
	@PostMapping("/member")
    Result toAddMember(@RequestBody Member member){
		Result result = new Result();
		Member user = memberRepository.findMemberByMemId(member.getMemId());
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        member.setMemJoinTime(year);
        System.out.println(member);
        memberRepository.save(member);
		if(user == null) {
			result.setStatusCode(1);
			result.setInfo("新建成功！");
		}else{
			result.setStatusCode(1);
			result.setInfo("更新成功！");
		}
		return result;
	}

    /**
     * 删除会员信息
     */
    @DeleteMapping("/member")
    Result deleteMember(@RequestBody Member member){
	    Result result = new Result();
	    String memId = member.getMemId();
	    if(memberRepository.findMemberByMemId(memId) != null){
            memberRepository.deleteByMemId(memId);
            result.setStatusCode(1);
            result.setInfo("删除成功！");
        }else {
	        result.setStatusCode(0);
	        result.setInfo("删除失败！");
        }
	    return result;
    }


	@GetMapping("/logout")
	Result logout(String sessionId){
		System.out.println(sessionId);
		Result result = new Result();
		redisTemplate.opsForHash().delete(sessionId);
		result.setStatusCode(1);
		result.setInfo("logout success");
		return result;
	}


}
