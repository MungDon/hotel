package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.roomtype.ReqRoomTypeAdd;
import com.example.demo.dto.request.roomtype.ReqRoomTypeUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.roomtype.ResRoomTypeList;
import com.example.demo.mapper.RoomTypeMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeService {

    private final RoomTypeMapper roomTypeMapper;

    @Transactional(readOnly = true)
    public List<ResRoomTypeList> findAllRoomType(){
        return roomTypeMapper.findAllRoomType();
    }

    @Transactional
    public ResponseDTO roomTypeAdd (List<ReqRoomTypeAdd> addList){
        int result = 0;
        for(ReqRoomTypeAdd req : addList){
            result += roomTypeMapper.roomTypeAdd(req);
        }
        if(result == addList.size()){
            result = 1;
        }
        return CommonUtils.successResponse(result, "객실 타입 저장 성공", ErrorCode.INSERT_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO roomTypeUpdate(ReqRoomTypeUpdate req){
        int result = roomTypeMapper.roomTypeUpdate(req);
        return CommonUtils.successResponse(result,"객실타입 수정 성공", ErrorCode.UPDATE_OPERATION_FAILED);
    }


    @Transactional
    public ResponseDTO roomTypeDelete(Long room_type_sid){
        int result = roomTypeMapper.roomTypeDelete(room_type_sid);
        return CommonUtils.successResponse(result,"객실타입 삭제 성공",ErrorCode.DELETE_OPERATION_FAILED);
    }
}
