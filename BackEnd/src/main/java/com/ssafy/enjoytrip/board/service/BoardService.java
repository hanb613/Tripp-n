package com.ssafy.enjoytrip.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.BoardCommentDto;
import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardLikeDto;
import com.ssafy.enjoytrip.board.model.BoardParameterDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface BoardService {
	public boolean writeArticle(BoardDto boardDto) throws Exception;
	public Map<String, Object> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;
	
	public BoardDto getArticle(int boardNo) throws Exception;
//	public void updateHit(int boardNo) throws Exception;
	public boolean modifyArticle(BoardDto boardDto) throws Exception;
	public boolean deleteArticle(int boardNo) throws Exception;
	
	/* 게시물 댓글 */
	public boolean writeComment(BoardCommentDto boardCommentDto) throws Exception;
	public List<BoardCommentDto> listComment(int boardNo) throws Exception;
	public boolean deleteComment(int commentNo) throws Exception;
	
	/* 게시물 좋아요 */
	public List<BoardLikeDto> getLikeList(int boardNo) throws Exception;
	public boolean likeBoard(BoardLikeDto boardLikeDto) throws Exception;
	int getLikeCount(int boardNo) throws Exception;

}
