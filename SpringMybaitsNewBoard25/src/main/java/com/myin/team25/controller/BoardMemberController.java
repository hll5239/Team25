package com.myin.team25.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myin.team25.domain.BoardVo;

import com.myin.team25.service.BoardMemberInfo;


@Controller
public class BoardMemberController {

	@Autowired
	BoardMemberInfo bmi;
	
	
	@RequestMapping(value="/BoardMemberInfoController")
	public String boardMemberList(Model model){
		
		ArrayList<HashMap <String, Object>> alist = new ArrayList<HashMap <String, Object>>();
		alist= bmi.BoardMemberInfo();
		//alist 주소값 있는지 확인
		System.out.println(alist);
		//alist안에 값 있는지 확인 후 대문자인지 소문자인지 파악
		System.out.println(alist.get(0));
		//alist안에들어있는 값 하나 찍어보기
		System.out.println(alist.get(0).get("BBIDX"));
		model.addAttribute("alist",alist); 
		
		
		return "test/boardMemberInfo";
	}
}
