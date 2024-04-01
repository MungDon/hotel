package com.example.demo.dto.request.hotel;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqHotelAdd {

	private Long hotel_sid; 		//호텔PK
	
	private String hotel_info;	// 호텔정보
	
	private String hotel_address;		// 호텔주소
	
	private List<MultipartFile> images;	// 이미지
	
	private List<ReqHotelOptions> options;	// 옵션
	
}
