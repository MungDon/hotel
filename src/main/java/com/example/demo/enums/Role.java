 package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	USER(1, "USER","일반회원"),
	STAFF(2, "STAFF","직원"),
	ADMIN(3,"ADMIN","관리자");
	
	private int code;
	private String type;
	private String name;
	
}