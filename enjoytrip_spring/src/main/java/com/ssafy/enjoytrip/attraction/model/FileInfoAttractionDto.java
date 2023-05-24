package com.ssafy.enjoytrip.attraction.model;

public class FileInfoAttractionDto {
	
	private int fileNo;
	private int favoriteNo;
	private String saveFolder;
	private String originalFile;
	private String saveFile;
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getFavoriteNo() {
		return favoriteNo;
	}
	public void setFavoriteNo(int favoriteNo) {
		this.favoriteNo = favoriteNo;
	}
	public String getSaveFolder() {
		return saveFolder;
	}
	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}
	public String getOriginalFile() {
		return originalFile;
	}
	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}
	public String getSaveFile() {
		return saveFile;
	}
	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}
	@Override
	public String toString() {
		return "FileInfoAttrDto [fileNo=" + fileNo + ", favoriteNo=" + favoriteNo + ", saveFolder=" + saveFolder
				+ ", originalFile=" + originalFile + ", saveFile=" + saveFile + "]";
	}
	
}
