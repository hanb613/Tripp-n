package com.ssafy.enjoytrip.attraction.controller;

import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.service.AttractionService;
import com.ssafy.enjoytrip.location.model.LocationDto;
import com.ssafy.enjoytrip.location.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/attraction")
public class AttractionController {

	private final Logger logger = LoggerFactory.getLogger(AttractionController.class);
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private AttractionService attractionService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<LocationDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<LocationDto>>(locationService.sidoList(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<LocationDto>> gugun(
			@RequestParam("sidoNo") int sidoNo) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<LocationDto>>(locationService.gugunList(sidoNo), HttpStatus.OK);
	}
	
//	@GetMapping("/search")
//	@ResponseBody
//	public List<AttractionDto> getAttrList(@RequestHeader int sidoNo, 
//								@RequestHeader int gugunNo, 
//								@RequestHeader int contentTypeId, 
//								Model model) throws Exception {
//		
//		List<AttractionDto> result;
//		if(contentTypeId==0) { // 관광지 유형 선택 안 했으면 ? 해당 지역 관광지 전체보기
//			result = attractionService.attrList(locationService.retLocNo(sidoNo, gugunNo).getLocNo());			
//		} else { // 관광지 유형 선택 했으면 ? 해당 관광지 유형 보기 
//			result = attractionService.attrListAll(locationService.retLocNo(sidoNo, gugunNo).getLocNo(), contentTypeId);						
//		}
//		return result;
//	}
	
}
