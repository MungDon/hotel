package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.request.reservation.ReqReservationAdd;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationMapper {

	/*임시 예약*/
	int pencilIn(ReqReservationAdd req);

	/*임시예약 삭제*/
	int deleteTemporaryReserve(@Param(value = "user_sid")Long user_sid);
}
