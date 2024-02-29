package com.example.demo.dto.response.user;

import lombok.Getter;

@Getter
public class ResUserLogin extends ResUserChk{
	
	private Long user_sid;
	
	private String user_name;
}
