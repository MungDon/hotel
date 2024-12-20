package com.example.demo.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
   import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestGlobalExceptionHandler {
	/**
	 * MailSendException 에러 처리/ 이메일 전송 실패시
	 * @param e (MailSendException) 예외
	 * @return
	 */
	@ExceptionHandler(MailSendException.class)
	public ResponseEntity<ErrorResponse> handleMailSendException(MailSendException e) {
		log.info("메일전송 실패");
		return ErrorResponse.toResponseEntity(ErrorCode.FAIL_SEND_EMAIL);
	}
	
	/**
	 * RestCustomException 커스텀 예외처리
	 * @param e(RestCustomException) 예외
	 * @return
	 */
	@ExceptionHandler(RestCustomException.class)
	public ResponseEntity<ErrorResponse> handleRestCustomException(RestCustomException e){
		log.info("RestCustomException 에러 사유 : {}", e.getErrorCode().getMessage());
		return ErrorResponse.toResponseEntity(e.getErrorCode());
	}
}
