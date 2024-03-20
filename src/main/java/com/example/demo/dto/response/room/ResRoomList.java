package com.example.demo.dto.response.room;

import java.util.List;

import lombok.Getter;

@Getter
public class ResRoomList {
	
	private Long room_sid;
	
	private String room_info;
	
	private List<ResOptions> options;
	
	private List<ResRoomImg> images;

}
