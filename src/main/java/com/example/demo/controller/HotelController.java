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
	
	
	@GetMapping("")
	public String hotelMain() {
		return "hotelmain";
	}
	
	
	/**
	 * 호텔 등록 폼페이지를 불러오는 메서드
	 * @param model
	 * @return hotel 등록 폼페이지
	 */
	@GetMapping("add")
	public String hotelForm() {
		return "hoteladd";
	}
	/**
	 * 폼에서 받은 입력데이터를 db 에 저장하는 메서드
	 * @param request
	 * @return 호텔메인 페이지로 redirect 
	 */
	@PostMapping("add")
	public String hotelAdd(ReqHotelAdd request) {
		hotelService.hotelAdd(request);
		return "redirect:/hotel/main";
	}
	
}
