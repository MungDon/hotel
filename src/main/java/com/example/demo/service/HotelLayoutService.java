package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.layout.ReqLayoutAdd;
import com.example.demo.dto.request.layout.ReqLayoutRoomAdd;
import com.example.demo.dto.request.layout.ReqLayoutUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.layout.ResLayoutList;
import com.example.demo.mapper.HotelLayoutMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class HotelLayoutService {

    private final HotelLayoutMapper hotelLayoutMapper;

    @Transactional(readOnly = true)
    public List<ResLayoutList> layoutList(){
        return hotelLayoutMapper.layoutList();
    }

    @Transactional
    public<T extends ReqLayoutAdd> ResponseDTO hotelLayoutProcess(List<T> layoutList,String successMessage){
        int floorAddResult = 0;
        int layoutAddResult = 0;
        int result = 0;
        for (T req : layoutList) {
            floorAddResult = hotelLayoutMapper.floorAdd(req);
            CommonUtils.throwRestCustomExceptionIf(floorAddResult==0,ErrorCode.INSERT_OPERATION_FAILED);
            for (ReqLayoutRoomAdd roomReq : req.getRooms()) {
                roomReq.setFloorSid(req.getFloorSid());
                layoutAddResult = hotelLayoutMapper.hotelLayoutAdd(roomReq);
                CommonUtils.throwRestCustomExceptionIf(layoutAddResult==0,ErrorCode.INSERT_OPERATION_FAILED);
            }
        }
        result = 1; // 모든 작업이 성공적으로 완료되었다는 의미
        return CommonUtils.successResponse(result,successMessage, ErrorCode.INSERT_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO hotelLayoutAdd(List<ReqLayoutAdd> addList) {
        return hotelLayoutProcess(addList, "구조 등록 성공");
    }

    @Transactional
    public ResponseDTO hotelLayoutRemoveAll(){
        int result = 0;
        result += hotelLayoutMapper.hotelLayoutRemoveAll();
        return CommonUtils.successResponse(result,"전체 초기화 완료",ErrorCode.DELETE_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO hotelLayoutUpdate(List<ReqLayoutUpdate> updateList){
        hotelLayoutMapper.hotelLayoutRemoveAll();
        return hotelLayoutProcess(updateList,"구조 업데이트 완료");
    }
}
