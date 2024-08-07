package com.example.demo.controller;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.request.hotel.ReqEdtorImg;
import com.example.demo.dto.request.hotel.ReqIntroAdd;
import com.example.demo.dto.request.hotel.ReqIntroUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.hotel.ResHotelIntro;
import com.example.demo.dto.response.hotel.ResIntroDetail;
import com.example.demo.dto.response.hotel.ResIntroList;
import com.example.demo.service.HotelService;
import com.example.demo.util.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {

	private final HotelService hotelService;

	/* 호텔 메인페이지 */
	@GetMapping("")
	public String hotelMain(@ModelAttribute("search") SearchDto search) {
		return "hotelmain";
	}

	/* 호텔 관리페이지 */
	@GetMapping("/management")
	public String hotelManagement() {
		return "hotelmanage";
	}

	/* 호텔소개 등록 현황 목록 */
	@GetMapping("/management/intro")
	public String hotelIntroManagement(Model model) {
		List<ResIntroList> intros = hotelService.findIntro();
		model.addAttribute("intros", intros);
		return "introlist";
	}

	/* 호텔 소개 등록 폼 */
	@GetMapping("/management/intro/add")
	public String introManagementAddForm() {
		return "intro_add";
	}

	/* 호텔 소개 등록 */
	@PostMapping("/management/intro/add")
	public String introAdd(ReqIntroAdd req, HttpServletRequest request) {
		req.setUser_sid(CommonUtils.getUserSid(request));
		hotelService.introAdd(req);
		return "redirect:/hotel/management/intro";
	}
	/* 소개글 작성 취소 */
	@PostMapping("/management/intro/add/cancel")
	@ResponseBody
	public ResponseEntity<ResponseDTO> writeCancel(@RequestBody List<String> fileNames) {
		ResponseDTO response = hotelService.deleteImg(fileNames);
		return ResponseEntity.ok(response);
	}
	
	/*호텔 소개 상세보기*/
	@GetMapping("/management/intro/detail/{hotel_sid}")
	public String introDetail(@PathVariable(value = "hotel_sid")Long hotel_sid,Model model){
		ResIntroDetail detail =  hotelService.introDetail(hotel_sid);
		model.addAttribute("detail", detail);
		return "intro_detail";
	}
	/*소개글 업데이트*/
	@GetMapping("/management/intro/update/{hotel_sid}")
	public String introUpdateForm(@PathVariable(value = "hotel_sid")Long hotel_sid,Model model){
		ResIntroDetail detail =  hotelService.introDetail(hotel_sid);
		model.addAttribute("detail", detail);
		return "intro_update";
	}
	@PostMapping("/management/intro/update")
	public String introUpdate(ReqIntroUpdate req){
		hotelService.introUpdate(req);
		return "redirect:/hotel/management/intro";
	}

	/*소개글 삭제*/
	@DeleteMapping("/management/intro/delete")
	@ResponseBody
	public ResponseEntity<ResponseDTO> introDelete(@RequestParam(value = "hotel_sid")Long hotel_sid){
		ResponseDTO response = hotelService.introDelete(hotel_sid);
		return ResponseEntity.ok(response);
	}

	/* 에디터 사진 업로드 */
	@PostMapping("/file/upload")
	@ResponseBody
	public String uploadImg(@RequestParam(value = "uploadFile") MultipartFile files) throws IOException {
		String uploadFile = hotelService.uploadImg(files);
		return uploadFile;
	}

	/* 이미지 미리보기 */
	@GetMapping("/file/display")
	@ResponseBody
	public ResponseEntity<byte[]> showImg(@RequestParam(value = "fileName") String fileName) {
		ResponseEntity<byte[]> result = hotelService.showImg(fileName);
		return result;
	}

	/* 에디터 내 사진 업로드(복사/붙여넣기) */
	@PostMapping("/file/uploadBase64")
	@ResponseBody
	public String uploadBase64Img(@RequestBody ReqEdtorImg req) {
		String fileName = hotelService.uploadBase64Img(req);
		return fileName;
	}

	@PostMapping("/select/intro")
	@ResponseBody
	public ResponseEntity<ResponseDTO> selectIntro(@RequestParam(value = "chkBoxValue") Long hotel_sid) {
		ResponseDTO response = hotelService.changeStatus(hotel_sid);
		return ResponseEntity.ok(response);
	}
	/* 유저 페이지 소개글 불러오기*/
	@GetMapping("/intro")
	public String hotelIntro(Model model){
		ResHotelIntro intro = hotelService.hotelIntro();
		model.addAttribute("intro",intro);
		return "hotel_intro";
	}
}
