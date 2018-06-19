package com.myin.team25.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myin.team25.domain.BoardVo;
import com.myin.team25.domain.Criteria;
import com.myin.team25.domain.SearchCriteria;
import com.myin.team25.service.BoardService;
import com.myin.team25.persistence.BoardService_Mapper;


@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService{

	
	
	@Autowired
	SqlSession sqlSession;

	public SqlSession test(){
		System.out.println("sqlSession:"+sqlSession);
		//mapper를 가져오는데 boardservice_mapper 클래스를 가져오려고한다 
				//sqlsession안의 sqlsessionfactory에 있는 것들과 일치가 되어야 한다.
		return sqlSession;
	}

	

	@Override
	public ArrayList<BoardVo> SelectBoardAll(SearchCriteria scri) {
	
		BoardService_Mapper bsm = sqlSession.getMapper(com.myin.team25.persistence.BoardService_Mapper.class);
		ArrayList<BoardVo> av =new ArrayList<BoardVo>();
		 av=bsm.SelectBoardAll(scri);
		
		return av;
	}



	@Override
	public BoardVo SelectBoardOne(int bBidx) {
		
		BoardService_Mapper bsm = sqlSession.getMapper(com.myin.team25.persistence.BoardService_Mapper.class);
		BoardVo bv=bsm.SelectBoardOne(bBidx);

		return bv;
	}



	@Override
	public int InsertBoard(BoardVo bv) {
		
		
		HashMap <String, Object> map = new HashMap <String, Object>();
		
		map.put("memberMidx", bv.getMemberMidx() );
		map.put("subject", bv.getSubject() );
		map.put("content", bv.getContent() );
		map.put("writer", bv.getWriter() );
		map.put("password", bv.getPassword() );
		map.put("ip", bv.getIp() );
		map.put("filename", bv.getFilename());
		
		System.out.println("값들?"+bv.getMemberMidx()+bv.getSubject()+ bv.getContent()+bv.getWriter()+bv.getPassword()+bv.getIp());
		BoardService_Mapper bsm = sqlSession.getMapper(com.myin.team25.persistence.BoardService_Mapper.class);
		int res=bsm.InsertBoard(map);
		
		return res;
	}



	@Override
	public int UpdateBoard(BoardVo bv) {
		HashMap <String, Object> map = new HashMap <String, Object>();
		
		map.put("subject", bv.getSubject() );
		map.put("content", bv.getContent() );
		map.put("ip", bv.getIp() );
		map.put("bBidx", bv.getbBidx());
		map.put("memberMidx", bv.getMemberMidx() );
		map.put("password", bv.getPassword() );
		
		
		BoardService_Mapper bsm = sqlSession.getMapper(com.myin.team25.persistence.BoardService_Mapper.class);
		int res=bsm.UpdateBoard(map);
		
		return res;
	}



	@Override
	public int DeleteBoard(BoardVo bv) {
		HashMap <String, Object> map = new HashMap <String, Object>();
		
		map.put("ip", bv.getIp() );
		map.put("bBidx", bv.getbBidx());
		map.put("password", bv.getPassword() );
		map.put("memberMidx", bv.getMemberMidx() );
		
		BoardService_Mapper bsm = sqlSession.getMapper(com.myin.team25.persistence.BoardService_Mapper.class);
		int res=bsm.DeleteBoard(map);
		
		return res;
	}




	@Transactional
	@Override
	public int ReplyBoard(BoardVo bv) {
		HashMap <String, Object> map = new HashMap <String, Object>();
		
		map.put("memberMidx", bv.getMemberMidx() );
		map.put("subject", bv.getSubject() );
		map.put("content", bv.getContent() );
		map.put("writer", bv.getWriter() );
		map.put("password", bv.getPassword() );
		map.put("ip", bv.getIp() );
		map.put("oidx", bv.getOidx());
		map.put("updown", bv.getUpdown());
		map.put("leftright", bv.getLeftright());
		
		
		BoardService_Mapper bsm = sqlSession.getMapper(com.myin.team25.persistence.BoardService_Mapper.class);
		
		bsm.ReplyUpdateBoard(map);
		int res2=bsm.ReplyInsertBoard(map);
		
		
		
		
		
		return res2;
	}



	@Override 
	public int TotalRecordCount(SearchCriteria scri) {
		BoardService_Mapper bsm = sqlSession.getMapper(com.myin.team25.persistence.BoardService_Mapper.class);
		int cnt =bsm.TotalRecordCount(scri);
		
		return cnt;
	}






	
	
}
