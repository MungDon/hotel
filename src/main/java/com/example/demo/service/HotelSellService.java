package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.hotelsell.ReqRoomAdd;
import com.example.demo.dto.request.hotelsell.ReqRoomAdd.Options;
import com.example.demo.mapper.HotelSellMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelSellService {
	
	private final HotelSellMapper hotelSellMapper;
	
	@Transactional
	public void hotelSellAdd(ReqRoomAdd add) {
		hotelSellMapper.hotelSellAdd(add);
		for(Options options : add.getOptions()) {
			options.setRoom_sid(add.getRoom_sid());
			hotelSellMapper.addOptions(options);
		}
		
		}
		
	}
