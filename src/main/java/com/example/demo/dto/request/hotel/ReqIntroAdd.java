package com.example.demo.dto.request.hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqIntroAdd {

	private Long user_sid; 		//호텔PK
	
	private String title;			// 소개글 제목 
	
	private String status;		// 대표글 상태
	
	private String content; 	// 에디터에서 작성한내용
	
	
	
}
