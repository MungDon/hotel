package com.example.demo.dto.response.user;

import com.example.demo.enums.Role;

import lombok.Getter;

@Getter
public class ResUserLogin{
	
	private String user_email;
	
	private String password;
	
	private Long user_sid;
	
	private String user_name;
	
	private String role;
}
