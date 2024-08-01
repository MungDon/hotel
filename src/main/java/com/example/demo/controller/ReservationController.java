package com.example.demo.controller;

import com.example.demo.dto.request.reservation.ReqReservationAdd;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.service.ReservationService;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;
	private final RoomService roomService;


	/*임시예약*/
	@PostMapping("")
	@ResponseBody
	public ResponseEntity<ResponseDTO> temporaryReserve(@ModelAttribute ReqReservationAdd req) {
		log.info("asd"+req.getRoom_sid());
		ResponseDTO response =  reservationService.pencilIn(req);
		return ResponseEntity.ok(response);
	}

	/*임시예약취소*/
	@DeleteMapping("/cancel")
	@ResponseBody
	public ResponseEntity<ResponseDTO> deleteTemporaryReserve(@RequestParam(value = "user_sid")Long user_sid){
		ResponseDTO response = reservationService.deleteTemporaryReserve(user_sid);
		return ResponseEntity.ok(response);
	}


}
