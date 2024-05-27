package com.example.demo.dto.response.hotel;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ResIntroList {
	
	private Long hotel_sid;
	
	private String title;		// 소개글 제목
	
	private String content; // 에디터로 등록된 내용
	
	private String status;	// 소개글 공개/비공개 상태
	
	private LocalDateTime created_date;	// 소개글 등록일시
}
