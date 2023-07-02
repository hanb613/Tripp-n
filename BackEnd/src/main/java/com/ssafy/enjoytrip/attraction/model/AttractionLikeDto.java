package com.ssafy.enjoytrip.attraction.model;

public class AttractionLikeDto {
	private int favoriteNo;
	private int contentNo;
	private int userNo;
	private String registDate;
	public int getFavoriteNo() {
		return favoriteNo;
	}
	public void setFavoriteNo(int favoriteNo) {
		this.favoriteNo = favoriteNo;
	}
	public int getContentNo() {
		return contentNo;
	}
	public void setContentNo(int contentNo) {
		this.contentNo = contentNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	@Override
	public String toString() {
		return "AttractionLikeDto [favoriteNo=" + favoriteNo + ", contentNo=" + contentNo + ", userNo=" + userNo
				+ ", registDate=" + registDate + "]";
	}
	
}
