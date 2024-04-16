package com.example.demo.dto.response.room;

import java.util.List;

import lombok.Getter;

@Getter
public class ResRoomList {
	
	private Long room_sid;	// 방 고유번호
	
	private String room_info;	// 방 정보
	
	private List<ResOptions> options;		// 옵션
	
	private List<ResRoomImg> images;	// 이미지

}
