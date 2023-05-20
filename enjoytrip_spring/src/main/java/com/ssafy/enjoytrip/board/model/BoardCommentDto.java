package com.ssafy.enjoytrip.board.model;

public class BoardCommentDto {

	private int commentNo;
	private int boardNo;
	private int userNo;
	private String content;
	private String createTime;
	private String userName;
	
	public int getCommentNo() {
		return commentNo;
	}
	
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	public int getUserNo() {
		return userNo;
	}
	
	public void setUserNo(int userNo) {
		this.userNo = userNo;
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
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "BoardCommentDto [commentNo=" + commentNo + ", boardNo=" + boardNo + ", userNo=" + userNo + ", content="
				+ content + ", createTime=" + createTime + "]";
	}
}
