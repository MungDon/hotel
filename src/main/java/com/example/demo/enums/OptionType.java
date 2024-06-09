package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OptionType {
	
	ROOM_INFO_OPTION("1","객실정보"),
	ROOM_USE_OPTION("2","객실이용");
	
	
	private String code;	
	private String name;
}
