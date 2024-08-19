 package com.example.demo.enums;

 import lombok.Getter;
 import lombok.RequiredArgsConstructor;

 @Getter
@RequiredArgsConstructor
public enum Role {
	USER("USER","일반회원"),
	STAFF_SIGNUP_AWAITING("STAFF_AWAIT","직원가입대기"),
	STAFF_SIGNUP_REJECT("STAFF_REJECT","직원가입거절"),
	STAFF("STAFF","직원"),
	ADMIN("ADMIN","관리자");
	
	private final String type;
	private final String name;

	public static String typeToName(String type) {
		for(Role roleEnum  : Role.values()) {
			if(roleEnum.getType().equals(type)) {
				return roleEnum.getName();
			}
		}
		return "없음";
	}
	
}