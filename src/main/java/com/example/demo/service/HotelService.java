package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.hotel.ReqHotelAdd;
import com.example.demo.mapper.HotelMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

	private final HotelMapper hotelMapper;

	/**
	 * 호텔등록
	 * @param request
	 */
	public void hotelAdd(ReqHotelAdd request) {
		hotelMapper.hotelAdd(request);
	}
}
