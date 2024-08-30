package com.example.demo.dto.response.reservation;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ResReserveInfo {

    private Long reserve_sid;           // 예약 시퀀스

    private String user_name;           // 예약자 명
    
    private String user_email;          // 예약자 이메일

    private LocalDate start_date;       // 체크인 일자

    private LocalDate end_date;         // 체크 아웃 일자

    private int adult_cnt;              // 예약 인원(성인)

    private int child_cnt;              //// 예약 인원(소아)

}
