package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.request.reservation.ReqReservationAdd;

@Mapper
public interface ReservationMapper {

	int pencilIn(ReqReservationAdd req);
}
