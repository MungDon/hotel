package com.example.demo.controller;

import com.example.demo.dto.request.layout.ReqLayoutAdd;
import com.example.demo.dto.request.layout.ReqLayoutUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.layout.ResLayoutList;
import com.example.demo.dto.response.room.ResRoomList;
import com.example.demo.service.HotelLayoutService;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hotel/layout")
@RequiredArgsConstructor
public class HotelLayoutController {

    private final HotelLayoutService hotelLayoutService;
    private final RoomService roomService;

    @GetMapping("")
    public String hotelLayoutList(Model model){
        List<ResLayoutList> layoutList = hotelLayoutService.layoutList();
        List<ResRoomList> roomList = roomService.simpleRoomList();
        model.addAttribute("roomList",roomList);
        model.addAttribute("layoutList",layoutList);
        return "hotel_layout_list";
    }

    @GetMapping("/add")
    public String hotelLayoutAddForm(Model model){
        List<ResRoomList> roomList = roomService.simpleRoomList();
        model.addAttribute("roomList",roomList);
        return "hotel_layout_add";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<ResponseDTO> hotelLayoutAdd(@RequestBody List<ReqLayoutAdd> addList){
        ResponseDTO response = hotelLayoutService.hotelLayoutAdd(addList);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/remove/all")
    @ResponseBody
    public ResponseEntity<ResponseDTO> hotelLayoutRemoveAll(){
        ResponseDTO response = hotelLayoutService.hotelLayoutRemoveAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<ResponseDTO> hotelLayoutUpdate(@RequestBody List<ReqLayoutUpdate> updateList){
        ResponseDTO response = hotelLayoutService.hotelLayoutUpdate(updateList);
        return ResponseEntity.ok(response);
    }
}
