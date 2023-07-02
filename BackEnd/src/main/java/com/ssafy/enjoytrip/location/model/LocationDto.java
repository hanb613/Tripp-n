package com.ssafy.enjoytrip.location.model;

public class LocationDto {
	
	private int locNo;
	private int gugunNo;
	private String gugunName;
	private int sidoNo;
	private String sidoName;
	private int contentTypeNo;
	
	public int getLocNo() {
		return locNo;
	}
	
	public void setLocNo(int locNo) {
		this.locNo = locNo;
	}
	
	public int getGugunNo() {
		return gugunNo;
	}
	
	public void setGugunNo(int gugunNo) {
		this.gugunNo = gugunNo;
	}
	
	public String getGugunName() {
		return gugunName;
	}
	
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	
	public int getSidoNo() {
		return sidoNo;
	}
	
	public void setSidoNo(int sidoNo) {
		this.sidoNo = sidoNo;
	}
	
	public String getSidoName() {
		return sidoName;
	}
	
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public int getContentTypeNo() {
		return contentTypeNo;
	}

	public void setContentTypeNo(int contentTypeNo) {
		this.contentTypeNo = contentTypeNo;
	}

	@Override
	public String toString() {
		return "LocationDto [locNo=" + locNo + ", gugunNo=" + gugunNo + ", gugunName=" + gugunName + ", sidoNo="
				+ sidoNo + ", sidoName=" + sidoName + ", contentTypeNo=" + contentTypeNo + "]";
	}
}
