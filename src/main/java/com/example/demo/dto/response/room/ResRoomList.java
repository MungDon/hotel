package com.example.demo.dto.response.room;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ResRoomList {
	
	private Long room_sid;				// 방 고유번호

	private String room_name;			// 객실 유형이제 객실명

	private String room_info;			// 방 설명
	
	private int price;					// 방 가격

	private LocalDateTime created_date;

	private LocalDateTime modified_date;

	private List<ResOptions> options;		// 옵션
	
	private ResRoomImg thumbnail;		// 대표이미지

}
