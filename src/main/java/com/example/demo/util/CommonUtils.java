package com.example.demo.util;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.Exception.RestCustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CommonUtils {

    /*foreign key 로 이용되는 user 시퀀스 가져오기*/
    public static Long getUserSid(HttpServletRequest request){
        HttpSession session = request.getSession(false);
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
}
