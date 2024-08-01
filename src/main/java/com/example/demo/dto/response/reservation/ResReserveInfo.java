package com.example.demo.dto.response.reservation;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ResReserveInfo {

    private Long reserve_sid;

    private LocalDate start_date;

    private LocalDate end_date;

    private int adult_cnt;

    private int child_cnt;

}
