package com.ssafy.enjoytrip.user.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.mapper.UserMapper;
import com.ssafy.enjoytrip.user.model.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private UserMapper userMapper;
	
	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return userMapper.idCheck(userId);
	}

	@Override
	public boolean joinUser(UserDto userDto) throws Exception {
		String email = userDto.getEmail();
		// String domain = ;
		//if(domain=="null") throw new Exception();
		if(userDto.getId() == null || userDto.getName() == null || userDto.getPassword() == null || userDto.getAge()<0 ||  (userDto.getGender()!='F' && userDto.getGender()!='M')) {
			throw new Exception();
		}
		return sqlSession.getMapper(UserMapper.class).joinUser(userDto) == 1;
	}

	@Override
	public UserDto loginUser(UserDto userDto) throws Exception {
		if (userDto.getId() == null || userDto.getPassword() == null)
			return null;
		return sqlSession.getMapper(UserMapper.class).loginUser(userDto);
	}
	
	@Override
	public boolean modifyUser(UserDto userDto) throws Exception {
		String email = userDto.getEmail();
		// String domain = ;
		//if(domain=="null") throw new Exception();
		if(userDto.getPassword() == null || userDto.getAge()<0 || (userDto.getGender()!='F' && userDto.getGender()!='M')) {
			throw new Exception();
		}
		return sqlSession.getMapper(UserMapper.class).modifyUser(userDto) == 1;
	}

}
