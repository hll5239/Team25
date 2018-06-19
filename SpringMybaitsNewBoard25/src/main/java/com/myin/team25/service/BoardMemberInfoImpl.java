package com.myin.team25.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myin.team25.persistence.BoardService_Mapper;

@Service("BoardMemberInfoImpl")
public class BoardMemberInfoImpl implements BoardMemberInfo {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public ArrayList<HashMap <String, Object>> BoardMemberInfo() {
		
		BoardService_Mapper bsm = sqlSession.getMapper(com.myin.team25.persistence.BoardService_Mapper.class);
				
			ArrayList<HashMap <String, Object>> alist=	bsm.BoardMemberInfo();
		
		return alist;
	}

}
