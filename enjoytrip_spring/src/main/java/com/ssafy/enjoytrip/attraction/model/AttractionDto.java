package com.ssafy.enjoytrip.attraction.model;

public class AttractionDto {
	
	private int contentNo;
	private int contentTypeId;
	private String title;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String tel;
	private String firstImage;
	private String firstImage2;
	private int locNo;
	private double latitude;
	private double longitude;
	private String overview;
	
	public int getContentNo() {
		return contentNo;
	}
	
	public void setContentNo(int contentNo) {
		this.contentNo = contentNo;
	}
	
	public int getContentTypeId() {
		return contentTypeId;
	}
	
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAddr1() {
		return addr1;
	}
	
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	
	public String getAddr2() {
		return addr2;
	}
	
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getFirstImage() {
		return firstImage;
	}
	
	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}
	
	public String getFirstImage2() {
		return firstImage2;
	}
	
	public void setFirstImage2(String firstImage2) {
		this.firstImage2 = firstImage2;
	}
	
	public int getLocNo() {
		return locNo;
	}
	
	public void setLocNo(int locNo) {
		this.locNo = locNo;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getOverview() {
		return overview;
	}
	
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	@Override
	public String toString() {
		return "AttractionDto [contentNo=" + contentNo + ", contentTypeId=" + contentTypeId + ", title=" + title
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", zipcode=" + zipcode + ", tel=" + tel + ", firstImage="
				+ firstImage + ", firstImage2=" + firstImage2 + ", locNo=" + locNo + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", overview=" + overview + "]";
	}
}
