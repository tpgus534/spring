package com.kitri.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kitri.admin.model.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/mvmemberlist.kitri")
	public String memberList() {
		return "/admin/member/memberlist";
	}
	@RequestMapping(value = "/memberlist.kitri", method = RequestMethod.GET)
	@ResponseBody
	public String memberList(@RequestParam Map<String, String> map ) {
		System.out.println("get : "+ map);
		String json = adminService.getMemberList(map);
		return json;
	}
	
}