package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SearchDto extends Search{
	
	private LocalDate start_date;		// 체크인 일자
	
	private LocalDate end_date;		// 체크 아웃일자
	
	private int adult_cnt;				// 성인 수
	
	private int child_cnt;				// 어린이 수
	


}
