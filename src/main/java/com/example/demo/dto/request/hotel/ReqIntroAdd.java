package com.example.demo.dto.request.hotel;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqIntroAdd {

	private Long hotel_sid; 		//호텔PK
	
	private String hotel_info;	// 호텔정보
	
	private String taskDetails;
	
	private List<MultipartFile> images;	// 이미지
	
	
}
