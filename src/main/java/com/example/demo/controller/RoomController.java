package com.example.demo.controller;

import com.example.demo.dto.ResPaging;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.request.room.ReqRoomAdd;
import com.example.demo.dto.response.room.ResRoomDetail;
import com.example.demo.dto.response.room.ResRoomList;
import com.example.demo.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
	
	private final RoomService roomService;
	
	/*방 목록*/
	@GetMapping("")
	public String roomList(Model model,@ModelAttribute("search") SearchDto search) {
		ResPaging<ResRoomList> rooms = roomService.roomList(search);
		model.addAttribute("rooms", rooms);
		return "roomlist";
	}

	/*방등록 페이지 폼*/
	@GetMapping("/add")
	public String roomAddForm() {
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
	@ResponseBody
	public ResponseEntity<ResRoomDetail> roomDetail(@PathVariable(value = "room_sid")Long room_sid) {
		ResRoomDetail detail = roomService.roomDetail(room_sid);
		return ResponseEntity.ok(detail);
	}
	/*방 수정페이지 폼*/
	@GetMapping("/update/{room_sid}")
	public String roomUpdateForm(@PathVariable(value="room_sid")Long room_sid, Model model) {
		ResRoomDetail update = roomService.roomDetail(room_sid);
		model.addAttribute("update", update);
		return "roomupdate";
	}
	
	/*방 수정*/
	@PutMapping("/update")
	public String roomUpdate(@Valid @ModelAttribute ReqRoomAdd request) throws IOException {
		roomService.roomUpdate(request);
		return"redirect:/room";
	}
	
	/*이미지 삭제*/
	@DeleteMapping("/img/delete")
	@ResponseBody
	public void roomImgRemove(@RequestBody Long room_img_sid) {
		roomService.roomImgRemove(room_img_sid);
	}
	
	/*방 삭제(논리)*/
	@DeleteMapping("/delete")
	@ResponseBody
	public void roomDelete(@RequestParam(value = "room_sid") Long room_sid) {
		roomService.roomDelete(room_sid);
	}
	/*삭제된 방목록(휴지통)*/
	@GetMapping("/delete/list")
	@ResponseBody
	public List<ResRoomList> deleteRooms (){
		List<ResRoomList> deleteRooms = roomService.deleteRooms();
		return deleteRooms;
	}
	/*방복구하기*/
	@PutMapping("/restore")
	@ResponseBody
	public void restoreRoom(@RequestParam(value = "room_sid")  Long room_sid) {
		roomService.restoreRoom(room_sid);
	}
	
	/*방 영구 삭제*/
	@DeleteMapping("/remove")
	@ResponseBody
	public void removeRoom(@RequestBody Long room_sid) {
		roomService.removeRoom(room_sid);
	}
}
