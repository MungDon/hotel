package com.example.demo.dto.response.layout;

import lombok.Getter;

import java.util.List;

@Getter
public class ResTodayReserveList {

    private String floor_name;

    private Long floor_sid;

    private List<ResRoomLayout> rooms;


}
