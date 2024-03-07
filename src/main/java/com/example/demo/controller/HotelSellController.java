package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.HotelSellService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sell")
public class HotelSellController {
	
	private final HotelSellService hotelSellService;
	
	
	@GetMapping("")
	public String hotelSellForm() {
		return "seller";
	}
}
