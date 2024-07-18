package com.example.demo.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CommonUtils {

    public static Long getSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Long user_sid = (Long) session.getAttribute("user_sid");
        return user_sid;
    }
}
