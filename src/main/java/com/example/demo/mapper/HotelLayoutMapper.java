package com.example.demo.mapper;

import com.example.demo.dto.request.layout.ReqLayoutAdd;
import com.example.demo.dto.request.layout.ReqLayoutRoomAdd;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HotelLayoutMapper {

    int floorAdd(ReqLayoutAdd req);

    int hotelLayoutAdd(ReqLayoutRoomAdd req);
}
