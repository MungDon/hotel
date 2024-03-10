package com.example.demo.dto.request.hotelsell;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqHotelAdd {
	
	private Long user_sid;	// 유저 테이블 Fk
	
	private String hotel_info; // 호텔정보
	 
	private String hotel_img;	// 이미지 주소 
	
	private List<Options> options; 
	
	
	@Getter
	@Setter
	public class Options{
		
		private String bed_type;		// 침대 유형
		
		private int bed_count;			// 침대 개수
		
		private int room_size;			// 방 사이즈
		
		private int room_count;		// 방 개수
		
		private Long room_price;	// 방 가격
		
		private String room_img;	// 방 이미지
		
		private String view_type;	// 전망 유형
		
		private int people;				// 수용가능 인원
		
		private String parking;		// 주차 유형
		
		
		
		
	}
	
	
	
	
}
