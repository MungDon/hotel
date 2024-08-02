package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationType {

	TEMPORARY("임시예약"),
	COMPLETED("예약완료");
	
	private String status;
}
