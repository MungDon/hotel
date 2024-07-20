package com.example.demo.Exception;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 커스텀 예외 핸들러
	 * @param e CustomException
	 * @param model 
	 * @return 에러페이지
	 */
	@ExceptionHandler(CustomException.class) // CustomException 클래스를 value 값으로 설정
	public String handleCustomException(CustomException e,Model model) { // CustomException 을 매개변수로 받음
		return sendErrorMessage(e.getErrorCode().getMessage(), model);
	}

	/**
	 * 유효성검사에서 발생한 예외 핸들러
	 * @param e MethodArgumentNotValidException
	 * @param model
	 * @return 에러페이지
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)// 유효성검사에서 발생한 예외 처리 핸들러
	public String handleValidErrorException(MethodArgumentNotValidException e,Model model) {
		final ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE,e.getBindingResult());
		return sendErrorMessage(errorResponse.getMessage(),model);
	}

	/**
	 * 파일 업로드 예외 핸들러
	 * @param e IOException
	 * @param model
	 * @return 에러페이지
	 */
	@ExceptionHandler(IOException.class)
	public String handleIOException(IOException e,Model model) {
		return sendErrorMessage(ErrorCode.FILE_UPLOAD_FAILED.getMessage(),model);
	}

	/*에러페이지에 에러 정보 전달*/
	private String sendErrorMessage(String errorMessage,Model model){
		model.addAttribute("errorMessage", errorMessage);
		return "error";
	}
	

}
