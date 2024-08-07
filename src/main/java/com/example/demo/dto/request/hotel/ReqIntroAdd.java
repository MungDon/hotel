package com.example.demo.dto.request.hotel;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqIntroAdd {


	private Long hotel_sid; 	// 호텔PK

	@NotBlank(message = "회원 FK 누락")
	private Long user_sid; 	// 회원 FK	

	@NotBlank(message = "제목은 필수 입력 사항입니다.")
	private String title;			// 소개글 제목

	private String status;		// 대표글 상태

	@NotBlank(message = "내용은 필수 입력사항입니다.")
	private String content; 	// 에디터에서 작성한내용
	
	
	
}
