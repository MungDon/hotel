package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import com.example.demo.util.CommonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.request.hotel.ReqEdtorImg;
import com.example.demo.dto.request.hotel.ReqIntroAdd;
import com.example.demo.dto.response.hotel.ResIntroList;
import com.example.demo.service.HotelService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

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
	public String hotelIntro(Model model) {
		List<ResIntroList> intros = hotelService.findByIntro();
		model.addAttribute("intros", intros);
		return "introlist";
	}

	/* 호텔 소개 등록 폼 */
	@GetMapping("/management/intro/add")
	public String introAddForm() {
		return "introadd";
	}

	/* 호텔 소개 등록 */
	@PostMapping("/management/intro/add")
	public String introAdd(ReqIntroAdd req, HttpServletRequest request) {
		req.setUser_sid(CommonUtils.getSession(request));
		hotelService.introAdd(req);
		return "redirect:/hotel/management/intro";
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

	@PostMapping("/file/cancel")
	@ResponseBody
	public ResponseEntity<String> writeCancel(@RequestBody List<String> fileNames) {
		int result = hotelService.deleteImg(fileNames);
		if (result == 1) {
			return ResponseEntity.ok("등록현황페이지로 넘어갑니다");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
	}

	@PostMapping("/select/intro")
	@ResponseBody
	public ResponseEntity<String> selectIntro(@RequestParam(value = "chkBoxValue") Long hotel_sid) {
		int result = hotelService.changeStatus(hotel_sid);
		if (result == 1) {
			return ResponseEntity.ok("성공");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
	}

}
