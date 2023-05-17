package com.ssafy.enjoytrip.location.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.location.mapper.LocationMapper;
import com.ssafy.enjoytrip.location.model.LocationDto;

@Service
public class LocationServiceImpl implements LocationService {

	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<LocationDto> sidoList() throws Exception {
		return sqlSession.getMapper(LocationMapper.class).listSido();
	}

	@Override
	public List<LocationDto> gugunList(int sidoNo) throws Exception {
		return sqlSession.getMapper(LocationMapper.class).listGugun(sidoNo);
	}

	@Override
	public LocationDto retLocNo(int sidoNo, int gugunNo) throws Exception {
		return sqlSession.getMapper(LocationMapper.class).retLocNo(sidoNo, gugunNo);
	}
}
