package com.example.demo.dto.request.reservation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqReserveCancel {

    private Long reserveSid;        // 예약 시퀀스

    private String reserveNumber;   // 예약 번호
}
