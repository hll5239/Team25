package com.myin.team25.persistence;

import java.util.ArrayList;
import java.util.HashMap;

import com.myin.team25.domain.BoardVo;
import com.myin.team25.domain.SearchCriteria;


public interface BoardService_Mapper {


	public ArrayList<BoardVo> SelectBoardAll(SearchCriteria scri);
	
	public int TotalRecordCount(SearchCriteria scri);
	
	public BoardVo SelectBoardOne(int bBidx);
	
	public int InsertBoard(HashMap <String, Object> map);
	
	public int UpdateBoard(HashMap <String, Object> map);
	
	public int DeleteBoard(HashMap <String, Object> map);
	
	public int ReplyUpdateBoard(HashMap <String, Object> map);
	
	public int ReplyInsertBoard(HashMap <String, Object> map);
	
	public ArrayList<HashMap <String, Object>> BoardMemberInfo();
	
}
