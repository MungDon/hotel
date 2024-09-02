package com.example.demo.dto.response.reservation;

import lombok.Getter;

@Getter
public class ResReserveCancel {

    private Long reserve_sid;        // 예약 시퀀스

    private String reserve_number;   // 예약 번호
}
