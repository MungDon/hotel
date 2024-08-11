package com.example.demo.dto.request.roomtype;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ReqRoomTypeUpdate {

    private Long room_type_sid;

    private String type_name;

    private String room_size;

    private String bed_size;

    private String current_img;

    private MultipartFile type_img;
}
