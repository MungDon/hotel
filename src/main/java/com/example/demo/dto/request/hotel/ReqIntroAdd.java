package com.example.demo.dto.request.hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqIntroAdd {

	private Long user_sid; 		//호텔PK
	
	private String content; // 에디터에서 작성한내용
	
	
	
}
