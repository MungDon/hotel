package com.example.demo.dto.response.hotel;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ResIntroList {
	
	private Long hotel_sid;
	
	private String title;		// 소개글 제목
	
	private String content; // 에디터로 등록된 내용
	
	private String status;
	
	private LocalDateTime created_date;
}
