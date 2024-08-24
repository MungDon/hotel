package com.example.demo.dto.request.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ReqRoomAdd {
	
	private Long room_sid;					// 방 PK

	@NotNull(message = "객실유형은 필수입니다.")
	private Long room_type_sid;				// 객실 유형 시퀀스

	@NotBlank(message = "방 정보는 필수 입니다!")
	private String room_info; 				// 방 정보
	
	private int price;						// 방가격
	
	private int adult_limit;				// 성인 제한인원
	
	private int child_limit;				// 소아 제한 인원

	private List<MultipartFile> images;		// 이미지 파일
	
	private List<MultipartFile> thumbnail; 	// 썸네일
	
	private List<ReqOptions> options;		// 옵션 리스트
	
	private List<ReqOptions> useOptions;	//객실이용 옵션
}
