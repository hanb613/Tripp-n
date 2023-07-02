package com.ssafy.enjoytrip.location.service;

import java.util.List;

import com.ssafy.enjoytrip.location.model.LocationDto;

public interface LocationService {
	
	List<LocationDto> sidoList() throws Exception;
	List<LocationDto> gugunList(int sidoNo) throws Exception;
	LocationDto retLocNo(int sidoNo, int gugunNo) throws Exception;

}
