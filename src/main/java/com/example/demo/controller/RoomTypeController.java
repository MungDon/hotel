package com.example.demo.controller;

import com.example.demo.dto.request.roomtype.ReqRoomTypeUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.roomtype.ResRoomTypeDetail;
import com.example.demo.dto.response.roomtype.ResRoomTypeList;
import com.example.demo.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/type")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;


    /*객실 타입 목록*/
    @GetMapping("")
    public String roomTypeList(Model model){
        List<ResRoomTypeList> typeList = roomTypeService.findAllRoomType();
        model.addAttribute("typeList",typeList);
        return "type_list";
    }

    /*객실 타입 등록 폼*/
    @GetMapping("/add")
    public String roomTypeAddForm(){
        return "type_add";
    }
    
    /*객실 타입 등록*/
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<ResponseDTO> roomTypeAdd(@RequestParam Map<String, String> params, @RequestParam Map<String, MultipartFile> files) throws IOException {
        ResponseDTO response = roomTypeService.roomTypeAdd(params, files);
        return ResponseEntity.ok(response);
    }

    /*객실 타입 수정*/
    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<ResponseDTO> roomTypeUpdate(@ModelAttribute ReqRoomTypeUpdate req) throws IOException {
        ResponseDTO response = roomTypeService.roomTypeUpdate(req);
        return ResponseEntity.ok(response);
    }

    /*객실 타입 삭제*/
    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<ResponseDTO> roomTypeDelete(@RequestParam(value = "room_type_sid")Long room_type_sid,
                                                      @RequestParam(value = "current_img")String current_img){
        ResponseDTO response = roomTypeService.roomTypeDelete(room_type_sid,current_img);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/find/detail")
    @ResponseBody
    public ResponseEntity<ResRoomTypeDetail> findRoomDetail(@Param(value = "roomTypeSid")Long roomTypeSid){
        ResRoomTypeDetail roomTypeDetail = roomTypeService.findRoomDetail(roomTypeSid);
        return ResponseEntity.ok(roomTypeDetail);
    }

}
