package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.reservation.ReqReservationAdd;
import com.example.demo.enums.ReservationType;
import com.example.demo.mapper.ReservationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationMapper reservationMapper; 
	
	@Transactional
	public void pencilIn(ReqReservationAdd req) {
		req.setReserve_status(ReservationType.TEMPORARY.getName());
		reservationMapper.pencilIn(req);
	}
}
