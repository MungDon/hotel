package com.example.demo.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.request.room.ReqOptions;
import com.example.demo.dto.request.room.ReqRoomAdd;
import com.example.demo.dto.response.room.ResRoomList;
import com.example.demo.mapper.RoomMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

	private final RoomMapper roomMapper;

	@Transactional
	public void fileUpload(MultipartFile file) {
		String originalName = file.getOriginalFilename();
		String uuid = String.valueOf(UUID.randomUUID());
		String extension = originalName.substring(originalName.lastIndexOf("."));
		String saveName = uuid+extension;
		
	}
	
	
	@Transactional
	public void roomAdd(ReqRoomAdd add) {
		roomMapper.roomAdd(add);
		for (ReqOptions options : add.getOptions()) {
			options.setRoom_sid(add.getRoom_sid());
			roomMapper.addOptions(options);
		}
	}
	 
	@Transactional
	public List<ResRoomList> roomList(){
		return roomMapper.roomList();
	}

}
