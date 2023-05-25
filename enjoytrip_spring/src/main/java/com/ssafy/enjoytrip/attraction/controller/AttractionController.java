package com.ssafy.enjoytrip.attraction.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.enjoytrip.attraction.model.AttractionCommentDto;
import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.AttractionLikeDto;
import com.ssafy.enjoytrip.attraction.model.FileInfoAttractionDto;
import com.ssafy.enjoytrip.attraction.service.AttractionService;
import com.ssafy.enjoytrip.location.model.LocationDto;
import com.ssafy.enjoytrip.location.service.LocationService;

@RestController
@RequestMapping("/attraction")
public class AttractionController {

	private final Logger logger = LoggerFactory.getLogger(AttractionController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Value("${file.path}")
	private String uploadPath;

	@Value("${file.imgPath}")
	private String uploadImgPath;

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
        resultMap.put("files", attractionService.getFiles(contentNo));
        
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
	
	//파일업로드
	@PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadFile(FileInfoAttractionDto fileInfoAttractionDto, @RequestParam("file") MultipartFile files, @RequestParam("likeNo") int likeNo) throws Exception {
		logger.debug("uploadFile : {}", fileInfoAttractionDto);
		
//		FileUpload 관련 설정.
		logger.debug("MultipartFile.isEmpty : {}", files);
		
		//resources에 저장하자
	    String rootPath = System.getProperty("user.dir");
	    String fileDir = rootPath + File.separator+"resources";
	    
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = fileDir + File.separator + today;
		logger.debug("저장 폴더 : {}", saveFolder);
		File folder = new File(saveFolder);
		if (!folder.exists())
			folder.mkdirs();

		FileInfoAttractionDto fileInfoDto = new FileInfoAttractionDto();
		String originalFileName = files.getOriginalFilename();
		if (!originalFileName.isEmpty()) {
			String saveFileName = UUID.randomUUID().toString()
					+ originalFileName.substring(originalFileName.lastIndexOf('.'));
			fileInfoDto.setFavoriteNo(likeNo);
			fileInfoDto.setSaveFolder(today);
			fileInfoDto.setOriginalFile(originalFileName);
			fileInfoDto.setSaveFile(saveFileName);
			logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", files.getOriginalFilename(), saveFileName);
			files.transferTo(new File(folder, saveFileName));
			
			
			attractionService.setSaveFile(fileInfoDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/download/{sfolder}/{ofile}/{sfile}")
	public ResponseEntity<Object> download(@PathVariable("sfolder") String sfolder, @PathVariable("ofile") String ofile,
			@PathVariable("sfile") String sfile) {
		logger.debug("download file info sfolder : {}, ofile : {}, sfile : {}", sfolder, ofile, sfile);
		String file = uploadPath + File.separator + sfolder + File.separator + sfile;

		try {
			Path filePath = Paths.get(file);
			Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기

			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(URLEncoder.encode(ofile, "UTF-8").replaceAll("\\+", "%20")).build());
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}
	@GetMapping("/showImage")
    public ResponseEntity<Resource> showImage(@RequestParam Map<String, String> param) {
        // 사진이 저장된 폴더 경로 변수 선언
        String imageRoot = "";
        String saveFolder = param.get("saveFolder");
        String saveFile = param.get("saveFile");
        imageRoot = new File("").getAbsolutePath() + "/resources/"+saveFolder+"/"+saveFile;
        System.out.println("showimage!!!!");
        logger.info("보여줄 파일: " + imageRoot);
        //localhost:8080/attraction/showImage?saveFolder=230525&saveFile=49e174a3-f227-4119-86b9-4b61dd9c3522.jpg
        // Resorce를 사용해서 로컬 서버에 저장된 이미지 경로 및 파일 명을 지정
        Resource resource = new FileSystemResource(imageRoot);

        
        // 로컬 서버에 저장된 이미지 파일이 없을 경우
        if(!resource.exists()){
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND); // 리턴 결과 반환 404
        }

     // 로컬 서버에 저장된 이미지가 있는 경우 로직 처리
        HttpHeaders header = new HttpHeaders();
        Path filePath = null;
        try {
            filePath = Paths.get(imageRoot);
            // 인풋으로 들어온 파일명 .png / .jpg 에 맞게 헤더 타입 설정
            header.add("Content-Type", Files.probeContentType(filePath));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // 이미지 리턴 실시 [브라우저에서 get 주소 확인 가능]
        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }
}
