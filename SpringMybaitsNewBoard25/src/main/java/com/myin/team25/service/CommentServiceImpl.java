package com.myin.team25.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myin.team25.domain.CommentVo;
import com.myin.team25.persistence.CommentService_Mapper;

@Service("CommentServiceImpl")
public class CommentServiceImpl implements CommentService {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public ArrayList<CommentVo> SelectCommentAll(int bbidx) {
		
		CommentService_Mapper csm = sqlSession.getMapper(com.myin.team25.persistence.CommentService_Mapper.class);
		ArrayList<CommentVo> av =csm.SelectCommentAll(bbidx);

		 return av;
	}
	
	@Override
	public ArrayList<CommentVo> getCommentMore(int bbidx, int block, int perBlockNum) {
		System.out.println("implµé¾î¿Ó1");
		CommentService_Mapper csm = sqlSession.getMapper(com.myin.team25.persistence.CommentService_Mapper.class);
		ArrayList<CommentVo> av =csm.getCommentMore(bbidx, block,perBlockNum);
		System.out.println("implµé¾î¿Ó2");
		 return av;
	}
	

	@Override
	public int InsertCommentAll(CommentVo cv) {
		System.out.println("impl°ª");
		HashMap <String, Object> map = new HashMap <String, Object>();
		
		map.put("cwriter", cv.getCwriter() );
		System.out.println("writer:"+cv.getCwriter());
		map.put("ccontent", cv.getCcontent() );
		System.out.println("ccontent:"+cv.getCcontent());
		map.put("ip", cv.getIp());
		System.out.println("ip:"+cv.getIp());
		map.put("bbidx", cv.getBbidx() );
		System.out.println("bbidx:"+cv.getBbidx());
		map.put("membermidx", cv.getMemberMidx() );
		System.out.println("membermidx:"+cv.getMemberMidx());
		
		
		CommentService_Mapper csm = sqlSession.getMapper(com.myin.team25.persistence.CommentService_Mapper.class);
		int res=csm.InsertCommentAll(map);
		
		System.out.println("impl°ª2");
		return res;
	}

	@Override
	public int DeleteComment(CommentVo cv) {
		HashMap <String, Object> map = new HashMap <String, Object>();
		
		map.put("ip", cv.getIp());
		map.put("cidx", cv.getCidx());
		map.put("membermidx", cv.getMemberMidx() );
		
		CommentService_Mapper csm = sqlSession.getMapper(com.myin.team25.persistence.CommentService_Mapper.class);
		int res=csm.DeleteComment(map);
		
		return res;
	}



	

}
