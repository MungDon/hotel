package com.example.demo.dto.request.reservation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReqReservationAdd {


	private Long room_sid;				// 객실 번호
	
	private Long user_sid;				// 유저 정보
	
	private LocalDate start_date;		// 체크인 일자
	
	private LocalDate end_date;		// 체크 아웃일자
	
	private int adult_cnt;					// 성인 수
	
	private int child_cnt;					// 어린이 수
	
	private String reserve_status;		// 예약상태

}
