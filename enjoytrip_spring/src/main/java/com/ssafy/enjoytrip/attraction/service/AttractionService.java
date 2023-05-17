package com.ssafy.enjoytrip.attraction.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;

public interface AttractionService {
	
	List<AttractionDto> attrList(int locNo) throws Exception;
	List<AttractionDto> attrListAll(int locNo, int contentTypeId) throws Exception;
}
