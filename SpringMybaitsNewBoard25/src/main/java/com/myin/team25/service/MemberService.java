package com.myin.team25.service;

import java.util.ArrayList;


import com.myin.team25.domain.*;



public interface MemberService {

	public ArrayList<MemberVo> selectMemberAll();
	
	public int maxMember();
	
	public MemberVo selectMemberOne(String memberId);
	
	public MemberVo loginCheck(MemberVo mv);
	
	public int keepLogin(int memberMidx, String sessionkey, String sessionLimit);
	
	public MemberVo checkAutoLogin(String sessionkey);
	
	public int insertMember(MemberVo mv);
	
	public int updateMember(MemberVo mv);
}
