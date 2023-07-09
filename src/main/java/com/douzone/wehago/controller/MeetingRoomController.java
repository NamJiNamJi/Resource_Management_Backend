package com.douzone.wehago.controller;


import com.douzone.wehago.domain.Room;
import com.douzone.wehago.dto.RoomSaveResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@Controller
public class MeetingRoomController {

    @PostMapping("room")
    @ResponseBody
    public RoomSaveResponse test(@RequestBody dto dto){
        log.info("{}",dto);
        RoomSaveResponse roomSaveResponse = RoomSaveResponse.builder()
                .ok("ok")
                .message("회의실 예약 성공")
                .build();
        return roomSaveResponse;
    }

    
    @PostMapping("/meetingRoom")
    @ResponseBody
    public RoomSaveResponse reserveRoom(@RequestBody Room room){
        log.info("{}", room.toString());

        RoomSaveResponse roomSaveResponse = RoomSaveResponse.builder()
                .ok("ok")
                .message("회의실 예약 성공")
                .build();
        return roomSaveResponse;
    }

    @Data
    static class dto{
        private String title;
        private String name;
        private String room;
    }
}
