package com.ssafy.enjoytrip.attraction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.mapper.AttractionMapper;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;

@Service
public class AttractionServiceImpl implements AttractionService{

	private AttractionMapper attractionMapper;
	
	
	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}

	@Override
	public List<AttractionDto> attrList(int locNo) throws Exception {
		return attractionMapper.listAttr(locNo);
	}

	@Override
	public List<AttractionDto> attrListAll(int locNo, int contentTypeId) throws Exception {
		return attractionMapper.listAttrAll(locNo, contentTypeId);
	}
}
