package com.example.demo.dto.request.room;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqRoomAdd {
	
	private Long room_sid;			// 방 PK

	@NotBlank(message = "방 정보는 필수 입니다!")
	private String room_name; 		// 방 정보
	
	private String local_num;		 // 지역번호
	
	private String middle_num;		// 중간번호
	
	private String last_num;			// 끝번호
	
	private int price;						// 방가격
	
	private int room_count;			// 등록 객실 수
	
	private int person_limit;			// 객실당 제한인원
	
	private LocalDate start_date;		// 예약가능기간(시작)
	
	private LocalDate end_date;		// 예약가능기간(끝)

	private List<MultipartFile> images;		// 이미지 파일
	
	private List<MultipartFile> thumbnail; 	// 썸네일
	
	private List<ReqOptions> options;			// 옵션 리스트 
	
	private List<ReqOptions> useOptions;	//객실이용 옵션
}
