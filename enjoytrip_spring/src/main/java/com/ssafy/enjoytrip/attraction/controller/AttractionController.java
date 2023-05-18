package com.ssafy.enjoytrip.attraction.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.service.AttractionService;
import com.ssafy.enjoytrip.location.model.LocationDto;
import com.ssafy.enjoytrip.location.service.LocationService;

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
	
	@GetMapping("/search")
	public ResponseEntity<List<AttractionDto>> getAttrList(@RequestParam("SIDO_NO") int sidoNo, @RequestParam("GUGUN_NO") int gugunNo, @RequestParam("CONTENT_ID") String contentTypeId) throws Exception {
		
		logger.info("관광지 조회 - 호출");
		System.out.println(sidoNo + " " + gugunNo + " "+contentTypeId);
		if(Integer.parseInt(contentTypeId)==0) { // 관광지 유형 선택 안 했으면 ? 해당 지역 관광지 전체보기
			return new ResponseEntity<List<AttractionDto>>(attractionService.attrList(locationService.retLocNo(sidoNo, gugunNo).getLocNo()), HttpStatus.OK);	
		} else { // 관광지 유형 선택 했으면 ? 해당 관광지 유형 보기 
			return new ResponseEntity<List<AttractionDto>>(attractionService.attrListAll(locationService.retLocNo(sidoNo, gugunNo).getLocNo(), Integer.parseInt(contentTypeId)), HttpStatus.OK);					
		}
	}
	
    //@ApiOperation(value = "관광지 상세보기", notes = "contentNo에 해당하는 관광지 상세정보 반환", response = AttractionDto.class)
    @GetMapping("/{contentNo}")
    public ResponseEntity<AttractionDto> getAttraction(@PathVariable("contentNo") int contentNo) throws Exception {
        logger.info("getAttr. - 호출 : " + contentNo);
        return new ResponseEntity<AttractionDto>(attractionService.getAttraction(contentNo), HttpStatus.OK);
    }
	
}
