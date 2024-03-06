package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {
		
	
	@GetMapping("")
	public String hotelForm() {
		return "hotel";
	}
	
	@GetMapping("/seller")
	public String hotelSellForm() {
		return "seller";
	}
}
