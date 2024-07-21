package com.example.demo.dto.response.user;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResUserInfo extends ResUserLogin{

    private String delete_yn;               // 탈퇴 여부

    private LocalDateTime created_date;     // 가입 일자

    private LocalDateTime modified_date;    // 회원정보 수정 일자

    private String emp_number;              // 사원 번호



}
