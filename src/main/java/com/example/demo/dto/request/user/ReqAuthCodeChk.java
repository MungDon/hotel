package com.example.demo.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqAuthCodeChk {

    @NotEmpty(message = "이메일 입력은 필수입니다.")
    private String email;

    @NotEmpty(message = "인증코드는 필수 입력사항입니다.")
    private String authCode;
}
