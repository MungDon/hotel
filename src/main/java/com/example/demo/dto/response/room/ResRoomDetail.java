package com.example.demo.dto.response.room;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class ResRoomDetail extends ResRoomList{
	
	private int child_limit;		// 소아 인원제한
	
	private int adult_limit;	// 성인 인원제한
	
	private int price;				// 객실 요금
	
	private String local_num;		 // 지역번호
	
	private String middle_num;		// 중간번호
	
	private String last_num;			// 끝번호
	
	private List<ResRoomImg>images;		// 객실이미지
	
	private LocalDateTime created_date;		// 생성 일시
	
	private LocalDateTime modified_date;	// 수정 일시
	
}
