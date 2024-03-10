package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.request.hotelsell.ReqHotelAdd;

@Mapper
public interface HotelSellMapper {
	
	/*호텔 판매 등록*/
	void hotelSellAdd(ReqHotelAdd add);
}
