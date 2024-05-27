package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IntroStatus {
	
	SELECTED_INTRODUCTION("1", "공개"),
	UNSELECTED_INTRODUCTION("0", "비공개");
	
	private String code;
	
	private String name;
	
	public static String statusCodeToName(String code) {
		for(IntroStatus introEnum  : IntroStatus.values()) {
			if(introEnum.getCode().equals(code)) {
				return introEnum.getName();
			}
		}
		return "없음";
	}
}
