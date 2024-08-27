package com.example.demo.mapper;

import com.example.demo.dto.request.layout.ReqLayoutRoomAdd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HotelLayoutMapper {

    int floorAdd(@Param(value = "floorName")String floorName);

    int hotelLayoutAdd(ReqLayoutRoomAdd req);
}
