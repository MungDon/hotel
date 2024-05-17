package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.request.hotel.ReqIntroAdd;
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
	
	/*호텔소개 등록 현황 목록*/
	@GetMapping("/management/intro")
	public String hotelIntro() {
		// 소개 등록 보이게하기
		return "hotelintro";
	}
	
	/*호텔 소개 등록 폼*/
	@GetMapping("/management/intro/add")
	public String introAddForm() {
		return "introadd";
	}
	
	@PostMapping("/management/intro/add")
	public String introAdd() {
		return "redirect:/hotel/hotelmanage";
	}
	/*에디터 내 사진 업로드*/
	@PostMapping("/file/uploadBase64")
	@ResponseBody
	public String uploadImg(String base64Code, String extension){
		String fileName = hotelService.uploadImg(base64Code,extension);
		return fileName;
	}
}
