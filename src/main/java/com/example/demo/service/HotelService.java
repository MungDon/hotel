 package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.hotel.ReqEdtorImg;
import com.example.demo.dto.request.hotel.ReqHotelImg;
import com.example.demo.dto.request.hotel.ReqIntroAdd;
import com.example.demo.dto.response.hotel.ResIntroList;
import com.example.demo.enums.IntroStatus;
import com.example.demo.mapper.HotelMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

	@Value("${file.Upimg}")
	private String path;
	
	private final HotelMapper hotelMapper;
	

	 /*호텔 소개 등록*/
	@Transactional
	public void introAdd(ReqIntroAdd req) {
		if(req.getStatus().equals(IntroStatus.SELECTED_INTRODUCTION.getCode())) {
			hotelMapper.resetStatus();
		}
		String statusName = IntroStatus.statusCodeToName(req.getStatus());
		req.setStatus(statusName);
		hotelMapper.introAdd(req);
		insertHotelSid(req.getHotel_sid());
	}
	/*이미지 테이블에 호텔소개 시퀀스 삽입*/
	private void insertHotelSid(Long hotel_sid) {
		// 저장된 소개글 내용중 이미지 파일명만 가져옴
		List<String> imgFileNames = extractImgFileName(hotel_sid);
		for(String imgFileName : imgFileNames) {
			hotelMapper.insertHotelSid(imgFileName, hotel_sid);
		}
	}
	
	
	/*소개 등록 내용중 파일명 추출 */
	private List<String> extractImgFileName(Long hotel_sid) {
		String content = hotelMapper.findByContent(hotel_sid);
		   List<String> imgFileNames = new ArrayList<String>();
	        // 정규 표현식을 사용하여 이미지 파일 이름 추출
	        String imgPattern = "fileName=([^\"]+)";
	        Pattern pattern = Pattern.compile(imgPattern);
	        Matcher matcher = pattern.matcher(content);

	        while (matcher.find()) {
	            imgFileNames.add(matcher.group(1)); // 첫 번째 캡처 그룹이 이미지 파일 이름
	        }

	        return imgFileNames;
	}
	
	/*에디터 이미지 등록*/
	@Transactional
	public String uploadImg(MultipartFile files) throws  IOException {
			String originalName = files.getOriginalFilename(); // 입력받은 파일의 원본 이름 저장
			String uuid = String.valueOf(UUID.randomUUID()); // uuid 생성
			String extension = originalName.substring(originalName.lastIndexOf(".")); // . 뒤로 잘라서 저장(확장자만 저장)
			String saveName = uuid + extension;// 랜덤으로 나온 uuid와 확장자를 더해서(연결해서) 저장
			File converFile = new File(path, saveName); // 저장된것을 파일로 변환
			
			if (!converFile.exists()) {//폴더없으면 생성
				converFile.mkdirs();
			}
			files.transferTo(converFile); 
			ReqHotelImg hotelImg = ReqHotelImg.builder().original_name(originalName).extension(extension).img_name(saveName).build();
			hotelMapper.uploadFile(hotelImg);
		return saveName;
	}
	
	/*에디터 내 이미지 미리보기*/
	public ResponseEntity<byte[]> showImg(String fileName){
		File file = new File(path+fileName);
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header,HttpStatus.OK);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 에디터 이미지 디코딩 & 파일 저장
	 * @param base64Code, extension
	 */
	@Transactional
	public String uploadBase64Img(ReqEdtorImg req) {
		
		int maxLength = 20;
		String filePath;
		String fileName= truncateAndAppendTimestamp(req.getBase64Code(), maxLength);
		
		filePath = path+fileName;
		
		try {
			File file = new File(filePath);
			Decoder decoder = Base64.getMimeDecoder();
			byte[] decodedBytes = decoder.decode(req.getBase64Code());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(decodedBytes);
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	/*base64 이미지 이름 재정의*/
	 private static String truncateAndAppendTimestamp(String base64Image, int maxLength) {
	        // 제거할 특수문자 정규식
	        String specialCharactersRegex = "[^a-zA-Z0-9]";

	        String truncatedBase64Image = base64Image.length() > maxLength
	                ? base64Image.substring(base64Image.length() - maxLength)
	                : base64Image;

	        // 특수문자를 제거하고 timestamp 생성
	        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
	        timestamp = timestamp.replaceAll(specialCharactersRegex, "");

	        // 특수문자를 제거한 timestamp를 포함하여 결과 문자열 생성
	        return new StringJoiner("_")
	                .add(truncatedBase64Image.replaceAll(specialCharactersRegex, ""))
	                .add(timestamp)
	                .toString();
	    }
	 
	 /*에디터 작성취소 시 이미지 삭제*/
	 @Transactional
	 public int deleteImg(List<String> fileNames) {
		 int result = 0;
		 int count = 0;
		 
		 for(String fileName : fileNames) {
			 result += hotelMapper.deleteFile(fileName);
			 count++;
		 }
		 if(result == count) {
			 removeImgFromPath(fileNames);
			 
		 }else {
			 throw new CustomException(ErrorCode.DB_DELETE_FAILED);
		 }
		 return result ;
	 }
	 
	 /*실제 경로에서 이미지 삭제*/
	 private void removeImgFromPath(List<String> fileNames) {
		 for(String fileName : fileNames) {
			 File file = new File(path, fileName);
			 if(file != null) {
				 file.delete();
			 }
		 }
	 }
	 
	 /*에디터 내용 불러오기*/
	 @Transactional(readOnly = true)
	 public List<ResIntroList> findByIntro() {
		 List<ResIntroList> intros =  hotelMapper.findByIntro();
		 return intros;
	 }
	 
	 /*대표 소개글 설정*/
	 @Transactional
	 public int changeStatus(Long hotel_sid) {
		 hotelMapper.resetStatus();
		 int result = hotelMapper.changeStatus(hotel_sid);
		 return result;
	 }
	 
}
