package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.layout.ReqLayoutAdd;
import com.example.demo.dto.request.layout.ReqLayoutRoomAdd;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.layout.ResLayoutList;
import com.example.demo.mapper.HotelLayoutMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelLayoutService {

    private final HotelLayoutMapper hotelLayoutMapper;

    @Transactional(readOnly = true)
    public List<ResLayoutList> layoutList(){
        return hotelLayoutMapper.layoutList();
    }

    @Transactional
    public ResponseDTO hotelLayoutAdd(List<ReqLayoutAdd> addList) {
        int floorAddResult = 0;
        int layoutAddResult = 0;
        int result = 0;
        for (ReqLayoutAdd req : addList) {
            floorAddResult += hotelLayoutMapper.floorAdd(req);
            CommonUtils.throwRestCustomExceptionIf(floorAddResult!=addList.size(),ErrorCode.INSERT_OPERATION_FAILED);
            for (ReqLayoutRoomAdd roomReq : req.getRooms()) {
                roomReq.setFloorSid(req.getFloorSid());
                layoutAddResult += hotelLayoutMapper.hotelLayoutAdd(roomReq);
                CommonUtils.throwRestCustomExceptionIf(layoutAddResult!=req.getRooms().size(),ErrorCode.INSERT_OPERATION_FAILED);
            }
        }
        result = 1; // 모든 작업이 성공적으로 완료되었다는 의미
        return CommonUtils.successResponse(result, "구조 등록 성공", ErrorCode.INSERT_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO hotelLayoutRemoveAll(){
        int result = 0;
        result += hotelLayoutMapper.hotelLayoutRemoveAll();
        return CommonUtils.successResponse(result,"전체 초기화 완료",ErrorCode.DELETE_OPERATION_FAILED);
    }
}
