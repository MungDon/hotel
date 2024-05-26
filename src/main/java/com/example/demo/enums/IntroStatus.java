package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IntroStatus {
	
	SELECTED_INTRODUCTION("1", "선택 소개글"),
	UNSELECTED_INTRODUCTION("0", "비선택 소개글");
	
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
