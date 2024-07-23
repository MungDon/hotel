package com.example.demo.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqSendAuthCode {

    @NotEmpty(message = "이메일 입력은 필수입니다.")
    private String email;       // 받는이메일
    
    private String action;      // 넘어온 페이지에 따라 전략구분을 위한 변수
                                // signup : 회원가입
                                // findPw : 비번찾기

}
