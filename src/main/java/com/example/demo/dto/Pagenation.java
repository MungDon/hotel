package com.example.demo.dto;

import lombok.Getter;

@Getter
public class Pagenation {
	
	private int totalRecordCount;		// 전체 데이터 수
	
	private int totalPageCount;			// 전체 페이지 수
	
	private int startPage;					// 시작 페이지 번호
	
	private int endPage;					// 끝페이지 번호
	
	private int limitStart;					// 리밋 시작위치
	
	private boolean existPrevPage;	// 이전 페이지 존재 여부
	
	private boolean existNextPage;	// 다음 페이지 여부
	
	public Pagenation(int totalRecordCount, SearchDto dto) {
		this.totalRecordCount = totalRecordCount;
		calculation(dto);
	}
	
	public void calculation(SearchDto dto) {
		
		// 전체 페이지 수 계산
		totalPageCount = ((totalRecordCount - 1) / dto.getPageSize())+1;
		
		// 현재페이지 번호가 전체 페이지 수 보다 큰경우 , 현재 페이지 번호에 전체 페이지 수 저장
		if(dto.getPage() > totalPageCount) {
			dto.setPage(totalPageCount) ;
		}
		
		// 시작 페이지 번호 계산 ex) 현재페이지 1, 페이지사이즈10 : (1-1)/10*10+1 = 1
		startPage = (dto.getPage() -1) / dto.getPageSize() * dto.getPageSize() + 1;
	}
}
