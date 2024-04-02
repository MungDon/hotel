package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.request.room.ReqOptions;
import com.example.demo.dto.request.room.ReqRoomAdd;
import com.example.demo.dto.request.room.ReqRoomImg;
import com.example.demo.dto.response.room.ResRoomDetail;
import com.example.demo.dto.response.room.ResRoomList;

@Mapper
public interface RoomMapper {
	
	/*방 등록*/
	void roomAdd(ReqRoomAdd add);
	
	/*옵션 등록*/
	void addOptions(ReqOptions options);
	
	/*이미지 파일 등록*/
	void uploadImg(ReqRoomImg uploadImg);
	
	/*방 수정*/
	void roomUpdate(ReqRoomAdd request);
	
	/*옵션 수정*/
	void optionUpdate(ReqOptions options);
	
	/*이미지 수정*/
	void imgUpdate(ReqRoomImg images);
	/*방 목록*/
	List<ResRoomList> roomList();
	
	/*방 상세보기*/
	List<ResRoomDetail> roomDetail(Long room_sid);
	
	/*이미지 삭제*/
	void roomImgRemove(Long room_img_sid);
	
	/*방 삭제*/
	void roomDelete(Long room_sid);
}
