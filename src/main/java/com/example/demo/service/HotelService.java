package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.request.hotel.ReqEdtorImg;
import com.example.demo.dto.request.hotel.ReqIntroAdd;
import com.example.demo.mapper.HotelMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

	@Value("${file.Upimg}")
	private String path;
	
	private final HotelMapper hotelMapper;
	
	/**
	 *  호텔 소개 등록
	 * @param req
	 */
	public void introAdd(ReqIntroAdd req) {
		hotelMapper.introAdd(req);
	}
	
	public String uploadFile(MultipartFile[] files) throws  IOException {
		for(MultipartFile file : files) {
			String originalName = file.getOriginalFilename(); // 입력받은 파일의 원본 이름 저장
			String uuid = String.valueOf(UUID.randomUUID()); // uuid 생성
			String extension = originalName.substring(originalName.lastIndexOf(".")); // . 뒤로 잘라서 저장(확장자만 저장)
			String saveName = uuid + extension;// 랜덤으로 나온 uuid와 확장자를 더해서(연결해서) 저장
			File converFile = new File(path, saveName); // 저장된것을 파일로 변환
			
			if (!converFile.exists()) {//폴더없으면 생성
				converFile.mkdirs();
			}
			file.transferTo(converFile); 
			
		}
	}
	
	
	/**
	 * 에디터 이미지 디코딩 과 DB 등록(호텔 소개 등록)
	 * @param 
	 */
	public String uploadBase64Img(ReqEdtorImg req) {
		String fileName= ;
		return fileName;
	}
}
