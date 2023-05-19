package com.ssafy.enjoytrip.user.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.UserDto;

@Mapper
public interface UserMapper {
	
	public int idCheck(String userId) throws SQLException;
	public int joinUser(UserDto userDto) throws SQLException;
	public UserDto loginUser(UserDto userDto) throws SQLException;
	
	//관리자기능-보류
}
