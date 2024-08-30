package com.example.demo.dto.response.reservation;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ResReserveList{
    private Long reserve_sid;           // 예약 시퀀스

    private String room_name;            // 객실 명

    private String user_name;           // 예약자 명

    private LocalDate start_date;       // 체크인 일자

    private LocalDate end_date;         // 체크 아웃 일자

}
