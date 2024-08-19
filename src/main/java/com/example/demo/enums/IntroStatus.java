package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IntroStatus {
	
	SELECTED_INTRODUCTION("1", "공개"),
	UNSELECTED_INTRODUCTION("0", "비공개");
	
	private final String code;
	
	private final String name;
	
	public static String statusCodeToName(String code) {
		for(IntroStatus introEnum  : IntroStatus.values()) {
			if(introEnum.getCode().equals(code)) {
				return introEnum.getName();
			}
		}
		return "없음";
	}
}
