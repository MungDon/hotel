package com.example.demo.Exception; 

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	
	ID_DUPLICATE ( HttpStatus.CONFLICT, "이미 있는 아이디입니다 로그인해주세요"),
	DUPLICATE_ROOM_DATA( HttpStatus.CONFLICT, "이미 등록된 객실입니다"),
	USER_NAME_DUPLICATE(HttpStatus.CONFLICT, "중복된 닉네임입니다"),
	PASS_DONT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 서로 일치하지 않습니다"),
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED,"고객전용 서비스입니다. 로그인 후 이용가능합니다."),
	NO_ACCOUNT(HttpStatus.NO_CONTENT,"없는 계정입니다, 계정을 생성해주세요"),
	NO_OPTION_NAME(HttpStatus.NO_CONTENT,"옵션명을 적지않았습니다, 옵션명을 적어주세요"),
	NO_OPTION_VALUE(HttpStatus.NO_CONTENT,"옵션 내용을 적지않았습니다, 옵션내용을 입력해 주세요"),
	NO_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 틀렸습니다"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "없는 페이지입니다"),
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST,"입력 정보가 유효하지 않습니다. 사유 : "),
	DB_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR,"데이터 삭제 실패, 다시 시도해도 안될 시 운영자에게 문의 해주세요"),
	FAIL_SEND_EMAIL(HttpStatus.INTERNAL_SERVER_ERROR,"이메일 전송 실패, 없는 이메일입니다."),
	FAIL_AUTHENTICATION(HttpStatus.BAD_REQUEST,"인증실패"),
	FAIL_TEMPORARY_RESERVATION(HttpStatus.INTERNAL_SERVER_ERROR,"임시예약 실패"),
	INSERT_OPERATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR,"저장 작업 실패"),
	UPDATE_OPERATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR,"수정 작업 실패"),
	DELETE_OPERATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR,"삭제 작업 실패"),
	RESERVATION_STATUS_CHANGE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,"예약 상태 변경 실패"),
	NO_AVAILABLE_ROOMS(HttpStatus.CONFLICT,"예약가능한 객실이 없습니다."),
	FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR,"파일 업로드에 실패하였습니다, 다시 시도해도 안될 시 운영자에게 문의 해주세요");
	
	private final  HttpStatus status;
	private final String message;
}
