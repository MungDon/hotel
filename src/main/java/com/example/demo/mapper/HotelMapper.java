package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.request.hotel.ReqHotelImg;
import com.example.demo.dto.request.hotel.ReqIntroAdd;
import com.example.demo.dto.response.hotel.ResIntroList;

@Mapper
public interface HotelMapper {
	
	/*호텔등록 */
	void introAdd(ReqIntroAdd req);
	
	/*에디터 이미지 등록*/
	void uploadFile(ReqHotelImg img);
	
	/*에디터 취소시 파일 삭제*/
	int deleteFile(@Param(value="fileName")String fileName);
	
	/*소개 글 목록*/
	List<ResIntroList> findByIntro();
	
	/*상태 리셋*/
	void resetStatus();
	/*대표 소개글 설정*/
	int changeStatus(@Param(value = "hotel_sid")Long hotel_sid);
}
