package com.example.demo.dto.request.room;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqRoomImg {
	
	private Long room_sid; 				// 방 고유번호
	
	private String original_name;		// 이미지 원본 이름
		
	private String extension;				// 이미지 확장자
	
	private String img_name;			// 이미지 명(UUID포함)
	
	private String img_type;				// 이미지 타입
}
