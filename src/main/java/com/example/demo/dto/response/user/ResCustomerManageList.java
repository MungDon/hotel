package com.example.demo.dto.response.user;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResCustomerManageList {

    private Long user_sid;                  // 유저 시퀀스       

    private String user_email;              // 유저 이메일

    private String user_name;               // 유저명

    private String delete_yn;               // 탈퇴여부

    private String role;                    // 유저권한

    private String emp_number;              // 사원번호

    private LocalDateTime created_date;     // 가입일

    private LocalDateTime modified_date;    // 회원정보 수정일

    public void  setFormattedData(String formattedDeleteYn,String formattedRole){
        this.delete_yn = formattedDeleteYn;
        this.role = formattedRole;
    }
}
