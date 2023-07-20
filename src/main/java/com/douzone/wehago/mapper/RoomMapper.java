package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Car;
import com.douzone.wehago.domain.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {

    void save(Room room);

    List<Room> findAll();

//    Member findOne(String memberId);

//    void update(Member member);

//    void delete(String memberId);
}
