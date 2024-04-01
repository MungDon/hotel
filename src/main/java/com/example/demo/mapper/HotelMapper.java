package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.request.hotel.ReqHotelAdd;

@Mapper
public interface HotelMapper {
	/**
	 * 호텔등록
	 * @param request
	 */
	void hotelAdd(ReqHotelAdd request);
}
