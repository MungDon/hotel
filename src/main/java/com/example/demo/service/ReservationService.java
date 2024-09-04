package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.Pagination;
import com.example.demo.dto.ResPaging;
import com.example.demo.dto.request.reservation.ReqReservationAdd;
import com.example.demo.dto.request.reservation.ReserveSearchDTO;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.reservation.ResReserveCancel;
import com.example.demo.dto.response.reservation.ResReserveInfo;
import com.example.demo.dto.response.reservation.ResReserveList;
import com.example.demo.enums.ReservationStatus;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationMapper reservationMapper;
	private final PaymentService paymentService;
	
	/*임시 예약*/
	@Transactional
	public Long pencilIn(ReqReservationAdd req) {
		req.setRoom_number(randomRoomNumber(req));
		req.setReserve_status(ReservationStatus.TEMPORARY.getStatus());
		int result = reservationMapper.pencilIn(req);
		CommonUtils.throwRestCustomExceptionIf(result != 1, ErrorCode.FAIL_TEMPORARY_RESERVATION);
		return req.getReserve_sid();
	}

	@Transactional(readOnly = true)
	public String randomRoomNumber(ReqReservationAdd req){
		List<String> roomNumbers = reservationMapper.availableRoomNumbers(req);
		CommonUtils.throwRestCustomExceptionIf(CommonUtils.isEmpty(roomNumbers),ErrorCode.NO_AVAILABLE_ROOMS);
		return  roomNumbers.get(0);
	}

	/*임시예약 삭제*/
	@Transactional
	public ResponseDTO deleteTemporaryReserve(Long reserveSid){
		int result = reservationMapper.deleteReserve(reserveSid);
		return CommonUtils.successResponse(result,"임시예약 삭제 완료",ErrorCode.DELETE_OPERATION_FAILED);
	}

	@Transactional(readOnly = true)
	public ResReserveInfo reserveInfo(Long reserveSid){
		return reservationMapper.reserveInfo(reserveSid);
	}
	
	/*예약 리스트*/
	@Transactional(readOnly = true)
	public ResPaging<ResReserveList> reserveList(ReserveSearchDTO dto){
		// 검색 조건에 맞는 데이터가 없을 경우 빈 리스트, null 반환
		int listCount = reservationMapper.reserveListCnt(dto);
		if(listCount < 1){
			return new ResPaging<>(Collections.emptyList(),null);
		}
		// 페이징 객체 생성과 동시에 페이징 계산
		Pagination pagination = new Pagination(listCount,dto);
		dto.setPagination(pagination);
		// 계산완료 후 해당 변수들로 offset 정해서 데이터 가져옴
		List<ResReserveList> reserveList = reservationMapper.reserveList(dto);
		// 응답 객체 안에 리스트와 페이징 객체 담음
		return new ResPaging<>(reserveList, pagination);
	}

	/*사용자 예약 취소*/
	@Transactional
	public ResponseDTO cancelReservation(Long reserveSid, String reserveNumber) throws IOException, InterruptedException {
		int DBDeleteResult = reservationMapper.deleteReserve(reserveSid);
		CommonUtils.throwRestCustomExceptionIf(DBDeleteResult < 0,ErrorCode.DB_DELETE_FAILED);
		int refundResult =  paymentService.refundReserve(reserveNumber);
		return CommonUtils.successResponse(refundResult,"예약이 취소되어 환불 처리되었습니다.",ErrorCode.DELETE_OPERATION_FAILED);
	}
	
	/*타입 및 객실 삭제 시 예약 삭제(취소)*/
	@Transactional
	public void deleteReservation(Long roomSid) throws IOException, InterruptedException {
		List<ResReserveCancel> targetReserveSids = reservationMapper.findReserveByRoomSid(roomSid);
		for(ResReserveCancel res : targetReserveSids){
			cancelReservation(res.getReserve_sid(),res.getReserve_number());
		}
	}

}
