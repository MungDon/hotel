package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.request.hotel.ReqEdtorImg;
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
	
	/*호텔 소개 등록*/
	@PostMapping("/management/intro/add")
	public String introAdd(ReqIntroAdd req) {
		hotelService.introAdd(req);
		return "redirect:/hotel/hotelmanage";
	}
	
	
	@PostMapping("/file/upload")
	@ResponseBody
	public String uploadImg(MultipartFile[] files) {
		String uploadFile = hotelService.uplaodImg(files);
		return uploadFile;
	}
	
	/*에디터 내 사진 업로드(복사/붙여넣기)*/
	@PostMapping("/file/uploadBase64")
	@ResponseBody
	public String uploadBase64Img(@RequestBody ReqEdtorImg req){
		String fileName = hotelService.uploadBase64Img(req);
		return fileName;
	}
}
