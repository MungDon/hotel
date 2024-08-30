package com.example.demo.mapper;

import com.example.demo.dto.request.reservation.ReqReservationAdd;
import com.example.demo.dto.request.reservation.ReserveSearchDTO;
import com.example.demo.dto.response.reservation.ResReserveInfo;
import com.example.demo.dto.response.reservation.ResReserveList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {

	/*임시 예약*/
	int pencilIn(ReqReservationAdd req);

	/*임시예약 삭제*/
	int deleteTemporaryReserve(@Param(value = "user_sid")Long user_sid);
	
	/*예약 상태 변경*/
	int changeReserveStatus(@Param(value = "reserveSid")Long reserveSid,@Param(value = "reserveStatus")String reserveStatus);
	
	/*예약 정보*/
	ResReserveInfo reserveInfo(@Param(value = "reserveSid")Long reserveSid);

	/*예약 리스트 카운트*/
	int reserveListCnt(ReserveSearchDTO dto);

	List<ResReserveList> reserveList(ReserveSearchDTO dto);
}
