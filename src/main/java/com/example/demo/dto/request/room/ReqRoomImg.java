package com.example.demo.dto.request.room;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqRoomImg {
	
	private Long room_sid;
	
	private String original_name;
		
	private String extension;
	
	private String img_name;
}
