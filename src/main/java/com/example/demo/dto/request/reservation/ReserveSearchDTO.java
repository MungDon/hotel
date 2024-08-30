package com.example.demo.dto.request.reservation;

import com.example.demo.dto.Search;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveSearchDTO extends Search {

    private Long user_sid;

    private String reservationType;         // 진행중인 예약 / 지난 예약
}
