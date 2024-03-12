package com.example.demo.Exception; 

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	
	ID_DUPLICATE ( HttpStatus.CONFLICT, "이미 있는 아이디입니다 로그인해주세요"),
	USER_NAME_DUPLICATE(HttpStatus.CONFLICT, "중복된 닉네임입니다"),
	PASS_DONT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 서로 일치하지 않습니다"),
	NO_ACCOUNT(HttpStatus.NO_CONTENT,"없는 계정입니다, 계정을 생성해주세요"),
	NO_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 틀렸습니다"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "없는 페이지입니다"),
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST,"입력 정보가 유효하지 않습니다. 사유 : ");
	
	private final  HttpStatus status;
	private final String message;
}
