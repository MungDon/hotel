package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.hotel.ReqIntroAdd;
import com.example.demo.mapper.HotelMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

	private final HotelMapper hotelMapper;

	/**
	 * 호텔등록
	 * @param request
	 */
	public String uploadImg(String base64Code, String extension) {
		String fileName="";
		// TODO - base64 -> 이미지 파일이름으로 변환작업
		return fileName;
	}
}
