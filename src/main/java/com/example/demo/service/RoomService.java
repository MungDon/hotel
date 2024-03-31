package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.request.room.ReqOptions;
import com.example.demo.dto.request.room.ReqRoomAdd;
import com.example.demo.dto.request.room.ReqRoomImg;
import com.example.demo.dto.response.room.ResRoomDetail;
import com.example.demo.dto.response.room.ResRoomList;
import com.example.demo.mapper.RoomMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {
	
	@Value("${file.Upimg}")
	private String path;
	
	private final RoomMapper roomMapper;
		/*이미지 등록*/
		@Transactional
		private void fileUpload(MultipartFile file, Long room_sid) throws IOException{
		String originalName = file.getOriginalFilename();	// 입력받은 파일의 원본 이름 저장
		String uuid = String.valueOf(UUID.randomUUID());	//uuid 생성
		String extension = originalName.substring(originalName.lastIndexOf(".")); // . 뒤로 잘라서 저장(확장자만 저장)
		String saveName = uuid+extension;// 랜덤으로 나온 uuid와 확장자를 더해서(연결해서) 저장
		File converFile = new File(path,saveName);	// 저장된것을 파일로 변환
		
		if(!converFile.exists()) {
			converFile.mkdirs();
		}
		file.transferTo(converFile);
		ReqRoomImg  upLoadImg = ReqRoomImg.builder()
																.room_sid(room_sid)
																.original_name(originalName)
																.extension(extension)
																.img_name(saveName)
																.build();
		roomMapper.uploadImg(upLoadImg);
																
	}
	
	/*방 등록*/
	@Transactional
	public void roomAdd(ReqRoomAdd add) throws IOException {
		roomMapper.roomAdd(add);
		for (ReqOptions options : add.getOptions()) {
			options.setRoom_sid(add.getRoom_sid());
			roomMapper.addOptions(options);
		}
		for(MultipartFile images : add.getImages()) {
			fileUpload(images, add.getRoom_sid());
		}
	}
	 /*방 목록*/
	@Transactional(readOnly = true)
	public List<ResRoomList>roomList(){
		return roomMapper.roomList();
	}
	/*방 상세보기*/
	@Transactional(readOnly = true)
	public List<ResRoomDetail>roomDetail(Long room_sid){
		return roomMapper.roomDetail(room_sid);
	}
	/*방 이미지 삭제*/
	@Transactional
	public void roomImgRemove(Long room_img_sid) {
		roomMapper.roomImgRemove(room_img_sid);
		//TODO - 파일까지 삭제하는 로직 추가예정
	}
	@Transactional
	public void roomDelete(Long room_sid) {
		roomMapper.roomDelete(room_sid);
	}

}
