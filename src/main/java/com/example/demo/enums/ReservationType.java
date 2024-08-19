package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationType {

	TEMPORARY("임시예약"),
	COMPLETED("예약완료");
	
	private final String status;
}
