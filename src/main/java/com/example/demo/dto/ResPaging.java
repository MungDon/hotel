package com.example.demo.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResPaging<T> {// 제네릭 클래스, 이 클래스 선언시 타입지정가능 멤버 변수인 list 도 자동으로 클래스 타입 따라감
	
	// 메인으로쓸 리스트
	private List<T> list = new ArrayList<>();
	
	// 페이징 객체
	private Pagination pagination;
	
	// 생성자
	public ResPaging(List<T> list, Pagination pagination) {
		this.list.addAll(list);
		this.pagination = pagination;
	}
}
