package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.request.room.ReqRoomAdd;
import com.example.demo.dto.response.room.ResRoomDetail;
import com.example.demo.dto.response.room.ResRoomList;
import com.example.demo.service.RoomService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
	
	private final RoomService roomService;
	
	/*방 목록*/
	@GetMapping("")
	public String roomList(Model model) {
		List<ResRoomList> rooms = roomService.roomList();
		model.addAttribute("rooms", rooms);
		return "roomlist";
	}
	
	/*방등록 페이지 폼*/
	@GetMapping("/add")
	public String roomAddForm(Model model) {
		model.addAttribute("options", new ReqRoomAdd());
		return "roomadd";
	}
	
	/*방 등록 기능*/
	@PostMapping("/add")
	public String roomAddAdd(@Valid @ModelAttribute ReqRoomAdd add) throws IOException {
		roomService.roomAdd(add);
		return "redirect:/room";
	}
	
	/*방 상세보기*/
	@GetMapping("/detail/{room_sid}")
	public String roomDetail(@PathVariable(value = "room_sid")Long room_sid, Model model) {
		List<ResRoomDetail> detail = roomService.roomDetail(room_sid);
		model.addAttribute("detail", detail);
		return "roomdetail";
	}
	/*방 수정페이지 폼*/
	@GetMapping("/update/{room_sid}")
	public String roomUpdate(@PathVariable(value="room_sid")Long room_sid, Model model) {
		List<ResRoomDetail> update = roomService.roomDetail(room_sid);
		model.addAttribute("update", update);
		return "roomupdate";
	}
	/*이미지 삭제*/

}
