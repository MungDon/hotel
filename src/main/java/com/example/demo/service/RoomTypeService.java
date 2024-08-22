package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.roomtype.ReqRoomTypeAdd;
import com.example.demo.dto.request.roomtype.ReqRoomTypeUpdate;
import com.example.demo.dto.request.roomtype.ReqTypeImg;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.roomtype.ResRoomTypeDetail;
import com.example.demo.dto.response.roomtype.ResRoomTypeList;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.mapper.RoomTypeMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomTypeService {

    private final RoomTypeMapper roomTypeMapper;
    private final RoomMapper roomMapper;

    @Value("${file.Upimg}")
    private String path;

    @Transactional(readOnly = true)
    public List<ResRoomTypeList> findAllRoomType(){
        return roomTypeMapper.findAllRoomType();
    }

    private ReqRoomTypeAdd createReqRoomTypeAdd(Map<String, String> param,int index){
        String type_name = param.get("typeAdd["+index+"].type_name");
        String room_size = param.get("typeAdd["+index+"].room_size");
        String bed_size = param.get("typeAdd["+index+"].bed_size");
        ReqRoomTypeAdd roomTypeAdd = ReqRoomTypeAdd.builder()
                .type_name(type_name)
                .room_size(room_size)
                .bed_size(bed_size)
                .build();
        return roomTypeAdd;
    }
    @Transactional
    public void saveImg(MultipartFile typeImg, Long room_type_sid) throws IOException {
        String originalName = typeImg.getOriginalFilename();
        String uuid = String.valueOf(UUID.randomUUID());
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String savaName = uuid + extension;
        File converFile = new File(path, savaName);
        typeImg.transferTo(converFile);

        ReqTypeImg reqTypeImg = ReqTypeImg.builder()
                .originalName(originalName)
                .imgName(savaName)
                .roomTypeSid(room_type_sid)
                .extension(extension)
                .build();
        roomTypeMapper.saveImg(reqTypeImg);
    }


    @Transactional
    public ResponseDTO roomTypeAdd (Map<String, String> param, Map<String, MultipartFile> files) throws IOException {
        int index = 0;
        int result = 0;
        while(param.containsKey("typeAdd["+index+"].type_name")){
            ReqRoomTypeAdd roomTypeAdd = createReqRoomTypeAdd(param,index);
            result += roomTypeMapper.roomTypeAdd(roomTypeAdd);
            MultipartFile typeImg = files.get("typeAdd["+index+"].type_img");
            saveImg(typeImg,roomTypeAdd.getRoom_type_sid());
            index ++;
        }
        return CommonUtils.successResponse(result, "객실 타입 저장 성공", ErrorCode.INSERT_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO roomTypeUpdate(ReqRoomTypeUpdate req) throws IOException {
        int result = roomTypeMapper.roomTypeUpdate(req);
        if(!CommonUtils.isEmpty(req.getType_img())){
            CommonUtils.deleteImg(path,req.getCurrent_img());
            roomTypeMapper.deleteImg(req.getCurrent_img(), req.getRoom_type_sid());
            saveImg(req.getType_img(),req.getRoom_type_sid());
        }
        log.info("1"+req.getRoom_type_sid());
        log.info("2"+req.getType_img());
        log.info("3"+req.getRoom_size());
        log.info("4"+req.getType_name());
        log.info("5"+req.getCurrent_img());
        log.info("6"+req.getBed_size());
        return CommonUtils.successResponse(result,"객실타입 수정 성공", ErrorCode.UPDATE_OPERATION_FAILED);
    }


    @Transactional
    public ResponseDTO roomTypeDelete(Long room_type_sid, String current_img){
        int result = roomTypeMapper.roomTypeDelete(room_type_sid);
        if(result > 0){
            CommonUtils.deleteImg(path,current_img);
        }
        return CommonUtils.successResponse(result,"객실타입 삭제 성공",ErrorCode.DELETE_OPERATION_FAILED);
    }

    @Transactional(readOnly = true)
    public ResRoomTypeDetail findRoomDetail(Long roomTypeSid){
        int result = roomMapper.chkDuplicateRoom(roomTypeSid);
        CommonUtils.throwRestCustomExceptionIf(result >= 1 ,ErrorCode.DUPLICATE_ROOM_DATA);
        return roomTypeMapper.findRoomTypeDetail(roomTypeSid);
    }
}
