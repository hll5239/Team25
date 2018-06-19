package com.myin.team25.service;



import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myin.team25.domain.MemberVo;
import com.myin.team25.service.MemberService;
import com.myin.team25.persistence.MemberService_Mapper;
//»Ð
@Service("MemberServiceImpl")
public class MemberServiceImpl implements MemberService{

	@Autowired
	SqlSession sqlSession;


	@Override
	public ArrayList<MemberVo> selectMemberAll() {
		MemberService_Mapper msm = sqlSession.getMapper(com.myin.team25.persistence.MemberService_Mapper.class);
		ArrayList<MemberVo> av =new ArrayList<MemberVo>();
		 av=msm.selectMemberAll();
		
		return av;
	}


	@Override
	public MemberVo selectMemberOne(String memberId) {
		MemberService_Mapper msm = sqlSession.getMapper(com.myin.team25.persistence.MemberService_Mapper.class);
		MemberVo av =msm.selectMemberOne(memberId);
		 
		return av;
	}


	@Override
	public MemberVo loginCheck(MemberVo mv) {
		MemberVo mc=null;
		HashMap <String, Object> map = new HashMap <String, Object>();
		
		map.put("memberId", mv.getMemberId() );
		map.put("memberPassword", mv.getMemberPassword() );
		
		
		MemberService_Mapper msm = sqlSession.getMapper(com.myin.team25.persistence.MemberService_Mapper.class);
		mc=msm.loginCheck(map);
	
		return mc;
	}


	@Override
	public int insertMember(MemberVo mv) {
		int res=0;
		HashMap <String, Object> map = new HashMap <String, Object>();
		
		map.put("memberMidx", mv.getMemberMidx() );
		map.put("memberId", mv.getMemberId() );
		map.put("memberName", mv.getMemberName() );
		map.put("memberPassword", mv.getMemberPassword() );
		map.put("memberJumin", mv.getMemberJumin() );
		map.put("memberEmail", mv.getMemberEmail() );
		map.put("memberAddr", mv.getMemberAddr() );
		map.put("memberSex", mv.getMemberSex());
		map.put("memberIp", mv.getMemberIp() );
		map.put("bidx", mv.getBidx() );
		
		
		MemberService_Mapper msm = sqlSession.getMapper(com.myin.team25.persistence.MemberService_Mapper.class);
		
		res=msm.insertMember(map);
		
		
		return res;
	}


	@Override
	public int updateMember(MemberVo mv) {
		int res=0;
		HashMap <String, Object> map = new HashMap <String, Object>();
		
		map.put("memberPassword", mv.getMemberPassword() );
		map.put("memberEmail", mv.getMemberEmail() );
		map.put("memberAddr", mv.getMemberAddr() );
		map.put("memberIp", mv.getMemberIp() );
		map.put("bidx", mv.getBidx() );
		map.put("memberMidx", mv.getMemberMidx() );
		
		MemberService_Mapper msm = sqlSession.getMapper(com.myin.team25.persistence.MemberService_Mapper.class);
		
		res=msm.updateMember(map);
		
		
		
		return res;
	}

	
	@Override
	public int maxMember() {

	
		MemberService_Mapper msm = sqlSession.getMapper(com.myin.team25.persistence.MemberService_Mapper.class);
		
		int av =msm.maxMember();
	
		
		
		return av;
	}


	@Override
	public int keepLogin(int memberMidx, String sessionkey, String sessionLimit) {
		HashMap <String, Object> map = new HashMap <String, Object>();
		
		map.put("memberMidx", memberMidx );
		map.put("sessionkey", sessionkey );
		map.put("sessionLimit", sessionLimit );
		
		MemberService_Mapper msm = sqlSession.getMapper(com.myin.team25.persistence.MemberService_Mapper.class);
		int mv= msm.keepLogin(map);
		
		return mv;
	}


	@Override
	public MemberVo checkAutoLogin(String sessionkey) {
		MemberService_Mapper msm = sqlSession.getMapper(com.myin.team25.persistence.MemberService_Mapper.class);
		MemberVo mv= msm.checkAutoLogin(sessionkey);
		
		return mv;
	}

}
