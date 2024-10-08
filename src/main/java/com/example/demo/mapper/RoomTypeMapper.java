package com.example.demo.mapper;

import com.example.demo.dto.request.roomtype.ReqRoomTypeAdd;
import com.example.demo.dto.request.roomtype.ReqRoomTypeUpdate;
import com.example.demo.dto.request.roomtype.ReqTypeImg;
import com.example.demo.dto.response.roomtype.ResRoomTypeDetail;
import com.example.demo.dto.response.roomtype.ResRoomTypeList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomTypeMapper {

    List<ResRoomTypeList> findAllRoomType();

    int roomTypeAdd(ReqRoomTypeAdd req);

    int roomTypeUpdate(ReqRoomTypeUpdate req);

    int roomTypeDelete(@Param(value = "room_type_sid")Long room_type_sid);

    void saveImg(ReqTypeImg req);

    int deleteImg(@Param(value = "current_img")String current_img,@Param(value = "room_type_sid")Long room_type_sid);

    ResRoomTypeDetail findRoomTypeDetail(@Param(value = "roomTypeSid")Long roomTypeSid);
}
