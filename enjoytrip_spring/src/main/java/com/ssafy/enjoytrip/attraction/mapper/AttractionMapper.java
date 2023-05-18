package com.ssafy.enjoytrip.attraction.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;

@Mapper
public interface AttractionMapper {
	
	List<AttractionDto> listAttr(int locNo) throws SQLException;
	List<AttractionDto> listAttrAll(int locNo, int contentTypeId) throws SQLException;
	AttractionDto getAttraction(int contentNo) throws SQLException;
}
