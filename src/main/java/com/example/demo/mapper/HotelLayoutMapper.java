package com.example.demo.mapper;

import com.example.demo.dto.request.layout.ReqLayoutAdd;
import com.example.demo.dto.request.layout.ReqLayoutRoomAdd;
import com.example.demo.dto.response.layout.ResLayoutList;
import com.example.demo.dto.response.layout.ResTodayReserveList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotelLayoutMapper {

    int floorAdd(ReqLayoutAdd req);

    int hotelLayoutAdd(ReqLayoutRoomAdd req);

    List<ResLayoutList> layoutList();

    int hotelLayoutRemoveAll();

    List<ResTodayReserveList> retrieveTodayReservations();

    int deleteHotelLayoutByRoomSid(@Param(value = "roomSid")Long roomSid);
}
