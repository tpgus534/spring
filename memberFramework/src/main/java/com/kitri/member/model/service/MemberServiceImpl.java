package com.kitri.member.model.service;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.kitri.member.model.*;
import com.kitri.member.model.dao.MemberDao;
import com.kitri.member.model.dao.MemberDaoImpl;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public String idCheck(String id) {
		int cnt = memberDao.idCheck(id);
//		System.out.println("cnt : " + cnt);
		JSONObject json = new JSONObject();
		json.put("idcount", cnt);
		return json.toString();
	}
	
	@Override
	public String zipSearch(String doro) {
		List<ZipcodeDto> list = memberDao.zipSearch(doro);
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		for(ZipcodeDto zipDto : list) {
			JSONObject zipcode = new JSONObject();
			zipcode.put("zipcode", zipDto.getZipcode());
			zipcode.put("address", zipDto.getSido()+" "+zipDto.getGugun()+" "+zipDto.getUpmyon()+" "+zipDto.getDoro()+" "+zipDto.getBuildingNumber()+" "+zipDto.getSigugunBuildingName());
			array.put(zipcode);
		}
		jsonObject.put("ziplist", array);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		return memberDao.registerMember(memberDetailDto);
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("userid", id);
//		map.put("userpwd", pass);
		return memberDao.loginMember(map);
	}

	@Override
	public MemberDetailDto getMember(String id) {
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto memberDetailDto) {
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		return 0;
	}

}
