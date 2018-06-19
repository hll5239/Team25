package com.myin.team25.service;

import java.util.ArrayList;

import com.myin.team25.domain.BoardVo;
import com.myin.team25.domain.Criteria;
import com.myin.team25.domain.SearchCriteria;

public interface BoardService {
	
	public ArrayList<BoardVo> SelectBoardAll(SearchCriteria scri);
	
	public int TotalRecordCount(SearchCriteria scri);
	
	public BoardVo SelectBoardOne(int bBidx);
	
	public int InsertBoard(BoardVo vo);
	
	public int UpdateBoard(BoardVo vo);
	
	public int DeleteBoard(BoardVo vo);
	
	public int ReplyBoard(BoardVo vo);
	
	
	
}
