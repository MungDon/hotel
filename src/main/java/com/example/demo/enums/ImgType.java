package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImgType {   
	roomImg(1,"roomImg", "상품이미지"),
	thumbnail(2,"thumbnail", "대표이미지");
	
	private int code;
	private String type;
	private String name;
	
}
