package com.example.demo.mapper;

import com.example.demo.dto.request.hotel.ReqHotelImg;
import com.example.demo.dto.request.hotel.ReqIntroAdd;
import com.example.demo.dto.request.hotel.ReqIntroUpdate;
import com.example.demo.dto.response.hotel.ResHotelIntro;
import com.example.demo.dto.response.hotel.ResIntroDetail;
import com.example.demo.dto.response.hotel.ResIntroList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotelMapper {
	
	/*호텔 소개 등록 */
	void introAdd(ReqIntroAdd req);
	
	/*소개등록 내용 가져오기*/
	ResIntroDetail findIntroDetailByHotelSid(@Param(value = "hotel_sid")Long hotel_sid);
	
	/*소개글 시퀀스삽입*/
	void insertHotelSid(@Param(value = "imgFileName")String imgFileName, @Param(value = "hotel_sid")Long hotel_sid);

	/*소개글 수정*/
	void introUpdate(ReqIntroUpdate req);

	int introDelete(@Param(value = "hotel_sid")Long hotel_sid);
	/*에디터 이미지 등록*/
	void uploadFile(ReqHotelImg img);
	
	/*에디터 취소시 파일 삭제*/
	int deleteFile(@Param(value="fileName")String fileName);
	
	/*소개 글 목록*/
	List<ResIntroList> findIntro();
	
	/*상태 리셋*/
	void resetStatus();
	
	/*대표 소개글 설정*/
	int changeStatus(@Param(value = "hotel_sid")Long hotel_sid);

	/*대표로 설정된 소개글 가져오기*/
	ResHotelIntro hotelIntro();
}
