package com.example.demo.mapper;

import com.example.demo.dto.request.layout.ReqLayoutAdd;
import com.example.demo.dto.request.layout.ReqLayoutRoomAdd;
import com.example.demo.dto.response.layout.ResLayoutList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelLayoutMapper {

    int floorAdd(ReqLayoutAdd req);

    int hotelLayoutAdd(ReqLayoutRoomAdd req);

    List<ResLayoutList> layoutList();
}
