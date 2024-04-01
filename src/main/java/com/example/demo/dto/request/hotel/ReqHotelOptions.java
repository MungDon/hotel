package com.example.demo.dto.request.hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqHotelOptions {

	private Long hotel_sid;		// 호텔 FK
	
	private String hotel_option_name; 	// 호텔 옵션이름
	
	private String hotel_option_value;		// 호텔 옵션 내용
}
