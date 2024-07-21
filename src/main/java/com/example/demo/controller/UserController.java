package com.example.demo.controller;

import com.example.demo.dto.request.user.ReqUserAdd;
import com.example.demo.dto.request.user.ReqUserLogin;
import com.example.demo.dto.response.user.ResUserLogin;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import com.example.demo.user.UserValidateStrategy;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	private final EmailService emailService;
	private final Map<String, UserValidateStrategy> userValidateStrategy;

	/*회원가입 폼*/
	@GetMapping("") // RequsetMapping에 /user 를 써놓은것을 그대로 사용
	public String userAddForm() { //회원가입 폼 페이지를 반환하는 메서드
		return "userjoin";
	}

	/*회원가입*/
	@PostMapping("")// RequsetMapping에 /user 를 써놓은것을 그대로 사용
	public String userAdd(@Valid ReqUserAdd add) {
		userService.userAdd(add);						// 사용자의 회원가입 입력데이터를 서비스에 넘겨줌 
		return "redirect:/user/login";					// 입력데이터 insert 성공 시 = 회원가입 성공 시 로그인 페이지로 리다이렉트
	}

	@GetMapping("/login")
	public String userLoginForm() {
		return "login";
	}
	@PostMapping("/login")
	public String userLogin(ReqUserLogin reqLogin, HttpServletRequest httpServletRequest) {
		ResUserLogin resLogin =  userService.userLogin(reqLogin);			//서비스로 넘겨받은로그인정보 resLogin 대입
		httpServletRequest.getSession().invalidate();						//세션생성전 세션파기
		HttpSession session = httpServletRequest.getSession(true);	//기존 세션반환
		session.setAttribute("user_info", resLogin);					//세션에 해당 회원 정보 저장
		session.setMaxInactiveInterval(1800);								//세션 30분 유지
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

	@PostMapping("/send/code")
	@ResponseBody
	public ResponseEntity<String> sendEmail(@RequestParam(value = "email")String email,@RequestParam(value = "action")String action) throws MessagingException, UnsupportedEncodingException {
		UserValidateStrategy strategy = userValidateStrategy.get(action);
		strategy.memberChk(email);
		emailService.sendEmail(email);
		return ResponseEntity.ok("ok");
	}
	
}
