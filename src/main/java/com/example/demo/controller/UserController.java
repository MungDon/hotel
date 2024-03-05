package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.request.user.ReqUserAdd;
import com.example.demo.dto.request.user.ReqUserLogin;
import com.example.demo.dto.response.user.ResUserLogin;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	/*판매자/일반회원 회원가입 선택지 페이지 폼*/
	@GetMapping("/choice")
	public String choiceForm() {
		return "choice";
	}
	
	@GetMapping("") // RequsetMapping에 /user 를 써놓은것을 그대로 사용
	public String userForm() { //회원가입 폼 페이지를 반환하는 메서드
		return "userjoin";
	}
	
	@PostMapping("")// RequsetMapping에 /user 를 써놓은것을 그대로 사용
	public String userAdd(@Valid ReqUserAdd add) {
		userService.userAdd(add);						// 사용자의 회원가입 입력데이터를 서비스에 넘겨줌 
		return "redirect:/user/login";					// 입력데이터 insert 성공 시 = 회원가입 성공 시 로그인 페이지로 리다이렉트
	}

	@GetMapping("/error") // 에러페이지 반환 맵핑
	public String errorForm(@RequestParam(value = "errorMessage")String errorMessage,Model model) { // 전역예외처리에서 보낸 errorMessge객체를 매개변수로 받음
		model.addAttribute("errorMessage",errorMessage); // 매개변수로 받은 예외객체를 뷰로 errorMessage라는 이름으로 보내준다.
		return "error";
	}
	
	@GetMapping("/login")
	public String userLoginForm() {
		return "login";
	}
	@PostMapping("/login")
	public String userLogin(ReqUserLogin reqLogin, HttpServletRequest httpServletRequest) {
		ResUserLogin resLogin =  userService.userLogin(reqLogin);			//서비스로 넘겨받은로그인정보 resLogin 대입
		httpServletRequest.getSession().invalidate();								//세션생성전 세션파기
		HttpSession session = httpServletRequest.getSession(true);			//세션없으면 생성 있으면 기존 세션반환
		session.setAttribute("user_sid", resLogin.getUser_sid());				//세션에 해당 회원 고유번호 저장
		session.setAttribute("user_name", resLogin.getUser_name());		//세션에 해당회원 회원명 저장
		session.setAttribute("role_user", resLogin.getRole());					//세션에 해당회원 권한 저장
		session.setMaxInactiveInterval(1800);											//세션 30분 유지
		return "redirect:/hotel";
	}
	
	@DeleteMapping("/logout")
	@ResponseBody
	public void userLogout(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession(false); //세션이 없으면 생성 하지않음 아니라면 기존 세션반환 
		if(session != null) {
			session.invalidate(); //세션이있다면 삭제
		}
		
	}
	
}
