package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.request.hotel.ReqHotelImg;
import com.example.demo.dto.request.hotel.ReqIntroAdd;

@Mapper
public interface HotelMapper {
	/**
	 * 호텔등록
	 * @param request
	 */
	void introAdd(ReqIntroAdd req);
	
	/*에디터 이미지 등록*/
	void uploadFile(ReqHotelImg img);
	
	/*에디터 취소시 파일 삭제*/
	int deleteFile(@Param(value="fileName")String fileName);
	
}
