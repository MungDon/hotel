 package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	USER("USER","일반회원"),
	STAFF_SIGNUP_AWAITING("STAFF_AWAIT","직원가입대기"),
	STAFF("STAFF","직원"),
	ADMIN("ADMIN","관리자");
	
	private String type;
	private String name;
	
}