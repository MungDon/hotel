 package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	USER(1, "USER","일반회원"),
	SELLER(2,"SELLER","판매자"),
	ADMIN(3,"ADMIN","관리자");
	
	private int code;
	private String type;
	private String name;
	
	public static Role fromString(String value ,Role roles) {
		for(Role role : Role.values()) {
			if(role.type.equals(value) && roles.getCode() <= role.getCode()) {
				return role;
			}
		}
		throw new IllegalArgumentException("다음 권한을 찾을 수 없습니다 : "+ value);
	}
}