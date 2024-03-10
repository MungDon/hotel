package com.example.demo.dto.request.hotelsell;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqHotelAdd {
	
	private Long option_sid; // 옵션 테이블 Fk
	
	private Long user_sid;	// 유저 테이블 Fk
	
	private String hotel_info; // 호텔정보
	
	private String hotel_img;	// 이미지 주소
	
	
	
	
	
}
