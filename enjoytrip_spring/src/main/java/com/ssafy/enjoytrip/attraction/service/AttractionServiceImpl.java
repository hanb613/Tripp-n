package com.ssafy.enjoytrip.attraction.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.mapper.AttractionMapper;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionLikeDto;
import com.ssafy.enjoytrip.location.mapper.LocationMapper;

@Service
public class AttractionServiceImpl implements AttractionService{

	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public List<AttractionDto> attrList(int locNo) throws Exception {
		return sqlSession.getMapper(AttractionMapper.class).listAttr(locNo);
	}

	@Override
	public List<AttractionDto> attrListAll(int locNo, int contentTypeId) throws Exception {
		return sqlSession.getMapper(AttractionMapper.class).listAttrAll(locNo, contentTypeId);
	}

	@Override
	public AttractionDto getAttraction(int contentNo) throws Exception {
		return sqlSession.getMapper(AttractionMapper.class).getAttraction(contentNo);
	}

	@Override
	public int getLikeCount(int contentNo) throws Exception {
		return sqlSession.getMapper(AttractionMapper.class).getLikeCount(contentNo);
	}

	@Override
	public List<AttractionLikeDto> getLikeList(int contentNo) throws Exception {
		return sqlSession.getMapper(AttractionMapper.class).getLikeList(contentNo);
	}

	@Override
	public boolean likeAttraction(AttractionLikeDto attrLikeDto) throws Exception {
		return sqlSession.getMapper(AttractionMapper.class).likeAttraction(attrLikeDto);
	}
	
	
}
