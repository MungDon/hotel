 package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	USER("USER","일반회원"),
	STAFF("STAFF","직원"),
	ADMIN("ADMIN","관리자");
	
	private String type;
	private String name;
	
}