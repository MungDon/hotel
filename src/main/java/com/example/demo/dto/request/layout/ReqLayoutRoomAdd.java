package com.example.demo.dto.request.layout;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqLayoutRoomAdd {

    private Long roomSid;

    private Long floorSid;

    private String roomNumber;
}
