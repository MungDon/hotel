package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationType {

	TEMPORARY("1","임시예약"),
	COMPLETED("2","예약완료");
	
	private String code;
	private String name;
}
