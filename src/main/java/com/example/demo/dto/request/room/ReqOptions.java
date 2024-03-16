package com.example.demo.dto.request.room;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReqOptions {
	
	private Long room_sid;	// 방 fk

	private String option_name; // 옵션 명	

	private String option_value;	// 옵션 내용
}
