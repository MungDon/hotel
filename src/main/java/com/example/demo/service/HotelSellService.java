package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.HotelSellMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelSellService {
	
	private final HotelSellMapper hotelSellMapper;
}
