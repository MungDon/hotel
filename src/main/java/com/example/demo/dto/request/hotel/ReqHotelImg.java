package com.example.demo.dto.request.hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqHotelImg {

	private Long hotel_sid;			// 호텔 FK
	
	private String original_name;	// 이미지 원본이름
	
	private String img_name;		// 이미지 명(uuid+확장자)
	
	private String extention;			// 확장자
}
