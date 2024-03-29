package com.ssafy.enjoytrip.attraction.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.attraction.model.AttractionCommentDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionLikeDto;
import com.ssafy.enjoytrip.attraction.model.FileInfoAttractionDto;

public interface AttractionService {
	
	List<AttractionDto> attrList(int locNo) throws Exception;
	List<AttractionDto> attrListAll(int locNo, int contentTypeId) throws Exception;
	AttractionDto getAttraction(int contentNo) throws Exception;
	int getLikeCount(int contentNo) throws Exception;
	List<AttractionLikeDto> getLikeList(int contentNo) throws Exception;
	boolean likeAttraction(AttractionLikeDto attrLikeDto) throws Exception;
	List<AttractionDto> getLikeAttrList(int userNo) throws Exception;
	List<Map<String, Object>> getLikeCountBySido() throws Exception;
	List<Map<String, Object>> getRecentLike() throws Exception;
	
	/* 댓글 */
	public boolean writeComment(AttractionCommentDto attractionCommentDto) throws Exception;
	public List<AttractionCommentDto> listComment(int contentNo) throws Exception;
	public boolean deleteComment(int commentNo) throws Exception;
	
	//파일
	List<FileInfoAttractionDto> getFiles(int contentNo) throws Exception;
	boolean setSaveFile(FileInfoAttractionDto fileInfoAttractionDto) throws Exception;
	
}
