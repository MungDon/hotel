package com.example.demo.dto.request.room;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqRoomAdd {
	
	private Long room_sid;		// 방 PK

	@NotBlank(message = "방 정보는 필수 입니다!")
	private String room_info; // 방 정보
	
	
	private String room_img;	// 이미지 주소 
	

	private List<ReqOptions> options;	// 옵션 리스트 
	
}
