package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.request.hotelsell.ReqHotelAdd;
import com.example.demo.service.HotelSellService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sell")
public class HotelSellController {
	
	private final HotelSellService hotelSellService;
	
	
	@GetMapping("")
	public String hotelSellForm(Model model) {
		model.addAttribute("options", new ReqHotelAdd());
		return "sell";
	}
	
	@PostMapping("")
	public String hotelSellAdd(@ModelAttribute ReqHotelAdd add) {
		hotelSellService.hotelSellAdd(add);
		return "redirecr:/sell/list";
	}
}
