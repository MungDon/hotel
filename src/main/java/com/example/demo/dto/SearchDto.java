package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
	
	private int page;				// 현재 페이지번호

	private int recordSize;		// 페이지당 출력할 데이터 개수
	
	private int pageSize;		// 화면 하단에 출력할 페이지 사이즈
	
	private LocalDate start_date;		// 체크인 일자
	
	private LocalDate end_date;		// 체크 아웃일자
	
	private int adult_cnt;				// 성인 수
	
	private int child_cnt;				// 어린이 수
	
	private Pagination pagination; // 페이지네이션 정보
	
	public SearchDto() {
		this.page = 1;
		this.recordSize = 10;
		this.pageSize = 10;
	}
	
	public int getOffset() {
		return (page - 1) * recordSize;
	}
}
