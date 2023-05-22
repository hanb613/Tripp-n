package com.ssafy.enjoytrip.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.user.service.UserService;

import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/join")
	public String join() {
		return "common/usermodal";
	}
	
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody UserDto userDto) throws Exception {
		logger.info("join - 호출");
		System.out.println(userDto);
		//userDto.setEmail(userDto.getEmail() + "@" + domain);
		//System.out.println(userDto);
		
		if (userService.joinUser(userDto)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<Map<String, Object>> modify(@RequestBody UserDto userDto) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			if (userService.modifyUser(userDto)) {
				System.out.println(userDto);
				resultMap.put("message", "success");
				resultMap.put("userInfo", userDto);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody  UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.loginUser(userDto);
			if (loginUser != null) {
				System.out.println(loginUser);
				resultMap.put("message", "success");
				resultMap.put("userInfo", loginUser);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> logout(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PutMapping("/{userNo}")
	public ResponseEntity<String> deleteUser(@PathVariable("userNo") int userNo) throws Exception {
		logger.info("deleteUser - 호출");
		if (userService.deleteUser(userNo)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/findID")
	public ResponseEntity<Map<String, Object>> findById(@RequestBody UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto findUser = userService.findById(userDto);
			if (findUser != null) {
				resultMap.put("message", "success");
				resultMap.put("userInfo", findUser);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("아이디 찾기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/findPW")
	public ResponseEntity<Map<String, Object>> findByPw(@RequestBody UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto findUser = userService.findByPw(userDto);
			if (findUser != null) {
				System.out.println(findUser);
				resultMap.put("message", "success");
				resultMap.put("userInfo", findUser);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("아이디 찾기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
