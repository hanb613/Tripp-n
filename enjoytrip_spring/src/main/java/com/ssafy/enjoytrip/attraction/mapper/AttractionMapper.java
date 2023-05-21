package com.ssafy.enjoytrip.attraction.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionLikeDto;

@Mapper
public interface AttractionMapper {
	
	List<AttractionDto> listAttr(int locNo) throws SQLException;
	List<AttractionDto> listAttrAll(int locNo, int contentTypeId) throws SQLException;
	AttractionDto getAttraction(int contentNo) throws SQLException;
	int getLikeCount(int contentNo) throws SQLException;
	List<AttractionLikeDto> getLikeList(int contentNo) throws SQLException;
	boolean likeAttraction(AttractionLikeDto attrLikeDto) throws SQLException;
}
