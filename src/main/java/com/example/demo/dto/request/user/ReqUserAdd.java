package com.example.demo.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUserAdd {				// 유저 입력 데이터 
	
	private Long user_sid;					// 유저 PK값, 자동 증가
	
	private String user_email;			// 유저 이메일 
	
	private String user_name;			// 유저명
	
	private String password;				// 유저 비밀번호
	
	private String password2;			// 유저 비밀번호 재확인
}
