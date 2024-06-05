package com.example.demo.dto.response.room;

import java.util.List;

import lombok.Getter;

@Getter
public class ResRoomList {
	
	private Long room_sid;		// 방 고유번호
	
	private String room_name;	// 방 이름
	
	private int price;					// 방 가격
	
	private List<ResOptions> options;		// 옵션
	
	private ResRoomImg thumbnail;		// 대표이미지

}
