package com.example.demo.dto.response.layout;

import lombok.Getter;

import java.util.List;

@Getter
public class ResLayoutList {

    private String floor_name;

    private List<ResRoomLayout> rooms;
}
