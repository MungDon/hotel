package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.hotelsell.ReqHotelAdd;
import com.example.demo.mapper.HotelSellMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelSellService {
	
	private final HotelSellMapper hotelSellMapper;
	
	@Transactional
	public void hotelSellAdd(ReqHotelAdd add) {
		hotelSellMapper.hotelSellAdd(add);
	}
}
