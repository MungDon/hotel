package com.example.demo.service;

import com.example.demo.dto.Pagination;
import com.example.demo.dto.ResPaging;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.request.room.ReqOptions;
import com.example.demo.dto.request.room.ReqRoomAdd;
import com.example.demo.dto.request.room.ReqRoomImg;
import com.example.demo.dto.response.room.ResRoomDetail;
import com.example.demo.dto.response.room.ResRoomList;
import com.example.demo.enums.ImgType;
import com.example.demo.enums.OptionType;
import com.example.demo.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {

	@Value("${file.Upimg}")
	private String path;

	private final RoomMapper roomMapper;

	private void convertThumbnail(File converFile, String saveName) throws IOException {
		File thumbnailFile = new File(path,"s_"+saveName); // 실제 디렉토리에 S 붙여서 저장
		
		BufferedImage bufferImg = ImageIO.read(converFile);
		
		double ratio  = 3;
		
		int width = (int)(bufferImg.getWidth()/ratio);
		int height = (int)(bufferImg.getHeight()/ratio);
		
		Thumbnails.of(converFile)
		.size(width, height)
		.toFile(thumbnailFile);
	}
	
	/* 이미지 실제 저장 */
	@Transactional
	public void saveFile(MultipartFile images, String img_type, Long room_sid) throws IOException {
		if(images != null && !images.isEmpty()) {
			String originalName = images.getOriginalFilename(); // 입력받은 파일의 원본 이름 저장
			String uuid = String.valueOf(UUID.randomUUID()); // uuid 생성
			String extension = originalName.substring(originalName.lastIndexOf(".")); // . 뒤로 잘라서 저장(확장자만 저장)
			String saveName = uuid + extension;// 랜덤으로 나온 uuid와 확장자를 더해서(연결해서) 저장
			File converFile = new File(path, saveName); // 저장된것을 파일로 변환

			if (!converFile.exists()) {//폴더없으면 생성
				converFile.mkdirs();
			}
			images.transferTo(converFile); 
			
			if(img_type.equals("thumbnail")) {
				convertThumbnail(converFile,saveName);
				saveName = "s_"+saveName; // db 에 저장될때 s 붙여서 저장
			}
			ReqRoomImg uploadImg= ReqRoomImg.builder().room_sid(room_sid).original_name(originalName).extension(extension)
					.img_name(saveName).img_type(img_type).build();
			roomMapper.uploadImg(uploadImg);
		}
	}
	
	/*이미지저장*/
	private void fileUpload(List<MultipartFile> images, String img_type, Long room_sid) throws IOException {
		for (MultipartFile img : images) {
			saveFile(img,  img_type, room_sid);
		}
	}
	
	/*옵션 저장*/
	private void addOptions(List<ReqOptions> options, Long room_sid,String optionType) {
		for (ReqOptions option : options) {
			if(option.getOption_name() == null && option.getOption_value() ==null) {
				continue;
			}
			option.setOption_type(optionType);
			option.setRoom_sid(room_sid);
			roomMapper.addOptions(option);
		}
	}
	/*옵션 수정*/
	private void updateOptions(List<ReqOptions> options, Long room_sid,String optionType) {
		for (ReqOptions option : options) {
			if(option.getOption_name() == null && option.getOption_value() ==null) {
				continue;
			}
			option.setOption_type(optionType);
			option.setRoom_sid(room_sid);
			roomMapper.optionUpdate(option);
		}
	}

	/* 방 등록 */
	@Transactional
	public void roomAdd(ReqRoomAdd add) throws IOException {
		roomMapper.roomAdd(add);
		addOptions(add.getOptions(), add.getRoom_sid(),OptionType.ROOM_INFO_OPTION.getName());
		addOptions(add.getUseOptions(), add.getRoom_sid(),OptionType.ROOM_USE_OPTION.getName());
		fileUpload(add.getImages(), ImgType.roomImg.name(), add.getRoom_sid());
		fileUpload(add.getThumbnail(), ImgType.thumbnail.name(), add.getRoom_sid());
	}

	/* 방 수정 */
	@Transactional
	public void roomUpdate(ReqRoomAdd reqeust) throws IOException {
		roomMapper.roomUpdate(reqeust);

		updateOptions(reqeust.getOptions(), reqeust.getRoom_sid(),OptionType.ROOM_INFO_OPTION.getName());
		updateOptions(reqeust.getUseOptions(), reqeust.getRoom_sid(),OptionType.ROOM_USE_OPTION.getName());
		
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
	public ResRoomDetail roomDetail(Long room_sid) {
		return roomMapper.roomDetail(room_sid);
	}

	/* 방 이미지 삭제 */
	@Transactional
	public void roomImgRemove(Long room_img_sid,String current_img) {
		roomMapper.roomImgRemove(room_img_sid);
		File file = new File(path, current_img);
		file.delete();

	}
	/*방 삭제(논리)*/
	@Transactional
	public void roomDelete(Long room_sid) {
		roomMapper.roomDelete(room_sid);
	}
	
	/*방삭제 (물리)*/
	@Transactional
	public void removeRoom(Long room_sid) {
		roomMapper.removeRoom(room_sid);
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
