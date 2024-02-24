package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.request.user.ReqUserAdd;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("") // RequsetMapping에 /user 를 써놓은것을 그대로 사용
	public String userForm() { //회원가입 폼 페이지를 반환하는 메서드
		return "join";
	}
	
	@PostMapping("")// RequsetMapping에 /user 를 써놓은것을 그대로 사용
	public String userAdd(ReqUserAdd add) {
		userService.userAdd(add);						// 사용자의 회원가입 입력데이터를 서비스에 넘겨줌 
		return "redirect:/user/login";					// 입력데이터 insert 성공 시 = 회원가입 성공 시 로그인 페이지로 리다이렉트
	}

	@GetMapping("/error")
	public String errorForm(@RequestParam(value = "errorMessage")String errorMessage,Model model) { // 전역예외처리에서 보낸 errorMessge객체를 매개변수로 받음
		model.addAttribute("errorMessage",errorMessage); // 매개변수로 받은 예외객체를 뷰로 errorMessage라는 이름으로 보내준다.
		return "error";
	}

}
