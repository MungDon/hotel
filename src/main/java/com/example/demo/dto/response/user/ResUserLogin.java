package com.example.demo.dto.response.user;

import lombok.Getter;

@Getter
public class ResUserLogin{

	private Long user_sid;

	private String user_email;
	
	private String password;
	
	private String user_name;
	
	private String role;

}
