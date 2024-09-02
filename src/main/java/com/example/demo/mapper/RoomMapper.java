package com.example.demo.mapper;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.request.room.ReqOptions;
import com.example.demo.dto.request.room.ReqRoomAdd;
import com.example.demo.dto.request.room.ReqRoomImg;
import com.example.demo.dto.request.room.ReqRoomUpdate;
import com.example.demo.dto.response.room.ResRoomDetail;
import com.example.demo.dto.response.room.ResRoomImg;
import com.example.demo.dto.response.room.ResRoomList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomMapper {
	
	/*방 등록*/
	void roomAdd(ReqRoomAdd add);
	
	/*옵션 등록*/
	void addOptions(ReqOptions options);
	
	/*이미지 파일 등록*/
	void uploadImg(ReqRoomImg uploadImg);
	
	/*방 수정*/
	void roomUpdate(ReqRoomUpdate request);
	
	/*옵션 수정*/
	void optionUpdate(ReqOptions options);

	/*방 목록*/
	List<ResRoomList> roomList(SearchDto serach);
	
	/*전체 레코드 수*/
	int count(SearchDto dto);
	
	/*방 상세보기*/
	ResRoomDetail roomDetail(@Param(value="room_sid")Long room_sid);
	
	/*이미지 삭제*/
	void roomImgRemove(@Param(value="room_img_sid")Long room_img_sid);
	
	/*방 삭제(논리)*/
	void roomDelete(@Param(value="room_sid")Long room_sid);
	
	int chkDuplicateRoom(@Param(value="roomTypeSid")Long roomTypeSid);

	ResRoomImg findCurrentThumbnail(@Param(value = "room_sid")Long room_sid);

	void clearAllOption(@Param(value = "room_sid")Long room_sid);
	
	/*호텔레이아웃 방등록용 객실목록*/
	List<ResRoomList> simpleRoomList();

	Long findAllByRoomTypeSid(@Param(value = "room_type_sid")Long room_type_sid);

}
