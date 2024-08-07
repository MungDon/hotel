package com.example.demo.dto.request.roomtype;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqRoomTypeAdd {

    private String type_name;

    private String room_size;

    private String bed_size;
}
