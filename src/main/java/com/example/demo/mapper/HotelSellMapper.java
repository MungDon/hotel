package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.request.hotelsell.ReqHotelAdd;
import com.example.demo.dto.request.hotelsell.ReqHotelAdd.Options;

@Mapper
public interface HotelSellMapper {
	
	/*호텔 판매 등록*/
	void hotelSellAdd(ReqHotelAdd add);
	
}
