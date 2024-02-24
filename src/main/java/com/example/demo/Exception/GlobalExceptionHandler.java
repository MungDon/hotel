package com.example.demo.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class) // CustomException 클래스를 value 값으로 설정
	public ModelAndView handleCustomException(CustomException e) { // CustomException 을 매개변수로 받음
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("redirect:/user/error");	// 데이터가 남아 뒤로가기 했을때 또다시 에러페이지가뜨는것을 막기위해  redirect 사용
		modelAndView.addObject("errorMessage", e.getErrorCode().getMessage()); // 해당 에러코드 안에 에러메시지를 errorMessage라는 이름의 객체로 컨트롤러에 보내줌
		return modelAndView;					// view의 이름과 객체를 리턴 = ModelAndView [view="redirect:/user/error"; model={errorMessage=해당 에러의 메시지}]																					
	}

}
