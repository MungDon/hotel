package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.request.hotel.ReqHotelAdd;
import com.example.demo.service.HotelService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {
		
	private final HotelService hotelService;
	
	/*호텔 메인페이지*/
	@GetMapping("")
	public String hotelMain() {
		return "hotelmain";
	}
	
	/*호텔 관리페이지*/
	@GetMapping("/management")
	public String hotelManagement() {
		return "hotelmanage";
	} 
	

	
}
