package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.reservation.ReqReservationAdd;
import com.example.demo.dto.response.ResponseDTO;
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
	public ResponseDTO pencilIn(ReqReservationAdd req) {
		req.setReserve_status(ReservationType.TEMPORARY.getName());
		int result = reservationMapper.pencilIn(req);
		return CommonUtils.successResponse(result,"임시예약 완료", ErrorCode.INSERT_OPERATION_FAILED);
	}

	@Transactional
	public ResponseDTO deleteTemporaryReserve(Long user_sid){
		int result = reservationMapper.deleteTemporaryReserve(user_sid);
		return CommonUtils.successResponse(result,"임시예약 삭제 완료",ErrorCode.DELETE_OPERATION_FAILED);
	}
}
