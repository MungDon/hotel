package com.example.demo.controller;

import com.example.demo.dto.ResPaging;
import com.example.demo.dto.request.reservation.ReqReservationAdd;
import com.example.demo.dto.request.reservation.ReqReserveCancel;
import com.example.demo.dto.request.reservation.ReserveSearchDTO;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.reservation.ResReserveInfo;
import com.example.demo.dto.response.reservation.ResReserveList;
import com.example.demo.service.ReservationService;
import com.example.demo.util.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;


	/*임시예약*/
	@PostMapping("")
	@ResponseBody
	public ResponseEntity<Long> temporaryReserve(@ModelAttribute @Valid ReqReservationAdd req) {
		Long reserveSid =  reservationService.pencilIn(req);
		return ResponseEntity.ok(reserveSid);
	}

	/*임시예약취소*/
	@DeleteMapping("/cancel")
	@ResponseBody
	public ResponseEntity<ResponseDTO> deleteTemporaryReserve(@RequestParam(value = "reserveSid")Long reserveSid){
		ResponseDTO response = reservationService.deleteTemporaryReserve(reserveSid);
		return ResponseEntity.ok(response);
	}

	/*예약 정보*/
	@GetMapping("/detail")
	@ResponseBody
	public ResponseEntity<ResReserveInfo> reserveInfo(@RequestParam(value = "reserveSid")Long reserveSid){
		ResReserveInfo reserveInfo = reservationService.reserveInfo(reserveSid);
		return ResponseEntity.ok(reserveInfo);
	}

	@GetMapping("/list")
	public String reserveList(@ModelAttribute("search")ReserveSearchDTO dto, Model model, HttpServletRequest request){
		Long user_sid = CommonUtils.getUserSid(request);
		dto.setUser_sid(user_sid);
		ResPaging<ResReserveList> reserveList = reservationService.reserveList(dto);
		model.addAttribute("reserveList",reserveList);
		return "reserve_list";
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseEntity<ResponseDTO> reserveCancel(ReqReserveCancel req) throws IOException, InterruptedException {
		ResponseDTO response =  reservationService.cancelReservation(req.getReserveSid(), req.getReserveNumber());
		return ResponseEntity.ok(response);
	}
}
