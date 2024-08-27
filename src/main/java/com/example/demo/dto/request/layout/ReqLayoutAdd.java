package com.example.demo.dto.request.layout;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReqLayoutAdd {

    private Long floorSid;

    private String floorName;

    private List<ReqLayoutRoomAdd> layoutRoomAddList;
}
