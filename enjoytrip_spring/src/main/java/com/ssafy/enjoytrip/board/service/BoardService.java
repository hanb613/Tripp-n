package com.ssafy.enjoytrip.board.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.BoardCommentDto;
import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardParameterDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface BoardService {
	public boolean writeArticle(BoardDto boardDto) throws Exception;
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;
	
	public BoardDto getArticle(int boardNo) throws Exception;
//	public void updateHit(int boardNo) throws Exception;
	public boolean modifyArticle(BoardDto boardDto) throws Exception;
	public boolean deleteArticle(int boardNo) throws Exception;
	
	/* 게시물 댓글 */
	public boolean writeComment(BoardCommentDto boardCommentDto) throws Exception;
	public List<BoardCommentDto> listComment(int boardNo) throws Exception;
	

}
