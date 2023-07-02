package com.ssafy.enjoytrip.board.model;

public class BoardLikeDto {
	private int likeNo;
	private int userNo;
	private int boardNo;
	private String likeTime;
	
	public int getLikeNo() {
		return likeNo;
	}
	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getLikeTime() {
		return likeTime;
	}
	public void setLikeTime(String likeTime) {
		this.likeTime = likeTime;
	}
	@Override
	public String toString() {
		return "BoardLikeDto [likeNo=" + likeNo + ", userNo=" + userNo + ", boardNo=" + boardNo + ", likeTime="
				+ likeTime + "]";
	}
}
