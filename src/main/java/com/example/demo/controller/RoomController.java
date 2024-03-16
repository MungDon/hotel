package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.request.room.ReqRoomAdd;
import com.example.demo.dto.response.room.ResRoomList;
import com.example.demo.service.RoomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
	
	private final RoomService roomService;
	
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
	public String roomAddAdd(@Valid @ModelAttribute ReqRoomAdd add) {
		roomService.roomAdd(add);
		return "redirect:/room/list";
	}
}
