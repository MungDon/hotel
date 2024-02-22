package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.request.user.ReqUserAdd;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("") // RequsetMapping에 /user를 써놓은것을 그대로 사용
	public String userForm() { //회원가입 폼 페이지를 반환하는 메서드
		return "join";
	}
	
	@PostMapping("")// RequsetMapping에 /user를 써놓은것을 그대로 사용
	public String userAdd(ReqUserAdd add) {
		userService.userAdd(add);
		return "redirect:/user/login";
	}



}
