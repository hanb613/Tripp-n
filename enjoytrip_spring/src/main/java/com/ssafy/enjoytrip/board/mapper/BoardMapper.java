package com.ssafy.enjoytrip.board.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.BoardCommentDto;
import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardLikeDto;
import com.ssafy.enjoytrip.board.model.BoardParameterDto;
import com.ssafy.enjoytrip.board.model.FileInfoBoardDto;

@Mapper
public interface BoardMapper {

	// 게시물
	public int writeArticle(BoardDto boardDto) throws SQLException;
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws SQLException;
	public int getTotalCount(BoardParameterDto boardParameterDto) throws SQLException;
	public BoardDto getArticle(int boardNo) throws SQLException;
	public void updateHit(int boardNo) throws SQLException;
	public int modifyArticle(BoardDto boardDto) throws SQLException;
	public int deleteArticle(int boardNo) throws SQLException;
//	List<FileInfoBoardDto> fileInfoBoardDto(int boardNo) throws Exception;
//	void deleteFile(int boardNo) throws Exception;
//	void registerFile(BoardDto boardDto) throws Exception;

//	// 게시물 댓글
	void deleteComment(int commentNo) throws SQLException;
	int writeComment(BoardCommentDto boardCommentDto) throws SQLException;
	List<BoardCommentDto> listComment(int boardNo) throws SQLException;
//	void modifyComment(BoardDto boardDto) throws SQLException;
//	BoardCommentDto getComment(int commentNo) throws SQLException;
	
//	// 게시물 좋아요
	public List<BoardLikeDto> getLikeList(int boardNo) throws SQLException;
//	int getLikeCount(int boardNo) throws SQLException;
//	void updateLike(int boardNo) throws SQLException;
}