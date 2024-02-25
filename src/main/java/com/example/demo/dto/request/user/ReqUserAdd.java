package com.example.demo.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUserAdd extends ReqUserLogin{			
	
	private String user_name;			// 유저명
	
	private String password2;			// 유저 비밀번호 재확인
}
