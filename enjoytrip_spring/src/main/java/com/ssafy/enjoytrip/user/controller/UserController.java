package com.ssafy.enjoytrip.user.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.user.service.UserService;

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
	
//	public String join(UserDto userDto, 
//			@RequestParam("emailid") String emailId, @RequestParam("emaildomain") String emailDomain, 
//			Model model) {
//		try {
//			userDto.setEmail(emailId + "@" + emailDomain);
//			System.out.println(userDto);
//			userService.joinUser(userDto);
//			return "redirect:/";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
//			return "error/error";
//		}
//	}
	
	@GetMapping("/login")
	public String login() {
		return "common/usermodal";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, 
			Model model, HttpSession session, HttpServletResponse response) {
		try {
			UserDto userDto = userService.loginUser(map);
			if(userDto != null) {
				session.setAttribute("userinfo", userDto);
				Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
				cookie.setPath("/board");
				response.addCookie(cookie);
				return "redirect:/";
			}else {
				//로그인 실패
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "/";
			}
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "error/error";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/info")
	public String info() {
		return "userInfo";
	}
}
