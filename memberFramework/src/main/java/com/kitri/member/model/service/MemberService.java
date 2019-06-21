package com.kitri.member.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kitri.member.model.*;

public interface MemberService {

	String idCheck(String id);
	String zipSearch(String doro);
	int registerMember(MemberDetailDto memberDetailDto);
	MemberDto loginMember(Map<String, String> map);
	
	MemberDetailDto getMember(String id);
	int modifyMember(MemberDetailDto memberDetailDto);
	int deleteMember(String id);
	
}
