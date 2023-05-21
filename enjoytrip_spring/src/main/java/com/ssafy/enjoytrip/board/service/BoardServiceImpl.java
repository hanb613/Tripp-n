package com.ssafy.enjoytrip.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.board.mapper.BoardMapper;
import com.ssafy.enjoytrip.board.model.BoardCommentDto;
import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.BoardLikeDto;
import com.ssafy.enjoytrip.board.model.BoardParameterDto;
import com.ssafy.enjoytrip.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeArticle(BoardDto boardDto) throws Exception {
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeArticle(boardDto) == 1;
	}

	@Override
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(BoardMapper.class).listArticle(boardParameterDto);
	}

	@Override
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(BoardMapper.class).getTotalCount(boardParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public BoardDto getArticle(int boardNo) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getArticle(boardNo);
	}
	
//	@Override
//	public void updateHit(int boardNo) throws Exception {
//		sqlSession.getMapper(BoardMapper.class).updateHit(boardNo);
//	}

	@Override
	@Transactional
	public boolean modifyArticle(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).modifyArticle(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int boardNo) throws Exception {
		sqlSession.getMapper(BoardMapper.class).deleteComment(boardNo);
		return sqlSession.getMapper(BoardMapper.class).deleteArticle(boardNo) == 1;
	}
	
	/* 게시물 댓글 */
	@Override
	public boolean writeComment(BoardCommentDto boardCommentDto) throws Exception {
		if(boardCommentDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeComment(boardCommentDto) == 1;
	}
	
	@Override
	public List<BoardCommentDto> listComment(int boardNo) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).listComment(boardNo);
	}

	@Override
	public List<BoardLikeDto> getLikeList(int boardNo) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getLikeList(boardNo);
	}
}