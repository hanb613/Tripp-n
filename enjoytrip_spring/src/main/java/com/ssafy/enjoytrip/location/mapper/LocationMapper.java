package com.ssafy.enjoytrip.location.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.location.model.LocationDto;

@Mapper
public interface LocationMapper {
	
	List<LocationDto> listSido() throws SQLException;
	List<LocationDto> listGugun(int sidoNo) throws SQLException;
	LocationDto retLocNo(int sidoNo, int gugunNo) throws SQLException;
}
