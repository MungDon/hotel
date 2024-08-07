package com.example.demo.dto.request.roomtype;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqRoomTypeUpdate {

    private Long room_type_sid;

    private String type_name;

    private String room_size;

    private String bed_size;
}
