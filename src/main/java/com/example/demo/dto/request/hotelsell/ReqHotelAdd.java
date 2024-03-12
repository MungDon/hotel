package com.example.demo.dto.request.hotelsell;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqHotelAdd {
	
	private String hotel_info; // 호텔정보
	 
	private String hotel_img;	// 이미지 주소 
	
	private List<Options> options; 
	
	
	@Getter
	@Setter
	public class Options{
			
			private String option_name; // 옵션 명	
			
			private String option_value;	// 옵션 내용
	}

	
	
	
	
}
