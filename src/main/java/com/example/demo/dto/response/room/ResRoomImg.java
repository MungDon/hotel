package com.example.demo.dto.response.room;

import lombok.Getter;

@Getter
public class ResRoomImg {
	
	private Long room_sid;			// 객실 시퀀스
	
	private Long room_img_sid;	// 객실 이미지 시퀀스
	
	private String img_name;		// 이미지 명
	
	private String original_name;	// 원본 이미지 명
}
