package com.example.demo.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	
	ID_DUPLICATE (HttpStatus.CONFLICT, "이미 있는 아이디입니다 로그인해주세요"),
	PASSWORD_DUPLICATE(HttpStatus.CONFLICT, "중복된 닉네임입니다"),
	PASS_DONT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 서로 일치하지 않습니다"),
	NO_ID(HttpStatus.NO_CONTENT,"없는 아이디입니다."),
	NO_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 틀렸습니다");
	
	private final  HttpStatus status;
	private final String message;
}
