package com.ssafy.enjoytrip.attraction.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionLikeDto;

public interface AttractionService {
	
	List<AttractionDto> attrList(int locNo) throws Exception;
	List<AttractionDto> attrListAll(int locNo, int contentTypeId) throws Exception;
	AttractionDto getAttraction(int contentNo) throws Exception;
	int getLikeCount(int contentNo) throws Exception;
	List<AttractionLikeDto> getLikeList(int contentNo) throws Exception;
	boolean likeAttraction(AttractionLikeDto attrLikeDto) throws Exception;
}
