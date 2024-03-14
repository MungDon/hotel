package com.example.demo.dto.request.hotelsell;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ReqRoomAdd {
	
	private Long room_sid;
	
	private String room_info; // 호텔정보
	 
	private String room_img;	// 이미지 주소 
	
	private List<Options> options; 
	
	
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Options{
			
			private Long room_sid;
			
			private String option_name; // 옵션 명	
			
			private String option_value;	// 옵션 내용
	}

	
	
	
	
}
