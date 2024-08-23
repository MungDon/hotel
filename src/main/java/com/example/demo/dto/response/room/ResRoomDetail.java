package com.example.demo.dto.response.room;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ResRoomDetail extends ResRoomList{

	private String room_info;

	private String room_name;

	private String room_size;

	private String bed_size;

	private int child_limit;		// 소아 인원제한
	
	private int adult_limit;	// 성인 인원제한
	
	private int price;				// 객실 요금
	
	private List<ResRoomImg>images;		// 객실이미지
	
	private LocalDateTime created_date;		// 생성 일시
	
	private LocalDateTime modified_date;	// 수정 일시
	
}
