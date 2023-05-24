package com.ssafy.enjoytrip.attraction.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.AttractionCommentDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionLikeDto;
import com.ssafy.enjoytrip.attraction.model.FileInfoAttractionDto;

@Mapper
public interface AttractionMapper {
	
	List<AttractionDto> listAttr(int locNo) throws SQLException;
	List<AttractionDto> listAttrAll(int locNo, int contentTypeId) throws SQLException;
	AttractionDto getAttraction(int contentNo) throws SQLException;
	int getLikeCount(int contentNo) throws SQLException;
	List<AttractionLikeDto> getLikeList(int contentNo) throws SQLException;
	boolean likeAttraction(AttractionLikeDto attrLikeDto) throws SQLException;
	List<AttractionDto> getLikeAttrList(int userNo) throws SQLException;
	List<Map<String, Object>> getLikeCountBySido() throws SQLException;
	List<Map<String, Object>> getRecentLike() throws SQLException;
	
	// 댓글
	int writeComment(AttractionCommentDto attractionCommentDto) throws SQLException;
	List<AttractionCommentDto> listComment(int contentNo) throws SQLException;
	int deleteComment(int commentNo) throws SQLException;
	//파일
	List<FileInfoAttractionDto> getFiles(int contentNo) throws SQLException;
	int setSaveFile(FileInfoAttractionDto fileInfoAttractionDto) throws SQLException;
}
