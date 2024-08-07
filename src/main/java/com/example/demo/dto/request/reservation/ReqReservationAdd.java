package com.example.demo.dto.request.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReqReservationAdd {

	@NotBlank(message = "객실 번호 누락")
	private Long room_sid;				// 객실 번호

	@NotBlank(message = "유저 정보 누락")
	private Long user_sid;				// 유저 정보

	@NotBlank(message = "체크인 일자 누락")
	private LocalDate start_date;		// 체크인 일자

	@NotBlank(message = "체크 아웃일자스 누락")
	private LocalDate end_date;		// 체크 아웃일자

	@NotBlank(message = "예약자 성인 수 누락")
	private int adult_cnt;					// 성인 수

	private int child_cnt;					// 어린이 수
	
	private String reserve_status;		// 예약상태

}
