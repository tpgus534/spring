package com.kitri.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.service.MemberService;

@Controller
@RequestMapping("/user")
@SessionAttributes("userInfo")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	//@Qualifier("Impl1")
	private MemberService memberService;
	
	@RequestMapping(value = "/register.kitri",method = RequestMethod.GET)
	public String register() {
		return "user/member/member";
	}
	@RequestMapping(value = "/register.kitri",method = RequestMethod.POST)
	public String register(MemberDetailDto memberDetailDto , Model model) {
		int cnt = memberService.registerMember(memberDetailDto);
		if (cnt != 0) {
			model.addAttribute("registerInfo", memberDetailDto);
			return "user/member/registerok";
		} else {
			return "user/member/registerfail";			
		}
	}
	@RequestMapping(value = "/idcheck.kitri",method = RequestMethod.GET)
	//@ResponseBody 이렇게 해도됨
	public @ResponseBody String idcheck(@RequestParam(name = "checkid") String id) {
		String json = memberService.idCheck(id);
		return json;
	}
	
	@RequestMapping(value = "/zipsearch.kitri",method = RequestMethod.GET)
	@ResponseBody
	public String zipSearch(@RequestParam(name = "doro") String doro) {
		String json = memberService.zipSearch(doro);
		return json;
	}
	
	@RequestMapping(value = "/login.kitri" , method = RequestMethod.GET)
	public String login() {
		return "user/login/login";
	}
//	@RequestMapping(value = "/login.kitri" ,method = RequestMethod.POST)
//	public String login(@RequestParam("id") String id, @RequestParam("pass") String pass, HttpSession session) {
//		MemberDto memberDto = memberService.loginMember(id, pass);
//		if (memberDto != null) {
//			session.setAttribute("userInfo", memberDto);
//			return "user/login/loginok";
//		} else {
//			return "user/login/loginfail";
//		}
//	}
	
	@RequestMapping(value = "/login.kitri" ,method = RequestMethod.POST)
	public String login(@RequestParam Map<String, String> map, Model model) {
		MemberDto memberDto = memberService.loginMember(map);
		if (memberDto != null) {
			model.addAttribute("userInfo",memberDto);
			return "user/login/loginok";
		} else {
			return "user/login/loginfail";
		}
	}
	
	@RequestMapping("/logout.kitri")
	public String logout(@ModelAttribute("userInfo") MemberDto memberDto , SessionStatus sessionStatus) {
			sessionStatus.setComplete();
			return "redirect:/index.jsp";
		
	}
}