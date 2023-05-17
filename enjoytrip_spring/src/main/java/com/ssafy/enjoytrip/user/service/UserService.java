package com.ssafy.enjoytrip.user.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.enjoytrip.user.model.UserDto;

public interface UserService {
	int idCheck(String userId) throws Exception;
	void joinUser(UserDto userDto) throws Exception;
	UserDto loginUser(Map<String, String> map) throws Exception;
}
