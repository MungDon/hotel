package com.example.demo.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUserLogin {
	
	@NotBlank(message = "이메일을 입력해주세요")
	private String user_email;			// 유저 이메일

	@NotBlank(message = "이메일을 입력해주세요")
	private String password;				// 유저 비밀번호

}
