package com.ssafy.enjoytrip.board.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardDto : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
public class BoardDto {

	@ApiModelProperty(value = "게시글 번호")
	private int boardNo;

	@ApiModelProperty(value = "작성자 번호")
	private int userNo;

	@ApiModelProperty(value = "작성자 이름")
	private String userName;
	
	@ApiModelProperty(value = "게시판별 글 번호")
	private int articleNo;

	@ApiModelProperty(value = "게시판 타입")
	private int boardType;

	@ApiModelProperty(value = "좋아요 번호")
	private int likeNo;

	@ApiModelProperty(value = "글 제목")
	private String subject;

	@ApiModelProperty(value = "글 내용")
	private String content;

	@ApiModelProperty(value = "작성일")
	private String createTime;

	@ApiModelProperty(value = "좋아요 누른 한 날짜")
	private String likeTime;
	
	@ApiModelProperty(value = "게시한 사진")
	private List<FileInfoBoardDto> fileInfoBoard;
	
	public int getBoardNo() {
		return boardNo;
	}
	
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	public int getArticleNo() {
		return articleNo;
	}
	
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	
	public int getBoardType() {
		return boardType;
	}
	
	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	
	public int getUserNo() {
		return userNo;
	}
	
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	public int getLikeNo() {
		return likeNo;
	}
	
	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getLikeTime() {
		return likeTime;
	}
	
	public void setLikeTime(String likeTime) {
		this.likeTime = likeTime;
	}
	
	public List<FileInfoBoardDto> getFileInfoBoard() {
		return fileInfoBoard;
	}
	
	public void setFileInfoBoard(List<FileInfoBoardDto> fileInfoBoard) {
		this.fileInfoBoard = fileInfoBoard;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "BoardDto [boardNo=" + boardNo + ", articleNo=" + articleNo + ", boardType=" + boardType + ", userNo="
				+ userNo + ", likeNo=" + likeNo + ", subject=" + subject + ", content=" + content + ", createTime="
				+ createTime + ", likeTime=" + likeTime + ", fileInfoBoard=" + fileInfoBoard + "]";
	}
}