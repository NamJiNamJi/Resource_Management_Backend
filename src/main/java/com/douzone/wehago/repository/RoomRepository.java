package com.douzone.wehago.repository;

import com.douzone.wehago.dto.RoomDTO;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RoomRepository {

    private final SqlSession sqlSession;

    public void insertRoom(RoomDTO room){
        sqlSession.insert("com.douzone.wehago.mapper.RoomMapper.insertRoom", room);
    }

    public RoomDTO selectRoomById(Long spcSeq){
        return sqlSession.selectOne("com.douzone.wehago.mapper.RoomMapper.selectRoomById", spcSeq);
    }

    public List<RoomDTO> selectAllRooms(){
        return sqlSession.selectList("com.douzone.wehago.mapper.RoomMapper.selectAllRooms");
    }

    public void updateRoom(RoomDTO room){
        sqlSession.update("com.douzone.wehago.mapper.RoomMapper.updateRoom" ,room);
    }

    public void deleteRoom(Long spcSeq){
        sqlSession.delete("com.douzone.wehago.mapper.RoomMapper.deleteRoom", spcSeq);
    }

}
