package com.ssafy.enjoytrip.user.model;

public class UserDto {
	private int userNo;
	private String id;
	private String name;
	private String password;
	private String email;
	private int locNo;
	private int age;
	private char gender;
	private String joinDate;
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLocNo() {
		return locNo;
	}
	public void setLocNo(int locNo) {
		this.locNo = locNo;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "UserDto [userNo=" + userNo + ", id=" + id + ", name=" + name + ", password=" + password + ", email="
				+ email + ", locNo=" + locNo + ", age=" + age + ", gender=" + gender + ", joinDate=" + joinDate + "]";
	}
}
