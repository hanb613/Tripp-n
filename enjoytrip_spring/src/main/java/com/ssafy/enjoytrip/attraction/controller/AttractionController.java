package com.ssafy.enjoytrip.attraction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.AttractionCommentDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionLikeDto;
import com.ssafy.enjoytrip.attraction.service.AttractionService;
import com.ssafy.enjoytrip.board.model.BoardCommentDto;
import com.ssafy.enjoytrip.location.model.LocationDto;
import com.ssafy.enjoytrip.location.service.LocationService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/attraction")
public class AttractionController {

	private final Logger logger = LoggerFactory.getLogger(AttractionController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
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
    public ResponseEntity<Map<String, Object>> getAttraction(@PathVariable("contentNo") int contentNo) throws Exception {
    	Map<String, Object> resultMap = new HashMap<>();
        logger.info("getAttr. - 호출 : " + contentNo);
        
        resultMap.put("attraction", attractionService.getAttraction(contentNo));
        resultMap.put("likedUsers", attractionService.getLikeList(contentNo));
        
        //return new ResponseEntity<AttractionDto>(attractionService.getAttraction(contentNo), HttpStatus.OK);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
	
    @PostMapping("/like")
    public ResponseEntity<String> likeAttraction(@RequestBody AttractionLikeDto attrLikeDto) throws Exception {
		logger.info("likeAttraction - 호출: "+attrLikeDto.getUserNo()+" liked "+ attrLikeDto.getContentNo());
		if (attractionService.likeAttraction(attrLikeDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    
    //차트
    @GetMapping("/chart")
    public List<Map<String, Object>> getLikeCountBySido() throws Exception {
    	logger.info("chart - 호출");
    	List<Map<String, Object>> result = attractionService.getLikeCountBySido();
    	System.out.println(result);
    	return result;
    }
    
    //Carousel용 최근 좋아요
    @GetMapping("/recent")
    public List<Map<String, Object>> getRecentLike() throws Exception{
    	logger.info("recent - 호출");
    	List<Map<String, Object>> result = attractionService.getRecentLike();
    	System.out.println(result);
    	return result;
    }

    @PostMapping("/comment")
	public ResponseEntity<String> writeComment(@RequestBody AttractionCommentDto attractionCommentDto) throws Exception {
		logger.info("writeComment - 호출");
		if (attractionService.writeComment(attractionCommentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/comment/{contentNo}")
	public ResponseEntity<List<AttractionCommentDto>> listComment(@PathVariable("contentNo") int contentNo) throws Exception {
		logger.info("listComment - 호출");
		System.out.println(attractionService.listComment(contentNo));
		return new ResponseEntity<List<AttractionCommentDto>>(attractionService.listComment(contentNo), HttpStatus.OK);
	}
	
	@DeleteMapping("/comment/{commentNo}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentNo") int commentNo) throws Exception {
		logger.info("deleteComment - 호출");
		if (attractionService.deleteComment(commentNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
