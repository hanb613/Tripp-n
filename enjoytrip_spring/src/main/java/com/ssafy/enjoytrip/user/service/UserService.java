package com.ssafy.enjoytrip.user.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.enjoytrip.user.model.UserDto;

public interface UserService {
	
	public int idCheck(String userId) throws Exception;
	public boolean joinUser(UserDto userDto) throws Exception;
	public UserDto loginUser(UserDto userDto) throws Exception;
	public boolean modifyUser(UserDto userDto) throws Exception;
	
}
