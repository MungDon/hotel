package com.example.demo.controller;

import com.example.demo.dto.request.reservation.ReqReservationAdd;
import com.example.demo.service.ReservationService;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;
	private final RoomService roomService;
	
	/*예약 폼*/
	@GetMapping("")
	@ResponseBody
	public ResponseEntity<String> reserveForm(@ModelAttribute ReqReservationAdd req) {
		reservationService.pencilIn(req);
		return ResponseEntity.ok("ok");
		
	}
}
