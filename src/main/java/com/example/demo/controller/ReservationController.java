package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.request.reservation.ReqReservationAdd;
import com.example.demo.dto.response.room.ResRoomDetail;
import com.example.demo.service.ReservationService;
import com.example.demo.service.RoomService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;
	private final RoomService roomService;
	
	/*예약 폼*/
	@GetMapping("")
	public String reserveForm(@ModelAttribute("reserve")ReqReservationAdd req,Model model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Long user_sid = (Long)session.getAttribute("user_sid");
		
		reservationService.pencilIn(req,user_sid);
		
		ResRoomDetail roomDetail = roomService.roomDetail(req.getRoom_sid());
		model.addAttribute("roomDetail", roomDetail);
		return "reserve";
		
	}
}
