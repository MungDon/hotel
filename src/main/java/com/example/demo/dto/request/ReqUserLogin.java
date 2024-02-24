package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUserLogin {
	
	private String user_email;			// 유저 이메일 
	
	private String password;				// 유저 비밀번호
}
