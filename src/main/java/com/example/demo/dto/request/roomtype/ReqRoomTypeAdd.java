package com.example.demo.dto.request.roomtype;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqRoomTypeAdd {

    private Long room_type_sid;

    private String type_name;

    private String room_size;

    private String bed_size;

}
