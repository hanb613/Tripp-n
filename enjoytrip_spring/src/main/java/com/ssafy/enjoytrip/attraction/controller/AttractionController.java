package com.ssafy.enjoytrip.attraction.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.service.AttractionService;
import com.ssafy.enjoytrip.location.model.LocationDto;
import com.ssafy.enjoytrip.location.service.LocationService;
import com.ssafy.enjoytrip.location.service.LocationServiceImpl;

@Controller
@RequestMapping("/attraction")
public class AttractionController {

	private final Logger logger = LoggerFactory.getLogger(AttractionController.class);
	
	private LocationService locationService;
	private AttractionService attractionService;
	
	public AttractionController(LocationService locationService, AttractionService attractionService) {
		super();
		this.locationService = locationService;
		this.attractionService = attractionService;
	}

	@GetMapping("/sido")
	public ResponseEntity<List<LocationDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<LocationDto>>(locationService.sidoList(), HttpStatus.OK);
	}
	
	@GetMapping("/getGugun/{sidoNo}")
	@ResponseBody
	public List<LocationDto> getGugun(@PathVariable int sidoNo) throws Exception {
		
		// 구군 정보 받아오기
		List<LocationDto> list = locationService.gugunList(sidoNo);

		return list;
	}
	
	@GetMapping("/search")
	@ResponseBody
	public List<AttractionDto> getAttrList(@RequestHeader int sidoNo, 
								@RequestHeader int gugunNo, 
								@RequestHeader int contentTypeId, 
								Model model) throws Exception {
		
		List<AttractionDto> result;
		if(contentTypeId==0) { // 관광지 유형 선택 안 했으면 ? 해당 지역 관광지 전체보기
			result = attractionService.attrList(locationService.retLocNo(sidoNo, gugunNo).getLocNo());			
		} else { // 관광지 유형 선택 했으면 ? 해당 관광지 유형 보기 
			result = attractionService.attrListAll(locationService.retLocNo(sidoNo, gugunNo).getLocNo(), contentTypeId);						
		}
		return result;
	}
	
}
