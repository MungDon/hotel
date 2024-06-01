package com.example.demo.dto.response.room;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class ResRoomDetail extends ResRoomList{
	
	private List<ResRoomImg>images;
	
	private LocalDateTime created_date;		// 생성 일시
	
	private LocalDateTime modified_date;	// 수정 일시
	
	
}
