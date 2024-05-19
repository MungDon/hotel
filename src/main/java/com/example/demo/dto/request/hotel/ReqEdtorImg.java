package com.example.demo.dto.request.hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqEdtorImg {
	
	private String base64Code; // 에디터에서 넘긴 base64 로 인코딩된 문자열
	
	private String extension; 	// 이미지 확장자
}
