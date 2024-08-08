package com.example.demo.controller;

import com.example.demo.dto.request.roomtype.ReqRoomTypeAdd;
import com.example.demo.dto.request.roomtype.ReqRoomTypeUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.roomtype.ResRoomTypeList;
import com.example.demo.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ResponseDTO> roomTypeAdd(@RequestBody List<ReqRoomTypeAdd> addList){
        ResponseDTO response = roomTypeService.roomTypeAdd(addList);
        return ResponseEntity.ok(response);
    }

    /*객실 타입 수정*/
    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<ResponseDTO> roomTypeUpdate(@ModelAttribute ReqRoomTypeUpdate req){
        ResponseDTO response = roomTypeService.roomTypeUpdate(req);
        return ResponseEntity.ok(response);
    }

    /*객실 타입 삭제*/
    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<ResponseDTO> roomTypeDelete(@RequestParam(value = "room_type_sid")Long room_type_sid){
        ResponseDTO response = roomTypeService.roomTypeDelete(room_type_sid);
        return ResponseEntity.ok(response);
    }
}
