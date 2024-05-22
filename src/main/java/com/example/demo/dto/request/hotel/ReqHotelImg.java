package com.example.demo.dto.request.hotel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqHotelImg {

	private String original_name;	// 이미지 원본이름
	
	private String img_name;		// 이미지 명(uuid+확장자)
	
	private String extension;			// 확장자
}
