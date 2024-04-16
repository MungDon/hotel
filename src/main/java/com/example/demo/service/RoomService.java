package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.Pagination;
import com.example.demo.dto.ResPaging;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.request.room.ReqOptions;
import com.example.demo.dto.request.room.ReqRoomAdd;
import com.example.demo.dto.request.room.ReqRoomImg;
import com.example.demo.dto.response.room.ResRoomDetail;
import com.example.demo.dto.response.room.ResRoomList;
import com.example.demo.enums.ImgType;
import com.example.demo.mapper.RoomMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

	@Value("${file.Upimg}")
	private String path;

	private final RoomMapper roomMapper;


	/* 이미지 실제 저장 */
	@Transactional
	private void saveFile(MultipartFile images, String img_type, Long room_sid) throws IOException {
			String originalName = images.getOriginalFilename(); // 입력받은 파일의 원본 이름 저장
			String uuid = String.valueOf(UUID.randomUUID()); // uuid 생성
			String extension = originalName.substring(originalName.lastIndexOf(".")); // . 뒤로 잘라서 저장(확장자만 저장)
			String saveName = uuid + extension;// 랜덤으로 나온 uuid와 확장자를 더해서(연결해서) 저장
			File converFile = new File(path, saveName); // 저장된것을 파일로 변환

			if (!converFile.exists()) {
				converFile.mkdirs();
			}
			images.transferTo(converFile);
			ReqRoomImg uploadImg= ReqRoomImg.builder().room_sid(room_sid).original_name(originalName).extension(extension)
					.img_name(saveName).img_type(img_type).build();
			roomMapper.uploadImg(uploadImg);
	}
	/*이미지저장*/
	private void fileUpload(List<MultipartFile> images, String img_type, Long room_sid) throws IOException {
		for (MultipartFile img : images) {
			saveFile(img,  img_type, room_sid);
		}
	}

	/* 방 등록 */
	@Transactional
	public void roomAdd(ReqRoomAdd add) throws IOException {
		roomMapper.roomAdd(add);
		for (ReqOptions options : add.getOptions()) {
			options.setRoom_sid(add.getRoom_sid());
			roomMapper.addOptions(options);
		}
		fileUpload(add.getImages(), ImgType.roomImg.name(), add.getRoom_sid());
		fileUpload(add.getThumbnail(), ImgType.thumbnail.name(), add.getRoom_sid());
	}

	/* 방 수정 */
	@Transactional
	public void roomUpdate(ReqRoomAdd reqeust) throws IOException {
		roomMapper.roomUpdate(reqeust);
		for (ReqOptions options : reqeust.getOptions()) {
			options.setRoom_sid(reqeust.getRoom_sid());
			roomMapper.optionUpdate(options);
		}
		 fileUpload(reqeust.getImages(),  ImgType.roomImg.name() ,reqeust.getRoom_sid());
		 fileUpload(reqeust.getThumbnail(), ImgType.thumbnail.name(), reqeust.getRoom_sid());
	}

	/* 방 목록 */
	@Transactional(readOnly = true)
	public ResPaging<ResRoomList> roomList(SearchDto search) {
		
		//조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null 을 담아 반환
		int count = roomMapper.count(search);
		if(count < 1) {
			return new ResPaging<>(Collections.emptyList(), null);
		}
		
		// Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 search 에 계산된 페이지 정보 저장
		Pagination Pagination = new Pagination(count, search);
		
		// 계산됨 페이지 정보의 일부()를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
		List<ResRoomList> list = roomMapper.roomList(search);
		return new ResPaging<>(list, Pagination);
	}

	/* 방 상세보기 */
	@Transactional(readOnly = true)
	public List<ResRoomDetail> roomDetail(Long room_sid) {
		return roomMapper.roomDetail(room_sid);
	}

	/* 방 이미지 삭제 */
	@Transactional
	public void roomImgRemove(Long room_img_sid) {
		roomMapper.roomImgRemove(room_img_sid);
		// TODO - 파일까지 삭제하는 로직 추가예정
	}
	/*방 삭제*/
	@Transactional
	public void roomDelete(Long room_sid) {
		roomMapper.roomDelete(room_sid);
	}
	/*방 삭제 목록*/
	@Transactional(readOnly = true)
	public List<ResRoomList> deleteRooms(){
		return roomMapper.deleteRooms();
	}

	/*방 복구하기*/
	@Transactional
	public void restoreRoom(Long room_sid) {
		roomMapper.restoreRoom(room_sid);
	}
}
