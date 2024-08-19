package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OptionType {
	
	ROOM_INFO_OPTION("1","객실정보"),
	ROOM_USE_OPTION("2","객실이용");
	
	
	private final String code;
	private final String name;
}
