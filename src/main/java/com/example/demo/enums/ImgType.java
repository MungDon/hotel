package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImgType {   
	roomImg(1,"roomImg", "상품이미지"),
	thumbnail(2,"thumbnail", "대표이미지");
	
	private final int code;
	private final String type;
	private final String name;
	
}
