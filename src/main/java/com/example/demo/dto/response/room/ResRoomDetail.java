package com.example.demo.dto.response.room;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ResRoomDetail {

	private Long room_sid;				// 방 고유번호

	private String room_info;

	private String room_name;		// roomType.type_name 과같음

	private String room_size;

	private String bed_size;

	private int child_limit;		// 소아 인원제한
	
	private int adult_limit;	// 성인 인원제한
	
	private int price;				// 객실 요금
	
	private List<ResRoomImg>images;		// 객실이미지

	private List<ResOptions> totalOptions;		// 옵션

	private List<ResOptions> infoOptions;		// 옵션

	private List<ResOptions> useOptions;		// 옵션

	private LocalDateTime created_date;		// 생성 일시
	
	private LocalDateTime modified_date;	// 수정 일시


	public void setInfoOptions(List<ResOptions> infoOptions) {
		this.infoOptions = infoOptions;
	}
	public void setUseOptions(List<ResOptions> useOptions) {
		this.useOptions = useOptions;
	}
}
