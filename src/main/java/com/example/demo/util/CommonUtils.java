package com.example.demo.util;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.Exception.RestCustomException;
import com.example.demo.dto.response.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.Map;

public class CommonUtils {

    /*foreign key 로 이용되는 user 시퀀스 가져오기*/
    public static Long getUserSid(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long user_sid = (Long) session.getAttribute("user_sid");
        return user_sid;
    }

    /*조건문에 따라 예외 발생(동기)*/
    public static void throwCustomExceptionIf(boolean conditionalStatement, ErrorCode errorCode) {
        if(conditionalStatement) {
            throw new CustomException(errorCode);
        }
    }
    /*조건문에 따라 예외 발생(비동기)*/
    public static void throwRestCustomExceptionIf(boolean conditionalStatement, ErrorCode errorCode) {
        if(conditionalStatement) {
            throw new RestCustomException(errorCode);
        }
    }

    /* null 체크 메서드 */
    public static boolean isEmpty(Object data) {
        if (data == null) {
            return true;
        }
        if (data instanceof String && "".equals(data)) {
            return true;
        }
        if (data instanceof Collection && ((Collection<?>) data).isEmpty()) {
            return true;
        }
        if (data instanceof Map && ((Map<?, ?>) data).isEmpty()) {
            return true;
        }
        if (data instanceof Object[] && ((Object[]) data).length == 0) {
            return true;
        }
        return false;
    }
    /*ResponseEntity Insert, Delete, Update 작업시 성공 반환*/
    public static ResponseDTO successResponse(int result, String message, ErrorCode errorCode){
        if(result < 1){
            throw new RestCustomException(errorCode);
        }
        return ResponseDTO.builder().status(HttpStatus.OK).message(message).success(true).build();
    }

}
