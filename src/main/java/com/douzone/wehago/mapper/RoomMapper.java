package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Car;
import com.douzone.wehago.domain.Room;
import com.douzone.wehago.dto.RoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {

    void insertRoom(RoomDTO room);

    RoomDTO selectRoomById(Long spcSeq);

    List<RoomDTO> selectAllRooms();

    void updateRoom(RoomDTO room);

    void deleteRoom(Long spcSeq);
}
