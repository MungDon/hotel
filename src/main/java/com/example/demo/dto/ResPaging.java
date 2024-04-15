package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ResPaging<T> {// 제네릭 클래스, 이 클래스 선언시 타입지정가능 멤버 변수인 list 도 자동으로 클래스 타입 따라감
	
	private List<T> list = new ArrayList<>();
	
	private Pagination pagination;
	
	public ResPaging(List<T> list, Pagination pagination) {
		this.list.addAll(list);
		this.pagination = pagination;
	}
}
