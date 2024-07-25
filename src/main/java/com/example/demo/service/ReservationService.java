package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.reservation.ReqReservationAdd;
import com.example.demo.enums.ReservationType;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationMapper reservationMapper; 
	
	@Transactional
	public void pencilIn(ReqReservationAdd req) {
		req.setReserve_status(ReservationType.TEMPORARY.getName());
		int result = reservationMapper.pencilIn(req);
		CommonUtils.throwRestCustomExceptionIf(result != 1, ErrorCode.FAIL_TEMPORARY_RESERVATION);
	}
}
