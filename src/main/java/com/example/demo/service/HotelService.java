 package com.example.demo.service;

 import com.example.demo.Exception.ErrorCode;
 import com.example.demo.dto.request.hotel.ReqEdtorImg;
 import com.example.demo.dto.request.hotel.ReqHotelImg;
 import com.example.demo.dto.request.hotel.ReqIntroAdd;
 import com.example.demo.dto.request.hotel.ReqIntroUpdate;
 import com.example.demo.dto.response.ResponseDTO;
 import com.example.demo.dto.response.hotel.ResHotelIntro;
 import com.example.demo.dto.response.hotel.ResIntroDetail;
 import com.example.demo.dto.response.hotel.ResIntroList;
 import com.example.demo.enums.IntroStatus;
 import com.example.demo.mapper.HotelMapper;
 import com.example.demo.util.CommonUtils;
 import lombok.RequiredArgsConstructor;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.http.HttpHeaders;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 import org.springframework.util.FileCopyUtils;
 import org.springframework.web.multipart.MultipartFile;

 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.nio.file.Files;
 import java.time.LocalDateTime;
 import java.time.format.DateTimeFormatter;
 import java.util.*;
 import java.util.Base64.Decoder;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class HotelService {

	@Value("${file.Upimg}")
	private String path;
	
	private final HotelMapper hotelMapper;
	
	/*소개글 상태 처리*/
	@Transactional
	public String introStatusProvider(String status){
		if(status.equals(IntroStatus.SELECTED_INTRODUCTION.getCode())) {
			hotelMapper.resetStatus();
		}
		return IntroStatus.statusCodeToName(status);
	}

	 /*호텔 소개 등록*/
	@Transactional
	public void introAdd(ReqIntroAdd req) {
		String statusName = introStatusProvider(req.getStatus());
		req.setStatus(statusName);
		hotelMapper.introAdd(req);
		insertHotelSid(req.getHotel_sid());
	}
	/*소개글 상세보기*/
	@Transactional(readOnly = true)
	public ResIntroDetail introDetail(Long hotel_sid){
		return hotelMapper.findIntroDetailByHotelSid(hotel_sid);
	}

	/*소개글 수정*/
	@Transactional
	public void introUpdate(ReqIntroUpdate req){
		String statusName = introStatusProvider(req.getStatus());
		req.setStatus(statusName);
		hotelMapper.introUpdate(req);
		insertHotelSid(req.getHotel_sid());
	}

	@Transactional
	public ResponseDTO introDelete(Long hotel_sid){
		int result = hotelMapper.introDelete(hotel_sid);
		return CommonUtils.successResponse(result,"소개글이 삭제되었습니다",ErrorCode.DELETE_OPERATION_FAILED);
	}

	@Transactional
	/*이미지 테이블에 호텔소개 시퀀스 삽입*/
	public void insertHotelSid(Long hotel_sid) {
		// 저장된 소개글 내용중 이미지 파일명만 가져옴
		List<String> imgFileNames = extractImgFileName(hotel_sid);
		deleteNotUseImages(imgFileNames,hotel_sid);
		for(String imgFileName : imgFileNames) {
			hotelMapper.insertHotelSid(imgFileName, hotel_sid);
		}
	}

	@Transactional
	public void deleteNotUseImages(List<String> images, Long hotel_sid){
		Map<String,Object> inUseData = new HashMap<>();
		inUseData.put("images",images);
		inUseData.put("hotel_sid", hotel_sid);
		List<String> notUserImages = hotelMapper.selectFilesNotInList(inUseData);
		if(!CommonUtils.isEmpty(notUserImages)) {
			deleteImg(notUserImages);
		}
	}

	@Transactional(readOnly = true)
	/*소개 등록 내용중 파일명 추출 */
	public List<String> extractImgFileName(Long hotel_sid) {
		ResIntroDetail detail = hotelMapper.findIntroDetailByHotelSid(hotel_sid);
		   List<String> imgFileNames = new ArrayList<String>();
	        // 정규 표현식을 사용하여 이미지 파일 이름 추출
	        String imgPattern = "fileName=([^\"]+)";
	        Pattern pattern = Pattern.compile(imgPattern);
	        Matcher matcher = pattern.matcher(detail.getContent());

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
	 * @param req
	 * req = base64Code, imgExtension
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
	 public ResponseDTO deleteImg(List<String> fileNames) {
		 int result = 0;

		 for(String fileName : fileNames) {
			 result += hotelMapper.deleteFile(fileName);
		 }
		 if(result == fileNames.size()) {
			 result = 1;
			 removeImgFromPath(fileNames);
		 }
		 return CommonUtils.successResponse(result,"이미지 삭제완료", ErrorCode.DELETE_OPERATION_FAILED);
	 }
	 
	 /*실제 경로에서 이미지 삭제*/
	 private void removeImgFromPath(List<String> fileNames) {
		 for(String fileName : fileNames) {
			 File file = new File(path, fileName);
			 file.delete();
		 }
	 }
	 
	 /*에디터 내용 불러오기*/
	 @Transactional(readOnly = true)
	 public List<ResIntroList> findIntro() {
		 List<ResIntroList> intros =  hotelMapper.findIntro();
		 return intros;
	 }
	 
	 /*대표 소개글 설정*/
	 @Transactional
	 public ResponseDTO changeStatus(Long hotel_sid) {
		 hotelMapper.resetStatus();
		 int result = hotelMapper.changeStatus(hotel_sid);
		 return CommonUtils.successResponse(result,"대표글이 설정되었습니다.",ErrorCode.UPDATE_OPERATION_FAILED);
	 }

	/* 유저 페이지 소개글 불러오기*/
	 @Transactional(readOnly = true)
	 public ResHotelIntro hotelIntro(){
		 return hotelMapper.hotelIntro();

	 }
}
