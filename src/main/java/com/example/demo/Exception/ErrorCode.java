package com.example.demo.Exception; 

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	
	ID_DUPLICATE ( HttpStatus.CONFLICT, "이미 있는 아이디입니다 로그인해주세요"),
	USER_NAME_DUPLICATE(HttpStatus.CONFLICT, "중복된 닉네임입니다"),
	PASS_DONT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 서로 일치하지 않습니다"),
	NO_ACCOUNT(HttpStatus.NO_CONTENT,"없는 계정입니다, 계정을 생성해주세요"),
	NO_OPTION_NAME(HttpStatus.NO_CONTENT,"옵션명을 적지않았습니다, 옵션명을 적어주세요"),
	NO_OPTION_VALUE(HttpStatus.NO_CONTENT,"옵션 내용을 적지않았습니다, 옵션내용을 입력해 주세요"),
	NO_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 틀렸습니다"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "없는 페이지입니다"),
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST,"입력 정보가 유효하지 않습니다. 사유 : "),
	FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR,"파일 업로드에 실패하였습니다, 다시 시도해도 안될 시 운영자에게 문의 해주세요");
	
	private final  HttpStatus status;
	private final String message;
}
